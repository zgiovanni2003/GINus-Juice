package Model;

import Model.ConnectionPool;
import Model.Recensione;
import Model.RecensioneDAO;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecensioneDAOTest {

    private RecensioneDAO recensioneDAO;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;
    private MockedStatic<ConnectionPool> connectionPoolMockStatic;

    @BeforeEach
    void setUp() throws SQLException {
        recensioneDAO = new RecensioneDAO();

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
    void testGetRecensioniByIdProdotto() throws SQLException {
        // Configurazione del mock per il ResultSet
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, false); // Una riga disponibile
        when(resultSetMock.getString("email_ut")).thenReturn("utente@test.com");
        when(resultSetMock.getString("corpo_recensione")).thenReturn("Recensione di test");
        when(resultSetMock.getDate("data_rec")).thenReturn(Date.valueOf("2025-01-01"));

        // Esegui il metodo
        List<Recensione> recensioni = recensioneDAO.getRecensioniByIdProdotto(1);

        // Verifica
        assertNotNull(recensioni);
        assertEquals(1, recensioni.size());
        Recensione recensione = recensioni.get(0);
        assertEquals("utente@test.com", recensione.getEmailUtente());
        assertEquals("Recensione di test", recensione.getCorpoRecensione());
        assertEquals(Date.valueOf("2025-01-01"), recensione.getDataRecensione());
        assertEquals(1, recensione.getIdProdotto());
    }

    @Test
    void testInserisciRecensione() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        boolean risultato = recensioneDAO.inserisciRecensione("utente@test.com", 1, "Recensione di test");

        // Verifica
        assertTrue(risultato);
        verify(preparedStatementMock).setString(1, "utente@test.com");
        verify(preparedStatementMock).setInt(2, 1);
        verify(preparedStatementMock).setString(3, "Recensione di test");
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testInserisciRecensioneFallimento() throws SQLException {
        // Configurazione del PreparedStatement mock per fallimento
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(0);

        // Esegui il metodo
        boolean risultato = recensioneDAO.inserisciRecensione("utente@test.com", 1, "Recensione di test");

        // Verifica
        assertFalse(risultato);
        verify(preparedStatementMock).setString(1, "utente@test.com");
        verify(preparedStatementMock).setInt(2, 1);
        verify(preparedStatementMock).setString(3, "Recensione di test");
        verify(preparedStatementMock).executeUpdate();
    }
}
