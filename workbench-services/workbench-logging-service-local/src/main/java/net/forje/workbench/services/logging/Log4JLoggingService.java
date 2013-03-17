package net.forje.workbench.services.logging;

import net.forje.workbench.services.LoggingService;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import org.apache.log4j.Logger;

@PluginImplementation
public class Log4JLoggingService implements LoggingService {

    @Override
    public void trace(final Class subject, final String message) {
        Logger.getLogger(subject).trace(message);
    }

    @Override
    public void trace(final Class subject, final String message, final Throwable throwable) {
        Logger.getLogger(subject).trace(message, throwable);
    }

    @Override
    public void debug(final Class subject, final String message) {
        Logger.getLogger(subject).debug(message);
    }

    @Override
    public void debug(final Class subject, final String message, final Throwable throwable) {
        Logger.getLogger(subject).debug(message, throwable);
    }

    @Override
    public void info(final Class subject, final String message) {
        Logger.getLogger(subject).info(message);
    }

    @Override
    public void info(final Class subject, final String message, final Throwable throwable) {
        Logger.getLogger(subject).info(message, throwable);
    }

    @Override
    public void warn(final Class subject, final String message) {
        Logger.getLogger(subject).warn(message);
    }

    @Override
    public void warn(final Class subject, final String message, final Throwable throwable) {
        Logger.getLogger(subject).warn(message, throwable);
    }

    @Override
    public void error(final Class subject, final String message) {
        Logger.getLogger(subject).error(message);
    }

    @Override
    public void error(final Class subject, final String message, final Throwable throwable) {
        Logger.getLogger(subject).error(throwable);
    }

    @Override
    public void fatal(final Class subject, final String message) {
        Logger.getLogger(subject).fatal(message);
    }

    @Override
    public void fatal(final Class subject, final String message, final Throwable throwable) {
        Logger.getLogger(subject).fatal(message, throwable);
    }

}
