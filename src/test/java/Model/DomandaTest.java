package Model;

import Model.Domanda;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DomandaTest {

    @Test
    void testCostruttoreConParametri() {
        // Setup
        int id = 1;
        String messaggio = "Test messaggio";
        LocalDateTime dataRicezione = LocalDateTime.now();
        boolean stato = false;
        String emailUtente = "utente@test.com";

        // Esegui
        Domanda domanda = new Domanda(id, messaggio, dataRicezione, stato, emailUtente);

        // Verifica
        assertEquals(id, domanda.getId());
        assertEquals(messaggio, domanda.getMessaggio());
        assertEquals(dataRicezione, domanda.getDataRicezione());
        assertEquals(stato, domanda.isStato());
        assertEquals(emailUtente, domanda.getEmailUtente());
    }

    @Test
    void testGetterESetter() {
        // Setup
        Domanda domanda = new Domanda();

        int id = 2;
        String messaggio = "Altro messaggio";
        LocalDateTime dataRicezione = LocalDateTime.now();
        boolean stato = true;
        String emailUtente = "altro@test.com";

        // Esegui
        domanda.setId(id);
        domanda.setMessaggio(messaggio);
        domanda.setDataRicezione(dataRicezione);
        domanda.setStato(stato);
        domanda.setEmailUtente(emailUtente);

        // Verifica
        assertEquals(id, domanda.getId());
        assertEquals(messaggio, domanda.getMessaggio());
        assertEquals(dataRicezione, domanda.getDataRicezione());
        assertTrue(domanda.isStato());
        assertEquals(emailUtente, domanda.getEmailUtente());
    }

    @Test
    void testToString() {
        // Setup
        int id = 3;
        String messaggio = "Messaggio per test toString";
        LocalDateTime dataRicezione = LocalDateTime.of(2025, 1, 1, 12, 0);
        boolean stato = false;
        String emailUtente = "test@test.com";

        // Esegui
        Domanda domanda = new Domanda(id, messaggio, dataRicezione, stato, emailUtente);
        String toStringResult = domanda.toString();

        // Verifica
        String expected = "Domanda{id=3, messaggio='Messaggio per test toString', dataRicezione=2025-01-01T12:00, stato=false, emailUtente='test@test.com'}";
        assertEquals(expected, toStringResult);
    }
}
