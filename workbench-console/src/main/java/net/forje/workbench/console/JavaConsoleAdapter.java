package net.forje.workbench.console;

import net.forje.workbench.module.UserConsole;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaConsoleAdapter implements UserConsole {

    private final Console _console;

    public JavaConsoleAdapter(final Console console) {
        _console = console;
    }

    public char[] readPassword() {
        return _console.readPassword();
    }

    public String readLine() {
        return _console.readLine();
    }

    public Console printf(final String s, final Object... objects) {
        return _console.printf(s, objects);
    }

    public Console format(final String s, final Object... objects) {
        return _console.format(s, objects);
    }

    public PrintWriter writer() {
        return _console.writer();
    }

    public void flush() throws IOException {
        _console.flush();
    }
}
