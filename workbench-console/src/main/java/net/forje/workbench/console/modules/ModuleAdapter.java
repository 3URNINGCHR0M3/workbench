package net.forje.workbench.console.modules;

import net.forje.workbench.EnvironmentLevel;
import net.forje.workbench.console.UserInput;
import net.forje.workbench.module.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class will wrap a module and expose handle the reflective calls needed to invoke it's functionality
 */
public class ModuleAdapter {

    private final Module _module;
    private final WorkbenchModule _workbenchModule;
    private final Class<? extends Module> _moduleClass;
    private List<CommandAdapter> _commandAdapters;

    public ModuleAdapter(final Module module) {
        _module = module;
        _moduleClass = module.getClass();
        _workbenchModule = _moduleClass.getAnnotation(WorkbenchModule.class);
    }

    public String getName() {
        return _workbenchModule.name();
    }

    public String getDescription() {
        return _workbenchModule.description();
    }

    public String getMacro() {
        return _workbenchModule.macro();
    }

    public boolean supportsEnvironmentLevel(final EnvironmentLevel environmentLevel) {

        final EnvironmentLevel[] environmentLevels = _workbenchModule.environmentLevel();

        for (int i = 0; i < environmentLevels.length; i++) {
            EnvironmentLevel level = environmentLevels[i];
            if (level.equals(environmentLevel)) {
                return true;
            }

            if (EnvironmentLevel.All.equals(level)) {
                return true;
            }

        }


        return false;
    }

    public void invoke(final ModuleContext moduleContext,
                       final UserInput userInput)
            throws Exception {

        final Method[] methods = _moduleClass.getMethods();

        final String command = userInput.getCommand();
        final boolean useDefault = "default".equals(command.toLowerCase());

        Method methodToInvoke = null;
        ModuleCommand methodAnnotation;

        for (final Method method : methods) {

            methodAnnotation = method.getAnnotation(ModuleCommand.class);

            if (methodAnnotation == null) {
                continue;
            }

            if (useDefault) {
                final ModuleDefaultCommand moduleDefaultCommand = method.getAnnotation(ModuleDefaultCommand.class);
                if (moduleDefaultCommand != null) {
                    methodToInvoke = method;
                    break;
                }
            }

            if (command.equals(methodAnnotation.macro())) {
                methodToInvoke = method;
                break;
            }

        }

        if (methodToInvoke != null) {

            final List userInputArguments = userInput.getArguments();

            final Class<?>[] parameterTypes = methodToInvoke.getParameterTypes();
            int argCount = parameterTypes.length;

            if (userInputArguments.size() < (argCount - 1)) {
                throw new InsufficientArgumentsException();
            }

            final Object[] arguments = new Object[argCount];
            arguments[0] = moduleContext;

            for (int i = 1; i < arguments.length; i++) {
                arguments[i] = userInputArguments.get(i - 1);
            }

            methodToInvoke.invoke(_module, arguments);

        } else {
            throw new InvalidCommandException(userInput.getModule(), userInput.getCommand());
        }


    }

    protected List<CommandAdapter> getCommands() {

        if (_commandAdapters == null) {
            _commandAdapters = new ArrayList();

            final Method[] methods = _moduleClass.getMethods();
            for (int i = 0; i < methods.length; i++) {

                Method method = methods[i];
                if (method.getAnnotation(ModuleCommand.class) != null) {
                    _commandAdapters.add(new CommandAdapter(_module, method));
                }

            }

            Collections.sort(_commandAdapters);

        }

        return _commandAdapters;

    }

}
