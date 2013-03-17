package net.forje.workbench.services;

/**
 * This service will expose the ability to persist configuration settings and similar values in a managed store.
 * There are no requirements on how the data will be stored (locally, remotely, database, file, registry)
 * It can be expected that if the values are stored locally, they will be stored in a secure manner.
 */

public interface ConfigurationService {

    /**
     * Returns the value of the specified property name.
     *
     * @param name the name of the property
     * @return the value of the property or null if a value is not defined.
     * @throws Exception if any error occurs.
     */
    public String getProperty(String name) throws Exception;

    /**
     * Sets the value of the specified property
     *
     * @param name  the name of the property
     * @param value
     * @throws Exception if any error occurs.
     */
    public String setProperty(String name, String value) throws Exception;

}
