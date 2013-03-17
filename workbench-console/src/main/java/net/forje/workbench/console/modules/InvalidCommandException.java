package net.forje.workbench.console.modules;

public class InvalidCommandException extends Exception {

    public InvalidCommandException(final String moduleMacro,
                                   final String commandMacro) {
        super("command [" + commandMacro + "] is not valid for module [" + moduleMacro + "]");
    }
}
