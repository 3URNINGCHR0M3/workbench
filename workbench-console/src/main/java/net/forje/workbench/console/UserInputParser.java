package net.forje.workbench.console;

import java.util.ArrayList;
import java.util.List;

public class UserInputParser {

    public UserInput parse(final String input) {

        if (input == null || input.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        final String[] strings = input.split(" ");

        final String one = strings[0];

        final String[] split = one.split(":");

        final String module;
        final String command;

        module = split[0];
        if (split.length > 1) {
            command = split[1];
        } else {
            command = "default";
        }


        List arguments = new ArrayList();

        if (strings.length > 1) {

            String argument;

            for (int i = 1; i < strings.length; i++) {
                argument = strings[i];
                arguments.add(argument);
            }
        }

        UserInput userInput = new UserInput(module, command, arguments);

        return userInput;

    }

}
