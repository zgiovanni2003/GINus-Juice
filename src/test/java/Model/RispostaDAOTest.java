package Model;

import Model.ConnectionPool;
import Model.Risposta;
import Model.RispostaDAO;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RispostaDAOTest {

    private RispostaDAO rispostaDAO;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;
    private MockedStatic<ConnectionPool> connectionPoolMockStatic;

    @BeforeEach
    void setUp() throws SQLException {
        rispostaDAO = new RispostaDAO();

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
    void testInserisciRisposta() throws SQLException {
        // Configurazione dei PreparedStatement mock
        PreparedStatement insertStmtMock = mock(PreparedStatement.class);
        PreparedStatement updateStmtMock = mock(PreparedStatement.class);

        when(connectionMock.prepareStatement("INSERT INTO risposta (corpo_messaggio, email_barista) VALUES (?, ?)"))
                .thenReturn(insertStmtMock);
        when(connectionMock.prepareStatement("UPDATE domanda SET stato = TRUE WHERE id_domanda = ?"))
                .thenReturn(updateStmtMock);

        when(insertStmtMock.executeUpdate()).thenReturn(1);
        when(updateStmtMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        boolean risultato = rispostaDAO.inserisciRisposta("Risposta di test", 1, "barista@test.com");

        // Verifica
        assertTrue(risultato);
        verify(insertStmtMock).setString(1, "Risposta di test");
        verify(insertStmtMock).setString(2, "barista@test.com");
        verify(insertStmtMock).executeUpdate();

        verify(updateStmtMock).setInt(1, 1);
        verify(updateStmtMock).executeUpdate();
    }

    @Test
    void testGetRisposteByEmailUtente() throws SQLException {
        // Configurazione del ResultSet mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

        when(resultSetMock.next()).thenReturn(true, false); // Una riga disponibile
        when(resultSetMock.getInt("id_risposta")).thenReturn(1);
        when(resultSetMock.getString("corpo_messaggio")).thenReturn("Risposta di test");
        when(resultSetMock.getTimestamp("data_rec"))
                .thenReturn(java.sql.Timestamp.valueOf(LocalDateTime.of(2025, 1, 1, 12, 0)));
        when(resultSetMock.getString("email_barista")).thenReturn("barista@test.com");

        // Esegui il metodo
        List<Risposta> risposte = rispostaDAO.getRisposteByEmailUtente("utente@test.com");

        // Verifica
        assertNotNull(risposte);
        assertEquals(1, risposte.size());
        Risposta risposta = risposte.get(0);
        assertEquals(1, risposta.getId());
        assertEquals("Risposta di test", risposta.getMessaggio());
        assertEquals(LocalDateTime.of(2025, 1, 1, 12, 0), risposta.getDataRicezione());
        assertEquals("barista@test.com", risposta.getEmailBarista());
    }
}
