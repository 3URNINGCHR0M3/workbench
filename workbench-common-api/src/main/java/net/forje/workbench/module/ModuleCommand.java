package net.forje.workbench.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies methods which are to be published as commands.  This annotation will only be checked for methods on
 * classes with the WorkbenchModule annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ModuleCommand {
    /**
     * The human readable name of the
     */
    String name();

    /**
     * A longer description of the command
     */
    String description();

    /**
     * the code which the command be invoked
     */
    String macro();

    /**
     * A list of descriptions for parameters to the command.
     */
    String[] arguments();

}
