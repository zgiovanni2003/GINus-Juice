package Model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.sql.*;

class DomandaDaoTest {

    private DomandaDAO domandaDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    void setUp() throws SQLException {
        // Creazione dei mock per la connessione e PreparedStatement
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);

        // Mock della classe ConnectionPool
        try (MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)) {
            // Configura il comportamento del mock per ConnectionPool.getConnection()
            mocked.when(ConnectionPool::getConnection).thenReturn(mockConnection);

            // Simula la preparazione della statement
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

            // Crea un'istanza di DomandaDAO che userà ConnectionPool mockato
            domandaDAO = new DomandaDAO();
        }
    }

    @Test
    void testInserisciDomanda() throws SQLException {
        // Dati per il test
        String messaggio = "Questo è un messaggio di prova";
        String emailUtente = "mario.rossi@email.com";

        // Simula l'esecuzione dell'update e restituisci un valore positivo
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Testa il metodo
        boolean risultato = domandaDAO.inserisciDomanda(messaggio, emailUtente);

        // Verifica se il risultato è true
        assertTrue(risultato, "La domanda dovrebbe essere inserita correttamente.");

        // Verifica che i metodi siano stati chiamati
        verify(mockPreparedStatement).setString(1, messaggio);
        verify(mockPreparedStatement).setString(2, emailUtente);
        verify(mockPreparedStatement).executeUpdate();
    }
}
