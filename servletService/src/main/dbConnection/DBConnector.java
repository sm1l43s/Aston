package main.dbConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that represents a database connector that reads the connection properties from a file.
 */
public class DBConnector {

    // The name of the properties file
    private static final String PROPERTIES_FILE = "D:\\TestProjects\\Aston\\servletService\\src\\database.properties";
    // The keys for the properties
    private static final String DRIVER_KEY = "db.driver";
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final String NAME_KEY = "db.name";

    // The fields for the connection properties
    private final String driver;
    private final String url;
    private final String user;
    private final String password;
    private final String dbName;

    /**
     * Constructs a new DBConnector object by reading the properties file and initializing the fields.
     */
    public DBConnector() {
        Properties properties = readPropertiesFile(PROPERTIES_FILE);
        this.driver = properties.getProperty(DRIVER_KEY);
        this.url = properties.getProperty(URL_KEY);
        this.user = properties.getProperty(USER_KEY);
        this.password = properties.getProperty(PASSWORD_KEY);
        this.dbName = properties.getProperty(NAME_KEY);
    }

    /**
     * Reads a properties file and returns a Properties object with the key-value pairs.
     * @param fileName The name of the file to read from.
     * @return A Properties object with the key-value pairs from the file.
     * @throws RuntimeException If an IOException occurs while reading the file.
     */
    private Properties readPropertiesFile(String fileName) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);
        } catch (IOException e) {

            Logger logger = Logger.getLogger(DBConnector.class.getName());
            logger.log(Level.SEVERE, "Error reading file property", e);
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * Returns the URL of the database.
     * @return The URL of the database.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the user name of the database.
     * @return The user name of the database.
     */
    public String getUser() {
        return user;
    }

    /**
     * Returns the password of the database.
     * @return The password of the database.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the name of the database.
     * @return The name of the database.
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * Returns the driver of the database.
     * @return The driver of the database.
     */
    public String getDriver() {
        return driver;
    }
}


