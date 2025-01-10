package Model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionPoolTest {

    private ConnectionPool connectionPool;

    @BeforeEach
    public void setUp() {
        // Inizializza ConnectionPool prima di ogni test
        connectionPool = new ConnectionPool();
    }

    @Test
    public void testGetConnection() throws SQLException {
        // Act: Ottieni una connessione dal pool
        Connection connection = connectionPool.getConnection();

        // Assert: Verifica che la connessione non sia null
        assertNotNull(connection, "La connessione non dovrebbe essere nulla.");
        assertFalse(connection.isClosed(), "La connessione non dovrebbe essere chiusa.");
    }

    @Test
    public void testReleaseConnection() throws SQLException {
        // Act: Ottieni una connessione dal pool
        Connection connection = connectionPool.getConnection();

        // Assert: Verifica che la connessione non sia nulla e non sia chiusa
        assertNotNull(connection);
        assertFalse(connection.isClosed());

        // Act: Rilascia la connessione
        connectionPool.releaseConnection(connection);

        // Verifica che la connessione possa essere riutilizzata
        Connection reusedConnection = connectionPool.getConnection();
        assertNotNull(reusedConnection);
        assertFalse(reusedConnection.isClosed(), "La connessione rilasciata dovrebbe essere riutilizzata.");
    }

    @Test
    public void testGetConnectionWithClosedConnection() throws SQLException {
        // Act: Ottieni una connessione dal pool
        Connection connection = connectionPool.getConnection();

        // Simula la chiusura della connessione
        connection.close();

        // Act: Richiedi una nuova connessione
        Connection newConnection = connectionPool.getConnection();

        // Assert: Verifica che la nuova connessione sia valida
        assertNotNull(newConnection, "La nuova connessione non dovrebbe essere nulla.");
        assertFalse(newConnection.isClosed(), "La nuova connessione non dovrebbe essere chiusa.");
    }
}
