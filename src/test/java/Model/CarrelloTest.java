package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CarrelloTest {

    private Carrello carrello;
    private Prodotto prodotto1;
    private Prodotto prodotto2;

    @BeforeEach
    public void setUp() {
        // Inizializza carrello e prodotti con costruttore che richiede ID e tipo
        carrello = new Carrello();
        prodotto1 = new Prodotto(1, "Gin", 19.99, "Alcolico");
        prodotto2 = new Prodotto(2, "Vodka", 14.99, "Alcolico");
    }

    @Test
    public void testAggiungiProdotto() {
        // Act: Aggiungi il prodotto 1 due volte
        carrello.aggiungiProdotto(prodotto1);
        carrello.aggiungiProdotto(prodotto1);

        // Assert: Verifica che il carrello contenga il prodotto con quantità 2
        Map<Prodotto, Integer> prodotti = carrello.getProdotti();
        assertTrue(prodotti.containsKey(prodotto1), "Il carrello dovrebbe contenere il prodotto aggiunto");
        assertEquals(2, prodotti.get(prodotto1), "La quantità del prodotto dovrebbe essere 2");
    }

    @Test
    public void testDiminuisciQuantita() {
        // Arrange: Aggiungi il prodotto 1 due volte
        carrello.aggiungiProdotto(prodotto1);
        carrello.aggiungiProdotto(prodotto1);

        // Act: Diminuisci la quantità del prodotto
        carrello.diminuisciQuantita(prodotto1);

        // Assert: La quantità dovrebbe scendere a 1
        Map<Prodotto, Integer> prodotti = carrello.getProdotti();
        assertEquals(1, prodotti.get(prodotto1), "La quantità del prodotto dovrebbe essere diminuita a 1");

        // Act: Diminuisci la quantità fino a rimuovere il prodotto
        carrello.diminuisciQuantita(prodotto1);

        // Assert: Il prodotto dovrebbe essere rimosso dal carrello
        assertFalse(prodotti.containsKey(prodotto1), "Il prodotto dovrebbe essere rimosso dal carrello");
    }

    @Test
    public void testSvuotaCarrello() {
        // Arrange: Aggiungi due prodotti
        carrello.aggiungiProdotto(prodotto1);
        carrello.aggiungiProdotto(prodotto2);

        // Act: Svuota il carrello
        carrello.svuotaCarrello();

        // Assert: Il carrello dovrebbe essere vuoto
        assertTrue(carrello.getProdotti().isEmpty(), "Il carrello dovrebbe essere vuoto dopo averlo svuotato");
    }

    @Test
    public void testToString() {
        // Arrange: Aggiungi i due prodotti
        carrello.aggiungiProdotto(prodotto1);
        carrello.aggiungiProdotto(prodotto2);

        // Act: Ottieni la rappresentazione del carrello in formato stringa
        String carrelloString = carrello.toString();

        // Assert: La rappresentazione del carrello dovrebbe contenere i nomi dei prodotti
        assertNotNull(carrelloString, "Il metodo toString() non dovrebbe restituire null");
        assertTrue(carrelloString.contains("Gin"), "La rappresentazione del carrello dovrebbe contenere il nome del prodotto 1");
        assertTrue(carrelloString.contains("Vodka"), "La rappresentazione del carrello dovrebbe contenere il nome del prodotto 2");
    }
}
