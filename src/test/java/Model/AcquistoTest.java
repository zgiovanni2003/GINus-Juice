package Model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AcquistoTest {

    @Test
    public void testCostruttoreVuoto() {
        Acquisto acquisto = new Acquisto();
        assertNotNull(acquisto, "L'oggetto Acquisto non dovrebbe essere null.");
    }

    @Test
    public void testCostruttoreConParametri() {
        Date dataAcquisto = new Date();
        Acquisto acquisto = new Acquisto(dataAcquisto, 50.0, "Gin Premium", "mario.rossi@email.com");

        assertEquals(dataAcquisto, acquisto.getDataAcquisto(), "La data di acquisto dovrebbe corrispondere.");
        assertEquals(50.0, acquisto.getPrezzo(), 0.001, "Il prezzo dovrebbe corrispondere.");
        assertEquals("Gin Premium", acquisto.getProdottiComprati(), "I prodotti acquistati dovrebbero corrispondere.");
        assertEquals("mario.rossi@email.com", acquisto.getEmailUtente(), "L'email utente dovrebbe corrispondere.");
    }

    @Test
    public void testGetterSetter() {
        Acquisto acquisto = new Acquisto();
        acquisto.setId(1);
        acquisto.setPrezzo(25.99);
        acquisto.setProdottiComprati("Whisky Classico");
        acquisto.setEmailUtente("lucia.bianchi@email.com");

        Date dataAcquisto = new Date();
        acquisto.setDataAcquisto(dataAcquisto);

        assertEquals(1, acquisto.getId(), "L'ID dovrebbe essere 1.");
        assertEquals(25.99, acquisto.getPrezzo(), 0.001, "Il prezzo dovrebbe corrispondere.");
        assertEquals("Whisky Classico", acquisto.getProdottiComprati(), "I prodotti acquistati dovrebbero corrispondere.");
        assertEquals("lucia.bianchi@email.com", acquisto.getEmailUtente(), "L'email utente dovrebbe corrispondere.");
        assertEquals(dataAcquisto, acquisto.getDataAcquisto(), "La data di acquisto dovrebbe corrispondere.");
    }

    @Test
    public void testCostruttoreConIdEConversioneData() {
        String dataAcquistoStr = "2025-01-01";
        Acquisto acquisto = new Acquisto(1, "mario.rossi@email.com", "Gin Premium", 50.0, dataAcquistoStr);

        assertEquals(1, acquisto.getId(), "L'ID dovrebbe essere 1.");
        assertEquals("mario.rossi@email.com", acquisto.getEmailUtente(), "L'email utente dovrebbe corrispondere.");
        assertEquals("Gin Premium", acquisto.getProdottiComprati(), "I prodotti acquistati dovrebbero corrispondere.");
        assertEquals(50.0, acquisto.getPrezzo(), 0.001, "Il prezzo dovrebbe corrispondere.");

        try {
            Date expectedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataAcquistoStr);
            assertEquals(expectedDate, acquisto.getDataAcquisto(), "La data di acquisto dovrebbe corrispondere.");
        } catch (ParseException e) {
            fail("Il parsing della data del test dovrebbe avere successo.");
        }
    }

    @Test
    public void testConversioneDataNonValida() {
        String dataAcquistoStr = "data-non-valida";
        Acquisto acquisto = new Acquisto(1, "mario.rossi@email.com", "Gin Premium", 50.0, dataAcquistoStr);

        assertNull(acquisto.getDataAcquisto(), "La data di acquisto dovrebbe essere null per una stringa non valida.");
    }
}
