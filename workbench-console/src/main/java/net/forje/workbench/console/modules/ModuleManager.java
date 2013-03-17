package net.forje.workbench.console.modules;

import net.forje.workbench.module.Module;
import net.forje.workbench.module.WorkbenchModule;
import net.forje.workbench.services.LoggingService;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.util.PluginManagerUtil;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ModuleManager {

    final PluginManager _pluginManager;

    private final Map<String, ModuleAdapter> _modules = new TreeMap<String, ModuleAdapter>();

    protected Iterator moduleMacros() {
        return _modules.keySet().iterator();
    }

    public ModuleManager(final PluginManager pluginManager) {

        _pluginManager = pluginManager;

    }

    public void loadModules() throws Exception {

        processModule(new ModulesModule(this));
        processModule(new ExitModule());

        PluginManagerUtil pmu = new PluginManagerUtil(_pluginManager);
        final Collection<Module> modules = pmu.getPlugins(Module.class);

        for (Iterator<Module> iterator = modules.iterator(); iterator.hasNext(); ) {

            Module module = iterator.next();
            processModule(module);

        }

    }

    private void processModule(final Module module) throws InstantiationException, IllegalAccessException {

        final LoggingService loggingService = _pluginManager.getPlugin(LoggingService.class);

        final ModuleAdapter moduleAdapter = new ModuleAdapter(module);

        final Class<? extends Module> moduleClass = module.getClass();

        final WorkbenchModule workbenchModule = moduleClass.getAnnotation(WorkbenchModule.class);

        if (workbenchModule == null) {
            loggingService.error(this.getClass(), "Class [" + module.getClass().getCanonicalName() +"] did not have the WorkbenchModule" );
            return;
        }

        final String macro = moduleAdapter.getMacro();

        loggingService.info(this.getClass(), "Adding class [" + module.getClass().getCanonicalName() +"] with macro [" + macro +"]");

        _modules.put(macro, moduleAdapter);

    }

    public boolean isRegisteredModule(final String macro) {
        return _modules.containsKey(macro);
    }

    /**
     * returns the ModuleAdapter object for the specified module macro
     *
     * @param macro the macro for the module we're interested in
     * @return the corresponding ModuleAdapter
     */
    public ModuleAdapter getModuleAdapter(final String macro) {

        final ModuleAdapter moduleAdapter = _modules.get(macro);
        // todo throw exception if no match

        return moduleAdapter;

    }

}
