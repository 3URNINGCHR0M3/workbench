package net.forje.workbench.console;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.options.addpluginsfrom.OptionReportAfter;

import java.io.File;
import java.util.Properties;

public class Plugins {

    private static PluginManager _pluginManager;

    public static synchronized void initialize() {

        // todo not sure this is thread safe, think so since method is synchronized
        if (_pluginManager == null) {
            _pluginManager = PluginManagerFactory.createPluginManager();

            // this (i think) loads plugins from the current classpath
//            _pluginManager.addPluginsFrom(ClassURI.CLASSPATH);


            final File pluginsDirectory = new File("./plugins/");

            if (!pluginsDirectory.exists()) {
                pluginsDirectory.mkdirs();
            } else {
                _pluginManager.addPluginsFrom(pluginsDirectory.toURI(), new OptionReportAfter());
            }

        }

    }

    public static synchronized PluginManager getPluginManager() {

        if (_pluginManager == null) {
            initialize();
        }

        return _pluginManager;

    }
}
