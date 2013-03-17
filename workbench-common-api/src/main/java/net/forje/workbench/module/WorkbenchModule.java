package net.forje.workbench.module;

import net.forje.workbench.EnvironmentLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies classes which are to be published as modules
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WorkbenchModule {
    /**
     * The human readable name for the module.
     */
    String name();

    /**
     * A longer description of the module and its functionality
     */
    String description();

    /**
     * The code which the module can be invoked, may not contain spaces.
     */
    String macro();

    /**
     * Returns a list of tokens indicating which environment levels the module can be run in
     */
    EnvironmentLevel[] environmentLevel();

}
