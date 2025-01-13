package Shop;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton per la gestione dei prodotti del negozio.
 * Carica i prodotti dal database e li rende disponibili per tutta l'applicazione.
 */
public class ProdottoSingleton {
    private static ProdottoSingleton instance;
    private List<Prodotto> prodotti;

    // Costruttore privato per impedire l'istanziazione diretta
    private ProdottoSingleton() {
        try {
            // Caricamento iniziale dei prodotti dal database
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            this.prodotti = prodottoDAO.getAllProdotti();
        } catch (Exception e) {
            // Log dell'errore e fallback a una lista vuota
            System.err.println("Errore durante il caricamento dei prodotti: " + e.getMessage());
            this.prodotti = new ArrayList<>();
        }
    }

    /**
     * Restituisce l'istanza singleton di ProdottoSingleton.
     * @return Istanza di ProdottoSingleton
     */
    public static synchronized ProdottoSingleton getInstance() {
        if (instance == null) {
            instance = new ProdottoSingleton();
        }
        return instance;
    }

    public void salvaProdottiNelContext(ServletContext context) {
        context.setAttribute("prodotti", prodotti);
        System.out.println("Prodotti salvati nel ServletContext: " + prodotti.size());
    }

    /**
     * Restituisce la lista di prodotti caricati.
     * @return Lista di prodotti
     */
    public List<Prodotto> getProdotti() {
        return prodotti;
    }
   //****************************************************************************++
    public static synchronized void setInstance(ProdottoSingleton customInstance) {
        instance = customInstance;
    }
    //**********************************************************************
}
