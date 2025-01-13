package Model;

import Model.Prodotto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdottoTest {

    @Test
    void testCostruttoreConParametri() {
        // Setup
        int id = 1;
        String nome = "Gin Premium";
        double prezzo = 25.99;
        String descrizione = "Gin di alta qualità";
        int quantita = 50;

        // Esegui
        Prodotto prodotto = new Prodotto(id, nome, prezzo, descrizione, quantita);

        // Verifica
        assertEquals(id, prodotto.getId());
        assertEquals(nome, prodotto.getNome());
        assertEquals(prezzo, prodotto.getPrezzo());
        assertEquals(descrizione, prodotto.getDescrizione());
        assertEquals(quantita, prodotto.getQuantita());
    }

    @Test
    void testGetterESetter() {
        // Setup
        Prodotto prodotto = new Prodotto();

        int id = 2;
        String nome = "Whisky Classico";
        double prezzo = 35.50;
        String descrizione = "Whisky invecchiato 12 anni";
        int quantita = 30;

        // Esegui
        prodotto.setId(id);
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setDescrizione(descrizione);
        prodotto.setQuantita(quantita);

        // Verifica
        assertEquals(id, prodotto.getId());
        assertEquals(nome, prodotto.getNome());
        assertEquals(prezzo, prodotto.getPrezzo());
        assertEquals(descrizione, prodotto.getDescrizione());
        assertEquals(quantita, prodotto.getQuantita());
    }

    @Test
    void testToString() {
        // Setup
        int id = 3;
        String nome = "Rum Scuro";
        double prezzo = 18.75;
        String descrizione = "Rum speziato";
        int quantita = 20;

        // Esegui
        Prodotto prodotto = new Prodotto(id, nome, prezzo, descrizione, quantita);
        String toStringResult = prodotto.toString();

        // Verifica
        String expected = "Prodotto{id=3, nome='Rum Scuro', prezzo=18.75, descrizione='Rum speziato', quantita=20}";
        assertEquals(expected, toStringResult);
    }

    @Test
    void testEquals() {
        // Setup
        Prodotto prodotto1 = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità", 50);
        Prodotto prodotto2 = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità", 50);
        Prodotto prodotto3 = new Prodotto(2, "Whisky Classico", 35.50, "Whisky invecchiato", 30);

        // Verifica
        assertEquals(prodotto1, prodotto2, "Prodotti con lo stesso ID dovrebbero essere uguali");
        assertNotEquals(prodotto1, prodotto3, "Prodotti con ID diversi non dovrebbero essere uguali");
    }

    @Test
    void testHashCode() {
        // Setup
        Prodotto prodotto1 = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità", 50);
        Prodotto prodotto2 = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità", 50);
        Prodotto prodotto3 = new Prodotto(2, "Whisky Classico", 35.50, "Whisky invecchiato", 30);

        // Verifica
        assertEquals(prodotto1.hashCode(), prodotto2.hashCode(), "Prodotti con lo stesso ID dovrebbero avere lo stesso hashCode");
        assertNotEquals(prodotto1.hashCode(), prodotto3.hashCode(), "Prodotti con ID diversi non dovrebbero avere lo stesso hashCode");
    }
}
