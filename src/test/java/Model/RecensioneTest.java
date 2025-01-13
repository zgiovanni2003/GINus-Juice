package Model;

import Model.Recensione;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecensioneTest {

    @Test
    void testCostruttore() {
        // Setup
        String emailUtente = "utente@test.com";
        int idProdotto = 1;
        String corpoRecensione = "Recensione di test";
        Date dataRecensione = new Date();

        // Esegui
        Recensione recensione = new Recensione(emailUtente, idProdotto, corpoRecensione, dataRecensione);

        // Verifica
        assertEquals(emailUtente, recensione.getEmailUtente());
        assertEquals(idProdotto, recensione.getIdProdotto());
        assertEquals(corpoRecensione, recensione.getCorpoRecensione());
        assertEquals(dataRecensione, recensione.getDataRecensione());
    }

    @Test
    void testGetterESetter() {
        // Setup
        Recensione recensione = new Recensione(null, 0, null, null);

        String emailUtente = "utente@test.com";
        int idProdotto = 2;
        String corpoRecensione = "Nuova recensione";
        Date dataRecensione = new Date();

        // Esegui
        recensione.setEmailUtente(emailUtente);
        recensione.setIdProdotto(idProdotto);
        recensione.setCorpoRecensione(corpoRecensione);
        recensione.setDataRecensione(dataRecensione);

        // Verifica
        assertEquals(emailUtente, recensione.getEmailUtente());
        assertEquals(idProdotto, recensione.getIdProdotto());
        assertEquals(corpoRecensione, recensione.getCorpoRecensione());
        assertEquals(dataRecensione, recensione.getDataRecensione());
    }

    @Test
    void testToString() {
        // Setup
        String emailUtente = "utente@test.com";
        int idProdotto = 3;
        String corpoRecensione = "Recensione per il prodotto";
        Date dataRecensione = new Date();

        Recensione recensione = new Recensione(emailUtente, idProdotto, corpoRecensione, dataRecensione);

        // Esegui
        String toStringResult = recensione.toString();

        // Verifica
        String expected = "Recensione{" +
                "emailUtente='" + emailUtente + '\'' +
                ", idProdotto=" + idProdotto +
                ", corpoRecensione='" + corpoRecensione + '\'' +
                ", dataRecensione=" + dataRecensione +
                '}';
        assertEquals(expected, toStringResult);
    }
}
