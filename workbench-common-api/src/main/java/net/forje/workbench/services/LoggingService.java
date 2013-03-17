package net.forje.workbench.services;

import net.xeoh.plugins.base.Plugin;

public interface LoggingService extends Plugin {

    public void trace(Class subject, String message);
    public void trace(Class subject, String message, Throwable throwable);

    public void debug(Class subject, String message);
    public void debug(Class subject, String message, Throwable throwable);

    public void info(Class subject, String message);
    public void info(Class subject, String message, Throwable throwable);

    public void warn(Class subject, String message);
    public void warn(Class subject, String message, Throwable throwable);

    public void error(Class subject, String message);
    public void error(Class subject, String message, Throwable throwable);

    public void fatal(Class subject, String message);
    public void fatal(Class subject, String message, Throwable throwable);

}
