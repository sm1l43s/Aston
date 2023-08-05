package main.dbConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnector {

    private final String driver;
    private final String url;
    private final String user;
    private final String password;
    private final String dbName;

    public DBConnector(String pathToFileProperties) {

        Properties properties = new Properties();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(pathToFileProperties);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String dbName = properties.getProperty("db.name");

        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDriver() {
        return driver;
    }
}
