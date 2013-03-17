package net.forje.workbench.modules.hello;

import net.forje.workbench.EnvironmentLevel;
import net.forje.workbench.module.Module;
import net.forje.workbench.module.ModuleCommand;
import net.forje.workbench.module.ModuleContext;
import net.forje.workbench.module.WorkbenchModule;
import net.forje.workbench.services.LoggingService;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@WorkbenchModule(name = "HelloWord", description = "A demo module that says 'Hello, World", macro = "hello", environmentLevel = {EnvironmentLevel.Dev})
@PluginImplementation
public class HelloWorldModule implements Module {

    @InjectPlugin
    public LoggingService _loggingService;


    public void start() {
        _loggingService.trace(this.getClass(), "starting");
    }

    public void stop() {
        _loggingService.trace(this.getClass(), "stopping");
    }

    @ModuleCommand(name = "Say hello", description = "causes the module to say hello", macro = "hello", arguments = {})
    public void sayHello(ModuleContext context) {
        _loggingService.info(this.getClass(), "saying hello");
    }

}
