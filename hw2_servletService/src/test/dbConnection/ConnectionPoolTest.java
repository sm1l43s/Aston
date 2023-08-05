package test.dbConnection;

import main.dbConnection.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionPoolTest {

    private ConnectionPool pool;

    @Before
    public void setUp() {
        pool = ConnectionPool.getInstance();
    }

    @After
    public void tearDown() {
        pool.close();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(pool);
        assertSame(pool, ConnectionPool.getInstance());
    }

    @Test
    public void testGetConnection() {
        Connection conn = pool.getConnection();
        assertNotNull(conn);
        try {
            assertFalse(conn.isClosed());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}

