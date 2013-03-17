package net.forje.workbench.tools;

import net.forje.workbench.module.ModuleCommand;
import net.forje.workbench.module.WorkbenchModule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ModuleValidator {

    private final Class _moduleClass;

    public ModuleValidator(Class moduleClass) {

        _moduleClass = moduleClass;

    }

    public boolean check() {



        final Method[] methods = _moduleClass.getMethods();

        WorkbenchModule workbenchModule = (WorkbenchModule) _moduleClass.getAnnotation(WorkbenchModule.class);

        final String macro = workbenchModule.macro();

        final int index = macro.indexOf(" ");
        if (index >= 0) {
            return false;
        }


        Method method;
        ModuleCommand annotation;
        boolean foundCommandMethod = false;

        for (int i = 0; i < methods.length; i++) {

            method = methods[i];
            annotation = method.getAnnotation(ModuleCommand.class);
            if (annotation != null) {
                foundCommandMethod = true;
                break;

            }

        }

        return foundCommandMethod;

    }

}
