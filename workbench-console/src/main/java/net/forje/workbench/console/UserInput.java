package net.forje.workbench.console;

import java.util.List;

/**
 * This object represents the parsed version of user input from the command prompt.  It will be the dta transfer object
 * between the console layer and the module layer.  This object only represents a well formed command string.  It does
 * not mean that the module/command pair is valid or that the set of parameters are sufficient or valid.
 */
public class UserInput {

    private final String _module;
    private final String _command;

    private final List _arguments;

    public UserInput(final String module,
                     final String command,
                     final List arguments) {
        _module = module;
        _command = command;
        _arguments = arguments;
    }

    public String getModule() {
        return _module;
    }

    public String getCommand() {
        return _command;
    }

    public List getArguments() {
        return _arguments;
    }

}
