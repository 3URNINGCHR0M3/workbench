package net.forje.workbench.console.modules;

import net.forje.workbench.EnvironmentLevel;
import net.forje.workbench.module.*;

import java.util.Iterator;
import java.util.List;

@WorkbenchModule(name = "Modules", description = "Reports information about the installed modules", macro = "modules", environmentLevel = {EnvironmentLevel.All})
public class ModulesModule implements Module {

    private final ModuleManager _moduleManager;

    public ModulesModule(final ModuleManager moduleManager) {
        _moduleManager = moduleManager;
    }

    public void start() {

    }

    public void stop() {

    }

    @ModuleCommand(name = "List", description = "Lists all modules and their descriptions", macro = "list", arguments = {})
    @ModuleDefaultCommand
    public void list(final ModuleContext context) {

        final UserConsole userConsole = context.getUserConsole();

        String macro;
        ModuleAdapter adapter;

        final Iterator iterator = _moduleManager.moduleMacros();
        while (iterator.hasNext()) {

            macro = (String) iterator.next();
            adapter = _moduleManager.getModuleAdapter(macro);
            final String name = adapter.getName();
            final String description = adapter.getDescription();

            userConsole.format("%-10s%-20s%-50s%n", new String[]{macro, name, description});

        }

    }

    @ModuleCommand(name = "Commands List", description = "Lists all commands on a module and their descriptions", macro = "commands", arguments = {"macro"})
    public void commands(final ModuleContext context,
                         final String macro) {

        final UserConsole userConsole = context.getUserConsole();

        final ModuleAdapter moduleAdapter = _moduleManager.getModuleAdapter(macro);
        final List<CommandAdapter> commands = moduleAdapter.getCommands();
        for (Iterator<CommandAdapter> iterator = commands.iterator(); iterator.hasNext(); ) {
            CommandAdapter commandAdapter = iterator.next();
            userConsole.format("%-10s%-20s%-50s%n", new String[]{commandAdapter.getMacro(), commandAdapter.getName(), (commandAdapter.isDefault() ? "(default) " : "") + commandAdapter.getDescription()});
        }


    }

}
