package Model;

import Model.Carrello;
import Model.Prodotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarrelloTest {

    private Carrello carrello;
    private Prodotto prodottoMock;

    @BeforeEach
    void setUp() {
        carrello = new Carrello();
        prodottoMock = mock(Prodotto.class); // Mock di un prodotto
    }

    @Test
    void testAggiungiProdotto() {
        // Configura comportamento del mock
        when(prodottoMock.getNome()).thenReturn("Gin Premium");
        when(prodottoMock.getPrezzo()).thenReturn(25.99);

        // Aggiungi il prodotto al carrello
        carrello.aggiungiProdotto(prodottoMock);

        // Verifica che il prodotto sia stato aggiunto
        Map<Prodotto, Integer> prodotti = carrello.getProdotti();
        assertTrue(prodotti.containsKey(prodottoMock));
        assertEquals(1, prodotti.get(prodottoMock)); // Verifica quantità
    }

    @Test
    void testDiminuisciQuantita_ProdottoRimosso() {
        // Aggiungi un prodotto
        carrello.aggiungiProdotto(prodottoMock);

        // Rimuovi il prodotto
        carrello.diminuisciQuantita(prodottoMock);

        // Verifica che il carrello sia vuoto
        assertTrue(carrello.getProdotti().isEmpty());
    }

    @Test
    void testDiminuisciQuantita_DiminuzioneQuantita() {
        // Aggiungi lo stesso prodotto due volte
        carrello.aggiungiProdotto(prodottoMock);
        carrello.aggiungiProdotto(prodottoMock);

        // Diminuire la quantità
        carrello.diminuisciQuantita(prodottoMock);

        // Verifica che la quantità sia diminuita
        assertEquals(1, carrello.getProdotti().get(prodottoMock));
    }

    @Test
    void testSvuotaCarrello() {
        // Aggiungi un prodotto
        carrello.aggiungiProdotto(prodottoMock);

        // Svuota il carrello
        carrello.svuotaCarrello();

        // Verifica che il carrello sia vuoto
        assertTrue(carrello.getProdotti().isEmpty());
    }

    @Test
    void testToString() {
        // Configura comportamento del mock
        when(prodottoMock.toString()).thenReturn("Prodotto{id=1, nome='Gin Premium', prezzo=25.99}");

        // Aggiungi un prodotto
        carrello.aggiungiProdotto(prodottoMock);

        // Verifica che il metodo toString() contenga il prodotto
        String carrelloString = carrello.toString();
        assertTrue(carrelloString.contains("Gin Premium"));
    }
}
