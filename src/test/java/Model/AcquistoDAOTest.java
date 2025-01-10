package Model;

import Model.Acquisto;
import Model.AcquistoDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AcquistoDAOTest {

    @Test
    public void testAcquistoSetAndGet() {
        // Arrange
        Acquisto acquisto = new Acquisto();
        acquisto.setPrezzo(45.50);
        acquisto.setProdottiComprati("Whiskey, Rum");
        acquisto.setEmailUtente("utente@example.com");

        // Act & Assert
        assertEquals(45.50, acquisto.getPrezzo(), "Il prezzo non corrisponde");
        assertEquals("Whiskey, Rum", acquisto.getProdottiComprati(), "I prodotti comprati non corrispondono");
        assertEquals("utente@example.com", acquisto.getEmailUtente(), "L'email dell'utente non corrisponde");
    }

    @Test
    public void testAcquistoDefaultValues() {
        // Arrange
        Acquisto acquisto = new Acquisto();

        // Act & Assert
        assertEquals(0.0, acquisto.getPrezzo(), "Il prezzo predefinito dovrebbe essere 0.0");
        assertNull(acquisto.getProdottiComprati(), "I prodotti comprati predefiniti dovrebbero essere null");
        assertNull(acquisto.getEmailUtente(), "L'email predefinita dovrebbe essere null");
    }
}
