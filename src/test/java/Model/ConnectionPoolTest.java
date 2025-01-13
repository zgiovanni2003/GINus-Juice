package Test;

import Model.ConnectionPool;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @BeforeAll
    static void setup() {
        System.out.println("Inizio dei test per ConnectionPool");
    }

    @Test
    void testGetConnection() {
        try {
            // Recupera una connessione dal pool
            Connection connection = ConnectionPool.getConnection();

            // Verifica che la connessione non sia null
            assertNotNull(connection, "La connessione non dovrebbe essere null");

            // Verifica che la connessione sia valida
            assertTrue(connection.isValid(2), "La connessione dovrebbe essere valida");

            // Rilascia la connessione al pool
            ConnectionPool.releaseConnection(connection);

        } catch (SQLException e) {
            fail("Eccezione durante il recupero della connessione: " + e.getMessage());
        }
    }

    @Test
    void testReleaseConnection() {
        try {
            // Recupera una connessione dal pool
            Connection connection = ConnectionPool.getConnection();

            // Rilascia la connessione al pool
            ConnectionPool.releaseConnection(connection);

            // Verifica che la connessione sia stata rilasciata
            assertDoesNotThrow(() -> ConnectionPool.getConnection(), "Non dovrebbe lanciare eccezioni");

        } catch (SQLException e) {
            fail("Eccezione durante il rilascio della connessione: " + e.getMessage());
        }
    }

    @Test
    void testConnectionReuse() {
        try {
            // Recupera una connessione dal pool
            Connection connection1 = ConnectionPool.getConnection();
            ConnectionPool.releaseConnection(connection1);

            // Recupera di nuovo una connessione
            Connection connection2 = ConnectionPool.getConnection();

            // Verifica che le connessioni siano uguali (riutilizzo)
            assertSame(connection1, connection2, "La connessione dovrebbe essere riutilizzata");

        } catch (SQLException e) {
            fail("Eccezione durante il riutilizzo della connessione: " + e.getMessage());
        }
    }

    @AfterAll
    static void teardown() {
        System.out.println("Fine dei test per ConnectionPool");
    }
}
