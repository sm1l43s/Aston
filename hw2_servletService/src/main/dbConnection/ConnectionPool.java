package main.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that represents a connection pool for accessing a database.
 * It creates a fixed number of connections and manages them in a list.
 * It provides methods to get, release, and close the connections.
 * It uses a singleton design pattern to ensure only one instance of the class exists.
 */
public class ConnectionPool {

    private static ConnectionPool instance; // the single instance of the class
    private String driver;
    private String url;
    private String username;
    private String password;
    private int maxConnections;
    private List<Connection> freeConnections;

    /**
     * A static block that initializes the single instance of the class.
     */
    static {
        instance = new ConnectionPool();
    }

    /**
     * A private constructor that creates and fills the list of connections.
     * It reads the database configuration from a DBConnector object and loads the driver class.
     * It throws a RuntimeException if the driver class is not found or the connection cannot be created.
     */
    private ConnectionPool() {
        DBConnector db = new DBConnector();
        driver = db.getDriver();
        url = db.getUrl() + db.getDbName();
        username = db.getUser();
        password = db.getPassword();
        maxConnections = 20;

        freeConnections = new ArrayList<>();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }

        for (int i = 0; i < maxConnections; i++) {
            Connection conn = createConnection();
            if (conn != null) {
                freeConnections.add(conn);
            }
        }
    }

    /**
     * A public static method that returns the single instance of the class.
     * @return the instance of ConnectionPool
     */
    public static ConnectionPool getInstance() {
        return instance;
    }

    /**
     * A public method that returns a connection from the list if available, or null otherwise.
     * @return a Connection object or null
     */
    public Connection getConnection() {
        if (!freeConnections.isEmpty()) {
            return freeConnections.remove(0);
        }
        return null;
    }

    /**
     * A public method that adds a connection back to the list if it is not closed.
     * @param conn a Connection object to be released
     */
    public void releaseConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    freeConnections.add(conn);
                }
            } catch (SQLException e) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.WARNING, e.getMessage(), e);
            }
        }
    }

    /**
     * A public method that closes all the connections in the list and clears it.
     */
    public void close() {
        for (Connection conn : freeConnections) {
            try (conn) {
            } catch (SQLException e) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.WARNING, e.getMessage(), e);
            }
        }
        freeConnections.clear();
    }

    /**
     * A private method that creates a connection using the database configuration.
     * @return a Connection object or null
     */
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}

