package main.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool instance;
    private String url;
    private String username;
    private String password;
    private int maxConnections;
    private List<Connection> freeConnections;

    private ConnectionPool() {
        DBConnector db = new DBConnector("D:\\TestProjects\\Aston\\servletService\\src\\database.properties");
        url = db.getUrl() + db.getDbName();
        username = db.getUser();
        password = db.getPassword();
        maxConnections = 10;

        freeConnections = new ArrayList<>();

        for (int i = 0; i < maxConnections; i++) {
            Connection conn = createConnection();
            if (conn != null) {
                freeConnections.add(conn);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        // Возвращаем экземпляр
        return instance;
    }

    public Connection getConnection() {
        if (!freeConnections.isEmpty()) {
            return freeConnections.remove(0);
        }
        return null;
    }

    public void releaseConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    freeConnections.add(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        for (Connection conn : freeConnections) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        freeConnections.clear();
    }

    private Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
