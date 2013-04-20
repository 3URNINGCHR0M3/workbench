package net.forje.workbench.console;

import java.util.List;

/**
 * This object represents the parsed version of user input from the command prompt.  It will be the data transfer object
 * between the console layer and the module layer.  This object only represents a well formed command string.  It does
 * not mean that the module/command pair is valid or that the set of parameters are sufficient or valid in the context
 * of that module/command pair.
 */
public class UserInput {

    /**
     * the macro for the module being invoked
     */
    private final String _module;

    /**
     * the command being invoked
     */
    private final String _command;

    /**
     * A list of String value from the command line.
     */
    private final List _arguments;

    /**
     * Constructor.
     *
     * @param module the macro for the module being invoked
     * @param command the command being invoked
     * @param arguments the arguments captured from the command line
     *
     * @see UserInputParser
     */
    public UserInput(final String module,
                     final String command,
                     final List arguments) {
        _module = module;
        _command = command;
        _arguments = arguments;
    }

    /**
     * Returns the macro for the module invoked.
     *
     * @return the macro for the module
     */
    public String getModule() {
        return _module;
    }

    /**
     * The command invoked
     *
     * @return the command invoked
     */
    public String getCommand() {
        return _command;
    }

    /**
     * List of Strings collected as arguments from the command line
     */
    public List getArguments() {
        return _arguments;
    }

}
