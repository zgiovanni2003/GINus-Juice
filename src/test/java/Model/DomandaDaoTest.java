package Model;

import Model.ConnectionPool;
import Model.Domanda;
import Model.DomandaDAO;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DomandaDAOTest {

    private DomandaDAO domandaDAO;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;
    private MockedStatic<ConnectionPool> connectionPoolMockStatic;

    @BeforeEach
    void setUp() throws SQLException {
        // Inizializza la classe da testare
        domandaDAO = new DomandaDAO();

        // Mock degli oggetti JDBC
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);

        // Mock statico di ConnectionPool
        connectionPoolMockStatic = mockStatic(ConnectionPool.class);
        connectionPoolMockStatic.when(ConnectionPool::getConnection).thenReturn(connectionMock);
    }

    @AfterEach
    void tearDown() {
        // Chiude il mock statico
        connectionPoolMockStatic.close();
    }

    @Test
    void testInserisciDomanda_Successo() throws SQLException {
        String messaggio = "Messaggio di Test";
        String emailUtente = "utente@test.com";

        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        boolean risultato = domandaDAO.inserisciDomanda(messaggio, emailUtente);

        // Verifica
        assertTrue(risultato, "La domanda dovrebbe essere inserita con successo");
        verify(preparedStatementMock).setString(1, messaggio);
        verify(preparedStatementMock).setString(2, emailUtente);
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testInserisciDomanda_Fallimento() throws SQLException {
        String messaggio = "Messaggio di Test";
        String emailUtente = "utente@test.com";

        // Configurazione del PreparedStatement mock per fallimento
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(0);

        // Esegui il metodo
        boolean risultato = domandaDAO.inserisciDomanda(messaggio, emailUtente);

        // Verifica
        assertFalse(risultato, "La domanda non dovrebbe essere inserita");
        verify(preparedStatementMock).setString(1, messaggio);
        verify(preparedStatementMock).setString(2, emailUtente);
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testGetDomandeNonRisposte() throws SQLException {
        // Configurazione del ResultSet mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, false); // Una riga disponibile
        when(resultSetMock.getInt("id_domanda")).thenReturn(1);
        when(resultSetMock.getString("corpo_messaggio")).thenReturn("Messaggio non risposto");
        when(resultSetMock.getString("email_utente")).thenReturn("utente@test.com");
        when(resultSetMock.getBoolean("stato")).thenReturn(false);

        // Esegui il metodo
        List<Domanda> domande = domandaDAO.getDomandeNonRisposte();

        // Verifica
        assertNotNull(domande, "La lista di domande non dovrebbe essere null");
        assertEquals(1, domande.size(), "La lista dovrebbe contenere una domanda");
        Domanda domanda = domande.get(0);
        assertEquals(1, domanda.getId());
        assertEquals("Messaggio non risposto", domanda.getMessaggio());
        assertEquals("utente@test.com", domanda.getEmailUtente());
        assertFalse(domanda.isStato());

        verify(preparedStatementMock).executeQuery();
        verify(resultSetMock, times(2)).next();
    }
}
