package Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AcquistoDAOTest {

    private AcquistoDAO acquistoDAO;

    @BeforeEach
    public void setUp() {
        acquistoDAO = new AcquistoDAO();
        resetDatabase();
    }

    @AfterEach
    public void tearDown() {
        resetDatabase();
    }

    private void resetDatabase() {
        try (Connection connection = ConnectionPool.getConnection()) {
            // Pulisci la tabella acquisto
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM acquisto")) {
                ps.executeUpdate();
            }

            // Pulisci la tabella utente
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM utente")) {
                ps.executeUpdate();
            }

            // Reinserisci un utente di test
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO utente (email, nome, cognome, password_d, stato, ruolo) VALUES (?, ?, ?, ?, ?, ?)")) {
                ps.setString(1, "mario.rossi@email.com");
                ps.setString(2, "Mario");
                ps.setString(3, "Rossi");
                ps.setString(4, "password123");
                ps.setBoolean(5, true);
                ps.setString(6, "utente");
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSalvaAcquisto() {
        Acquisto acquisto = new Acquisto();
        acquisto.setPrezzo(50.0);
        acquisto.setProdottiComprati("Gin Premium, Whisky Classico");
        acquisto.setEmailUtente("mario.rossi@email.com");

        boolean result = acquistoDAO.salvaAcquisto(acquisto);

        assertTrue(result, "Il salvataggio dell'acquisto dovrebbe avere successo.");
    }

    @Test
    public void testGetAcquistiByEmail() {
        // Inserire un acquisto di test
        Acquisto acquisto = new Acquisto();
        acquisto.setPrezzo(50.0);
        acquisto.setProdottiComprati("Gin Premium, Whisky Classico");
        acquisto.setEmailUtente("mario.rossi@email.com");
        acquistoDAO.salvaAcquisto(acquisto);

        // Recuperare gli acquisti
        List<Acquisto> acquisti = acquistoDAO.getAcquistiByEmail("mario.rossi@email.com");

        assertNotNull(acquisti, "La lista degli acquisti non dovrebbe essere null.");
        assertEquals(1, acquisti.size(), "La lista degli acquisti dovrebbe contenere un elemento.");
        assertEquals("Gin Premium, Whisky Classico", acquisti.get(0).getProdottiComprati(), "I prodotti acquistati dovrebbero corrispondere.");
        assertEquals(50.0, acquisti.get(0).getPrezzo(), "Il prezzo dovrebbe corrispondere.");
    }
}
