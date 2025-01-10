package Autenticazione;

import Model.Utente;
import Model.UtenteDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckLoginTest {

    @Test
    public void testLoginSuccess() {
        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Utente utente = utenteDAO.findUtenteByEmailAndPassword("mario.rossi@email.com", "password123");
            assertNotNull(utente, "L'utente dovrebbe essere autenticato.");
            assertEquals("utente", utente.getRuolo(), "Ruolo utente non corretto.");
        } catch (Exception e) {
            fail("Errore durante il test: " + e.getMessage());
        }
    }

    @Test
    public void testLoginEmailNonEsistente() {
        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Utente utente = utenteDAO.findUtenteByEmailAndPassword("mario.rossEEi@email.com", "password123");
            assertNull(utente, "L'utente non dovrebbe esistere nel sistema.");
        } catch (Exception e) {
            fail("Errore durante il test: " + e.getMessage());
        }
    }

    @Test
    public void testLoginPasswordErrata() {
        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Utente utente = utenteDAO.findUtenteByEmailAndPassword("mario.rossi@email.com", "passwordErrata!");
            assertNull(utente, "La password è errata, il login dovrebbe fallire.");
        } catch (Exception e) {
            fail("Errore durante il test: " + e.getMessage());
        }
    }

    @Test
    public void testLoginBarista() {
        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Utente utente = utenteDAO.findUtenteByEmailAndPassword("giuseppe.verdi@email.com", "password789");
            assertNotNull(utente, "Il barista dovrebbe essere autenticato.");
            assertEquals("barista", utente.getRuolo(), "Il ruolo dovrebbe essere barista.");
        } catch (Exception e) {
            fail("Errore durante il test: " + e.getMessage());
        }
    }

    @Test
    public void testLoginAdmin() {
        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Utente utente = utenteDAO.findUtenteByEmailAndPassword("lucia.bianchi@email.com", "password456");
            assertNotNull(utente, "L'admin dovrebbe essere autenticato.");
            assertEquals("admin", utente.getRuolo(), "Il ruolo dovrebbe essere admin.");
        } catch (Exception e) {
            fail("Errore durante il test: " + e.getMessage());
        }
    }
}
