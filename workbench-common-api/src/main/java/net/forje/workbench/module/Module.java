package net.forje.workbench.module;

import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

/**
 * This is a marker interface which all modules must implement.  It is used to enforce the relationship with the
 * plugin framework.  All other meta data is exposed through the WorkbenchModule annotation.
 */
@PluginImplementation
public interface Module extends Plugin {

    /**
     * Will be called at the beginning of the module life cycle.  Implementations should take necessary steps to
     * initialize their state.
     */
    public void start() throws Exception;

    /**
     * Will be called at the end of the module life cycle.   Implementations should take necessary steps to
     * finalize their state.
     */
    public void stop() throws Exception;

}
