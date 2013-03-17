package net.forje.workbench.console.modules;

import net.forje.workbench.module.ModuleCommand;
import net.forje.workbench.module.ModuleDefaultCommand;

import java.lang.reflect.Method;

public class CommandAdapter implements Comparable {

    private final Object _object;
    private final Method _method;
    private final boolean _isDefault;
    private final ModuleCommand _moduleCommand;
    private String _argumentString;

    public CommandAdapter(final Object object,
                          final Method method) {

        _object = object;
        _method = method;

        _isDefault = (_method.getAnnotation(ModuleDefaultCommand.class) != null);
        _moduleCommand = _method.getAnnotation(ModuleCommand.class);

    }

    public boolean isDefault() {
        return _isDefault;
    }

    public String getName() {
        return _moduleCommand.name();
    }

    public String getDescription() {
        return _moduleCommand.description();
    }

    public String getMacro() {
        return _moduleCommand.macro();
    }

    public String getArguments() {

        if (_argumentString == null) {
            _argumentString = buildArgumentString();
        }

        return _argumentString;

    }

    private String buildArgumentString() {

        final String[] arguments = _moduleCommand.arguments();

        final String argumentString;

        if (arguments.length == 0) {
            argumentString = "";
        } else {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < arguments.length; i++) {
                if (i > 0) {
                    stringBuilder.append(" ,");
                }
                String argument = arguments[i];
                stringBuilder.append(argument);
            }

            argumentString = stringBuilder.toString();
        }
        return argumentString;
    }

    public int compareTo(final Object o) {
        CommandAdapter that = (CommandAdapter) o;
        return this.getMacro().compareTo(that.getMacro());
    }

}
