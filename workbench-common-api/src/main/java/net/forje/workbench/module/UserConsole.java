package net.forje.workbench.module;

/**
 * This interface defines the set of methods which a Module may interface with the console interface.
 * It is mandated because java.io.Console is concrete and there is no interface it implements.
 */
public interface UserConsole extends java.io.Flushable {

    public char[] readPassword();

    public java.lang.String readLine();

    public java.io.Console printf(java.lang.String s, java.lang.Object... objects);

    public java.io.Console format(java.lang.String s, java.lang.Object... objects);

    public java.io.PrintWriter writer();

}
