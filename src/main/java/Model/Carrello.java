package Model;

import java.util.HashMap;
import java.util.Map;

public class Carrello {
    private Map<Prodotto, Integer> prodotti;

    public Carrello() {
        this.prodotti = new HashMap<>();
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        prodotti.put(prodotto, prodotti.getOrDefault(prodotto, 0) + 1);
        System.out.println("mi trovo nella funzione aggiugni prodotto " + prodotto.toString());
    }

    public void diminuisciQuantita(Prodotto prodotto) {
        if (prodotti.containsKey(prodotto)) {
            int quantita = prodotti.get(prodotto);
            if (quantita > 1) {
                prodotti.put(prodotto, quantita - 1);
            } else {
                prodotti.remove(prodotto);
            }
        }
    }

    public Map<Prodotto, Integer> getProdotti() {
        return prodotti;
    }

    public void svuotaCarrello() {
        prodotti.clear();
    }

    @Override
    public String toString() {
        return "Carrello{" +
                "prodotti=" + prodotti +
                '}';
    }
}
