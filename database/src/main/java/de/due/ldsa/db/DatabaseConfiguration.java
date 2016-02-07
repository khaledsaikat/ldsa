package de.due.ldsa.db;

/**
 * @author scrobart
 * Contains the Database configuration.
 */
public final class DatabaseConfiguration {
    static {

    }

    /**
     * Gets the Database IP
     *
     * @return The Database IP as String.
     */
    public static String getIP() {
        // TODO: don't hardcode "127.0.0.1" - make this loadable from somewhere...
        return "127.0.0.1";
    }
}
