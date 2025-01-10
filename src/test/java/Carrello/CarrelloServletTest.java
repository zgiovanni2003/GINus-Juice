package Carrello;

import Model.Carrello;
import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarrelloServletTest {

    private CarrelloServlet carrelloServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ProdottoDAO prodottoDAO;

    @BeforeEach
    public void setUp() {
        carrelloServlet = new CarrelloServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        prodottoDAO = mock(ProdottoDAO.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testAggiuntaAlCarrello() throws ServletException, IOException {
        // Configura i mock
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        Carrello carrello = new Carrello();

        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(request.getParameter("action")).thenReturn("aggiungi");
        when(request.getParameter("id")).thenReturn("1");
        when(prodottoDAO.getProdottoById(1)).thenReturn(prodotto);

        // Esegui il metodo doPost
        carrelloServlet.doPost(request, response);

        // Verifica
        assertEquals(1, carrello.getProdotti().get(prodotto));
        verify(response).sendRedirect("Carrello.jsp");
    }

    @Test
    public void testQuantitaSuperioreDisponibile() throws ServletException, IOException {
        // Configura i mock
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        Carrello carrello = new Carrello();

        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(request.getParameter("action")).thenReturn("aggiungi");
        when(request.getParameter("id")).thenReturn("1");
        when(prodottoDAO.getProdottoById(1)).thenReturn(prodotto);

        // Simula aggiunta di una quantità superiore al magazzino
        for (int i = 0; i < 101; i++) {
            carrello.aggiungiProdotto(prodotto);
        }

        // Verifica quantità massima
        int maxScorte = 50;
        if (carrello.getProdotti().get(prodotto) > maxScorte) {
            request.setAttribute("errore", "Hai superato la quantità massima selezionabile.");
        }

        // Verifica
        assertEquals(50, maxScorte); // Confronto con la scorta massima
        verify(request).setAttribute("errore", "Hai superato la quantità massima selezionabile.");
    }

    @Test
    public void testSvuotaCarrello() throws ServletException, IOException {
        // Configura i mock
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        Carrello carrello = new Carrello();
        carrello.aggiungiProdotto(prodotto);

        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(request.getParameter("action")).thenReturn("svuota");

        // Esegui il metodo doPost
        carrello.svuotaCarrello();

        // Verifica
        assertTrue(carrello.getProdotti().isEmpty());
    }

    @Test
    public void testDiminuisciProdotto() throws ServletException, IOException {
        // Configura i mock
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        Carrello carrello = new Carrello();
        carrello.aggiungiProdotto(prodotto);
        carrello.aggiungiProdotto(prodotto); // Aggiungi 2 quantità

        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(request.getParameter("action")).thenReturn("diminuisci");
        when(request.getParameter("id")).thenReturn("1");
        when(prodottoDAO.getProdottoById(1)).thenReturn(prodotto);

        // Esegui il metodo doPost
        carrello.diminuisciQuantita(prodotto);

        // Verifica
        assertEquals(1, carrello.getProdotti().get(prodotto));
    }
    @Test
    public void testAumentaProdottoNelCarrello() throws ServletException, IOException {
        // Configura i mock
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        Carrello carrello = new Carrello();
        carrello.aggiungiProdotto(prodotto); // Quantità iniziale: 1

        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(request.getParameter("action")).thenReturn("aggiungi");
        when(request.getParameter("id")).thenReturn("1");
        when(prodottoDAO.getProdottoById(1)).thenReturn(prodotto);

        // Esegui il metodo doPost
        carrelloServlet.doPost(request, response);

        // Verifica che il prodotto sia stato aumentato nel carrello
        assertEquals(2, carrello.getProdotti().get(prodotto)); // Quantità attesa: 2
        assertEquals(51.98, prodotto.getPrezzo() * carrello.getProdotti().get(prodotto), 0.01); // Prezzo totale atteso: 51.98
        verify(response).sendRedirect("Carrello.jsp");
    }

}
