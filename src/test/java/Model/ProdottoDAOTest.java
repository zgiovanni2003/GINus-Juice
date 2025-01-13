package Model;

import Model.ConnectionPool;
import Model.Prodotto;
import Model.ProdottoDAO;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdottoDAOTest {

    private ProdottoDAO prodottoDAO;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;
    private MockedStatic<ConnectionPool> connectionPoolMockStatic;

    @BeforeEach
    void setUp() throws SQLException {
        prodottoDAO = new ProdottoDAO();

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
    void testGetAllProdotti() throws SQLException {
        // Configurazione del mock per il ResultSet
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, false); // Una riga disponibile
        when(resultSetMock.getInt("id_prodotto")).thenReturn(1);
        when(resultSetMock.getString("nome")).thenReturn("Gin Premium");
        when(resultSetMock.getDouble("prezzo")).thenReturn(25.99);
        when(resultSetMock.getInt("quantita")).thenReturn(50);

        // Esegui il metodo
        List<Prodotto> prodotti = prodottoDAO.getAllProdotti();

        // Verifica
        assertNotNull(prodotti);
        assertEquals(1, prodotti.size());
        Prodotto prodotto = prodotti.get(0);
        assertEquals(1, prodotto.getId());
        assertEquals("Gin Premium", prodotto.getNome());
        assertEquals(25.99, prodotto.getPrezzo());
        assertEquals(50, prodotto.getQuantita());
    }

    @Test
    void testGetProdottoById() throws SQLException {
        // Configurazione del mock per il ResultSet
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("id_prodotto")).thenReturn(2);
        when(resultSetMock.getString("nome")).thenReturn("Whisky Classico");
        when(resultSetMock.getDouble("prezzo")).thenReturn(35.50);
        when(resultSetMock.getString("descrizione")).thenReturn("Whisky di alta qualità");
        when(resultSetMock.getInt("quantita")).thenReturn(30);

        // Esegui il metodo
        Prodotto prodotto = prodottoDAO.getProdottoById(2);

        // Verifica
        assertNotNull(prodotto);
        assertEquals(2, prodotto.getId());
        assertEquals("Whisky Classico", prodotto.getNome());
        assertEquals(35.50, prodotto.getPrezzo());
        assertEquals("Whisky di alta qualità", prodotto.getDescrizione());
        assertEquals(30, prodotto.getQuantita());
    }

    @Test
    void testAggiungiProdotto() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Esegui il metodo
        Prodotto prodotto = new Prodotto(0, "Rum Scuro", 18.75, "Rum speziato", 20);
        prodottoDAO.aggiungiProdotto(prodotto);

        // Verifica
        verify(preparedStatementMock).setString(1, "Rum Scuro");
        verify(preparedStatementMock).setString(2, "Rum speziato");
        verify(preparedStatementMock).setDouble(3, 18.75);
        verify(preparedStatementMock).setInt(4, 20);
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testDeleteProdottoById() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        boolean risultato = prodottoDAO.deleteProdottoById(1);

        // Verifica
        assertTrue(risultato);
        verify(preparedStatementMock).setInt(1, 1);
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testUpdateProdotto() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        Prodotto prodotto = new Prodotto(1, "Vodka Premium", 22.99, "Vodka di alta qualità", 25);
        boolean risultato = prodottoDAO.updateProdotto(prodotto);

        // Verifica
        assertTrue(risultato);
        verify(preparedStatementMock).setString(1, "Vodka Premium");
        verify(preparedStatementMock).setDouble(2, 22.99);
        verify(preparedStatementMock).setString(3, "Vodka di alta qualità");
        verify(preparedStatementMock).setInt(4, 25);
        verify(preparedStatementMock).setInt(5, 1);
        verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void testAggiornaQuantitaProdotto() throws SQLException {
        // Configurazione del PreparedStatement mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        // Esegui il metodo
        boolean risultato = prodottoDAO.aggiornaQuantitaProdotto(1, 5);

        // Verifica
        assertTrue(risultato);
        verify(preparedStatementMock).setInt(1, 5);
        verify(preparedStatementMock).setInt(2, 1);
        verify(preparedStatementMock).setInt(3, 5);
        verify(preparedStatementMock).executeUpdate();
    }
}
