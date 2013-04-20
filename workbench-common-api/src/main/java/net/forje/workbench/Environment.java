package net.forje.workbench;

/**
 * <p>
 * This interface exposes the set of data used to access a specific environment.  The information about the environment
 * will be exposed as a set of properties.  This interface is read only so that client classes cannot manipulate the
 * data in this bundle.
 * </p>
 * <p>
 * The use of an enumeration here is a security mechanism, to control the set of which can be accessed through this
 * class.  If String keys  were used, this collection of values could be used to pass any kind of data around.
 * </p>
 */
public interface Environment {

    /**
     * This enumeration represents the set of environment properties exposed by the service.
     */
    public static enum Property {
        /**
         * The JDBC URL for the environments OLTP database
         */
        OltpJdbcUrl,
        /**
         * The JDBC URL for the environments OLAP database
         */
        OlapJdbcUrl,
        /**
         * The base URL for the application web entry point
         */
        WebServerBaseUrl,
        /**
         * The base URL for the application server web entry point
         */
        AppServerBaseUrl,
        /**
         * The host name of the batch server for this environment
         */
        BatchServerAddress,
        /**
         * The public key to access the web service for this environment
         */
        WebServiceKey,
        /**
         * The subject of the web interface
         */
        WebServiceSubject,
        /**
         * An instance of EnvironmentLevel (Dev, QA, production, etx)
         * @see EnvironmentLevel
         */
        Level,
        /**
         * The full name for the environment
         */
        Name,
        /**
         * The reference code for the environment
         */
        Code
    }

    ;

    /**
     * Returns the value for a requested property, identified by the property argument.  Null will be returned if the
     * requested property is not known for this environment.
     *
     * @return the value of the specified property, null if the value is not known.
     */
    public Object getValue(Environment.Property property);

}
