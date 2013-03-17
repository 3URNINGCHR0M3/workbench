package net.forje.workbench.console;

import net.forje.workbench.console.modules.ModuleAdapter;
import net.forje.workbench.console.modules.ModuleManager;
import net.forje.workbench.module.ModuleContext;
import net.forje.workbench.module.UserConsole;
import net.xeoh.plugins.base.PluginManager;

import java.io.Console;
import java.util.Date;
import java.util.Scanner;

/**
 * The command line app for the console.
 */
public class WorkbenchConsole {

    private static final String NO_CONSOLE = "Error: Console unavailable";
    private static final String TIME_FORMAT = "%1$tH:%1$tM:%1$tS";
    private static final String PROMPT = TIME_FORMAT + " $ ";

    private static ModuleManager _moduleManager;
    private static PluginManager _pluginManager;
    private static ModuleContext _moduleContext;

    public static void main(String[] args) {

        Console console = System.console();
        UserConsole userConsole = new JavaConsoleAdapter(console);

        _moduleContext = new ModuleContextImpl(userConsole);

        if (console != null) {

            try {
                Plugins.initialize();
                _pluginManager = Plugins.getPluginManager();
                _moduleManager = new ModuleManager(_pluginManager);
                _moduleManager.loadModules();
            } catch (Exception e) {
                e.printStackTrace();
            }

            execCommandLoop(console);

        } else {
            throw new RuntimeException(NO_CONSOLE);
        }

    }

    private static void execCommandLoop(final Console console) {

        final UserInputParser userInputParser = new UserInputParser();
        String commandLine;

        while (true) {

            commandLine = console.readLine(PROMPT, new Date());

            if (commandLine == null || commandLine.trim().length() == 0) {
                continue;
            }

            final UserInput userInput = userInputParser.parse(commandLine);

            final String moduleMacro = userInput.getModule();

            if (_moduleManager.isRegisteredModule(moduleMacro)) {
                ModuleAdapter adapter = _moduleManager.getModuleAdapter(moduleMacro);
                try {
                    adapter.invoke(_moduleContext, userInput);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                console.printf("macro [%s] does not identify a registered module%n", moduleMacro);
            }

        }

    }

}
