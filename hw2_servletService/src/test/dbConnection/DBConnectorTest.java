package test.dbConnection;

import main.dbConnection.DBConnector;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBConnectorTest {

    private DBConnector createSampleDBConnector() {
        return new DBConnector();
    }

    @Test
    public void testGetUrl() {
        DBConnector dbConnector = createSampleDBConnector();
        assertEquals("jdbc:postgresql://localhost:5432/", dbConnector.getUrl());
    }

    @Test
    public void testGetUser() {
        DBConnector dbConnector = createSampleDBConnector();
        assertEquals("postgres", dbConnector.getUser());
    }

    @Test
    public void testGetPassword() {
        DBConnector dbConnector = createSampleDBConnector();
        assertEquals("postgres", dbConnector.getPassword());
    }

    @Test
    public void testGetDbName() {
        DBConnector dbConnector = createSampleDBConnector();
        assertEquals("library", dbConnector.getDbName());
    }

    @Test
    public void testGetDriver() {
        DBConnector dbConnector = createSampleDBConnector();
        assertEquals("org.postgresql.Driver", dbConnector.getDriver());
    }
}

