package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DomandaTest {

    private Domanda domanda;

    @BeforeEach
    public void setUp() {
        // Inizializza una domanda per ogni test
        domanda = new Domanda(1, "Messaggio di esempio", LocalDateTime.now(), true, "utente@example.com");
    }

    @Test
    public void testCostruttoreConParametri() {
        // Assert: Verifica che il costruttore imposti correttamente i valori
        assertEquals(1, domanda.getId(), "ID non corrisponde.");
        assertEquals("Messaggio di esempio", domanda.getMessaggio(), "Messaggio non corrisponde.");
        assertNotNull(domanda.getDataRicezione(), "Data ricezione non dovrebbe essere nulla.");
        assertTrue(domanda.isStato(), "Stato non corrisponde.");
        assertEquals("utente@example.com", domanda.getEmailUtente(), "Email utente non corrisponde.");
    }

    @Test
    public void testGetterSetter() {
        // Act: Imposta nuovi valori tramite i setter
        domanda.setId(2);
        domanda.setMessaggio("Nuovo messaggio");
        domanda.setDataRicezione(LocalDateTime.now().plusDays(1));
        domanda.setStato(false);
        domanda.setEmailUtente("nuovo@utente.com");

        // Assert: Verifica che i getter restituiscano i nuovi valori
        assertEquals(2, domanda.getId(), "ID non corrisponde.");
        assertEquals("Nuovo messaggio", domanda.getMessaggio(), "Messaggio non corrisponde.");
        assertNotNull(domanda.getDataRicezione(), "Data ricezione non dovrebbe essere nulla.");
        assertFalse(domanda.isStato(), "Stato non corrisponde.");
        assertEquals("nuovo@utente.com", domanda.getEmailUtente(), "Email utente non corrisponde.");
    }

    @Test
    public void testToString() {
        // Act: Ottieni la rappresentazione in formato stringa
        String domandaString = domanda.toString();

        // Assert: Verifica che la rappresentazione contenga tutte le informazioni
        assertTrue(domandaString.contains("id=1"), "Il toString non contiene l'ID.");
        assertTrue(domandaString.contains("Messaggio di esempio"), "Il toString non contiene il messaggio.");
        assertTrue(domandaString.contains("utente@example.com"), "Il toString non contiene l'email.");
        assertTrue(domandaString.contains("stato=true"), "Il toString non contiene lo stato.");
    }
}
