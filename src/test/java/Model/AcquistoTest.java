package Model;

import Model.Acquisto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AcquistoTest {

    private Acquisto acquisto;

    @BeforeEach
    public void setUp() {
        // Inizializza un oggetto Acquisto prima di ogni test
        acquisto = new Acquisto();
    }

    @Test
    public void testSetAndGetId() {
        acquisto.setId(1);
        assertEquals(1, acquisto.getId(), "L'ID non corrisponde al valore atteso");
    }

    @Test
    public void testSetAndGetDataAcquisto() {
        Date now = new Date();
        acquisto.setDataAcquisto(now);
        assertEquals(now, acquisto.getDataAcquisto(), "La data di acquisto non corrisponde al valore atteso");
    }

    @Test
    public void testSetAndGetPrezzo() {
        acquisto.setPrezzo(99.99);
        assertEquals(99.99, acquisto.getPrezzo(), "Il prezzo non corrisponde al valore atteso");
    }

    @Test
    public void testSetAndGetProdottiComprati() {
        String prodotti = "Gin, Vodka, Rum";
        acquisto.setProdottiComprati(prodotti);
        assertEquals(prodotti, acquisto.getProdottiComprati(), "I prodotti comprati non corrispondono al valore atteso");
    }

    @Test
    public void testSetAndGetEmailUtente() {
        String email = "utente@example.com";
        acquisto.setEmailUtente(email);
        assertEquals(email, acquisto.getEmailUtente(), "L'email dell'utente non corrisponde al valore atteso");
    }

    @Test
    public void testConstructorWithParameters() {
        Date now = new Date();
        String prodotti = "Gin, Vodka, Rum";
        String email = "utente@example.com";
        double prezzo = 99.99;

        Acquisto acquistoConParametri = new Acquisto(now, prezzo, prodotti, email);

        assertEquals(now, acquistoConParametri.getDataAcquisto(), "La data di acquisto non corrisponde al valore atteso");
        assertEquals(prezzo, acquistoConParametri.getPrezzo(), "Il prezzo non corrisponde al valore atteso");
        assertEquals(prodotti, acquistoConParametri.getProdottiComprati(), "I prodotti comprati non corrispondono al valore atteso");
        assertEquals(email, acquistoConParametri.getEmailUtente(), "L'email dell'utente non corrisponde al valore atteso");
    }
}
