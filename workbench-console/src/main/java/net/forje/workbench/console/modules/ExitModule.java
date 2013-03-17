package net.forje.workbench.console.modules;

import net.forje.workbench.EnvironmentLevel;
import net.forje.workbench.module.*;

@WorkbenchModule(name = "Exit", description = "Exits the system", macro = "exit", environmentLevel = {EnvironmentLevel.All})
public class ExitModule implements Module {

    public void start() {

    }

    public void stop() {

    }

    @ModuleCommand(name = "Exit", description = "Exit the system", macro = "exit", arguments = {})
    @ModuleDefaultCommand
    public void exit(ModuleContext context) {
        System.out.println("need to do shutdown stuff.");
        // todo stop modules
        // todo shutdown services
        // think about setting a variable on the ModuleContext to indicate that the system should exit
        System.exit(0);
    }

}
