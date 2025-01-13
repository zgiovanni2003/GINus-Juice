package Model;

import Model.Utente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtenteTest {

    @Test
    void testCostruttoreConParametri() {
        // Setup
        String email = "utente@test.com";
        String nome = "Mario";
        String cognome = "Rossi";
        String password = "password123";
        boolean stato = true;
        String ruolo = "utente";

        // Esegui
        Utente utente = new Utente(email, nome, cognome, password, stato, ruolo);

        // Verifica
        assertEquals(email, utente.getEmail());
        assertEquals(nome, utente.getNome());
        assertEquals(cognome, utente.getCognome());
        assertEquals(password, utente.getPassword());
        assertTrue(utente.isStato());
        assertEquals(ruolo, utente.getRuolo());
    }

    @Test
    void testGetterESetter() {
        // Setup
        Utente utente = new Utente();

        String email = "admin@test.com";
        String nome = "Admin";
        String cognome = "Boss";
        String password = "admin123";
        boolean stato = false;
        String ruolo = "admin";

        // Esegui
        utente.setEmail(email);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setPassword(password);
        utente.setStato(stato);
        utente.setRuolo(ruolo);

        // Verifica
        assertEquals(email, utente.getEmail());
        assertEquals(nome, utente.getNome());
        assertEquals(cognome, utente.getCognome());
        assertEquals(password, utente.getPassword());
        assertFalse(utente.isStato());
        assertEquals(ruolo, utente.getRuolo());
    }

    @Test
    void testToString() {
        // Setup
        String email = "utente@test.com";
        String nome = "Mario";
        String cognome = "Rossi";
        String password = "password123";
        boolean stato = true;
        String ruolo = "utente";

        Utente utente = new Utente(email, nome, cognome, password, stato, ruolo);

        // Esegui
        String toStringResult = utente.toString();

        // Verifica
        String expected = "Utente{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", password='" + password + '\'' +
                ", stato=" + stato +
                ", ruolo='" + ruolo + '\'' +
                '}';
        assertEquals(expected, toStringResult);
    }

    @Test
    void testEquals() {
        // Setup
        Utente utente1 = new Utente("utente@test.com", "Mario", "Rossi", "password123", true, "utente");
        Utente utente2 = new Utente("utente@test.com", "Mario", "Rossi", "password123", true, "utente");
        Utente utente3 = new Utente("admin@test.com", "Admin", "Boss", "admin123", false, "admin");

        // Verifica
        assertEquals(utente1, utente2, "Gli oggetti con gli stessi valori dovrebbero essere uguali");
        assertNotEquals(utente1, utente3, "Gli oggetti con valori diversi non dovrebbero essere uguali");
    }

    @Test
    void testHashCode() {
        // Setup
        Utente utente1 = new Utente("utente@test.com", "Mario", "Rossi", "password123", true, "utente");
        Utente utente2 = new Utente("utente@test.com", "Mario", "Rossi", "password123", true, "utente");
        Utente utente3 = new Utente("admin@test.com", "Admin", "Boss", "admin123", false, "admin");

        // Verifica
        assertEquals(utente1.hashCode(), utente2.hashCode(), "Gli oggetti con gli stessi valori dovrebbero avere lo stesso hashCode");
        assertNotEquals(utente1.hashCode(), utente3.hashCode(), "Gli oggetti con valori diversi non dovrebbero avere lo stesso hashCode");
    }
}
