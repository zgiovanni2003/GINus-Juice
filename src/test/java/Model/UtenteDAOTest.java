package Model;

import Model.ConnectionPool;
import Model.Utente;
import Model.UtenteDAO;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtenteDAOTest {

    private UtenteDAO utenteDAO;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;
    private MockedStatic<ConnectionPool> connectionPoolMockStatic;

    @BeforeEach
    void setUp() throws SQLException {
        utenteDAO = new UtenteDAO();

        // Mock degli oggetti JDBC
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);

        // Mock statico per ConnectionPool
        connectionPoolMockStatic = mockStatic(ConnectionPool.class);
        connectionPoolMockStatic.when(ConnectionPool::getConnection).thenReturn(connectionMock);
    }

    @AfterEach
    void tearDown() {
        // Chiude il mock statico
        connectionPoolMockStatic.close();
    }

    @Test
    void testFindUtenteByEmailAndPassword() throws SQLException {
        // Configurazione del ResultSet mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getString("email")).thenReturn("utente@test.com");
        when(resultSetMock.getString("nome")).thenReturn("Mario");
        when(resultSetMock.getString("cognome")).thenReturn("Rossi");
        when(resultSetMock.getString("password_d")).thenReturn("password123");
        when(resultSetMock.getBoolean("stato")).thenReturn(true);
        when(resultSetMock.getString("ruolo")).thenReturn("utente");

        // Esegui il metodo
        Utente utente = utenteDAO.findUtenteByEmailAndPassword("utente@test.com", "password123");

        // Verifica
        assertNotNull(utente);
        assertEquals("utente@test.com", utente.getEmail());
        assertEquals("Mario", utente.getNome());
        assertEquals("Rossi", utente.getCognome());
        assertEquals("password123", utente.getPassword());
        assertTrue(utente.isStato());
        assertEquals("utente", utente.getRuolo());
    }

    @Test
    void testRegisterUser() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        boolean risultato = utenteDAO.registerUser("nuovo@test.com", "Luigi", "Verdi", "password456", true, "utente");

        // Verifica
        assertTrue(risultato);
        verify(preparedStatementMock).setString(1, "nuovo@test.com");
        verify(preparedStatementMock).setString(2, "Luigi");
        verify(preparedStatementMock).setString(3, "Verdi");
        verify(preparedStatementMock).setString(4, "password456");
        verify(preparedStatementMock).setBoolean(5, true);
        verify(preparedStatementMock).setString(6, "utente");
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testDeleteUserByEmail() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        utenteDAO.deleteUserByEmail("utente@test.com");

        // Verifica
        verify(preparedStatementMock).setString(1, "utente@test.com");
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testAggiornaRuolo() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        boolean risultato = utenteDAO.aggiornaRuolo("utente@test.com", "admin");

        // Verifica
        assertTrue(risultato);
        verify(preparedStatementMock).setString(1, "admin");
        verify(preparedStatementMock).setString(2, "utente@test.com");
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testGetAllUtenti() throws SQLException {
        // Configurazione del ResultSet mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

        when(resultSetMock.next()).thenReturn(true, false); // Una riga disponibile
        when(resultSetMock.getString("email")).thenReturn("utente@test.com");
        when(resultSetMock.getString("nome")).thenReturn("Mario");
        when(resultSetMock.getString("cognome")).thenReturn("Rossi");
        when(resultSetMock.getString("password_d")).thenReturn("password123");
        when(resultSetMock.getBoolean("stato")).thenReturn(true);
        when(resultSetMock.getString("ruolo")).thenReturn("utente");

        // Esegui il metodo
        List<Utente> utenti = utenteDAO.getAllUtenti();

        // Verifica
        assertNotNull(utenti);
        assertEquals(1, utenti.size());
        Utente utente = utenti.get(0);
        assertEquals("utente@test.com", utente.getEmail());
        assertEquals("Mario", utente.getNome());
        assertEquals("Rossi", utente.getCognome());
        assertEquals("password123", utente.getPassword());
        assertTrue(utente.isStato());
        assertEquals("utente", utente.getRuolo());
    }
}
