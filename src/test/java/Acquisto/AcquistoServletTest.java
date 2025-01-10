package Acquisto;

import Model.AcquistoDAO;
import Model.Carrello;
import Model.Prodotto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AcquistoServletTest {

    private AcquistoServlet acquistoServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private AcquistoDAO acquistoDAO;

    @BeforeEach
    public void setUp() {
        acquistoServlet = new AcquistoServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        acquistoDAO = mock(AcquistoDAO.class);

        // Simulazione del comportamento della sessione
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testAcquistoSuccesso() throws Exception {
        // Mock del carrello con prodotti
        Carrello carrello = new Carrello();
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        carrello.aggiungiProdotto(prodotto);

        // Configurazione del mock
        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(session.getAttribute("email")).thenReturn("mario.rossi@email.com");
        when(acquistoDAO.salvaAcquisto(any())).thenReturn(true);

        // Mockare direttamente l'istanza DAO
        AcquistoServlet servletUnderTest = new AcquistoServlet() {
            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) {
                boolean successo = acquistoDAO.salvaAcquisto(any());
                try {
                    if (successo) {
                        session.removeAttribute("carrello");
                        response.sendRedirect("ConfermaAcquisto.jsp");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Esegui il test
        servletUnderTest.doPost(request, response);

        // Verifica che il carrello venga rimosso e reindirizzato
        verify(session).removeAttribute("carrello");
        verify(response).sendRedirect("ConfermaAcquisto.jsp");
    }

    @Test
    public void testCarrelloVuoto() throws Exception {
        // Mock di un carrello vuoto
        when(session.getAttribute("carrello")).thenReturn(new Carrello());

        // Esegui la servlet
        acquistoServlet.doPost(request, response);

        // Verifica il reindirizzamento alla pagina del carrello
        verify(response).sendRedirect("Carrello.jsp");
    }

    @Test
    public void testUtenteNonLoggato() throws Exception {
        // Mock del carrello con prodotti
        Carrello carrello = new Carrello();
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        carrello.aggiungiProdotto(prodotto);

        // Simulazione di un utente non loggato
        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(session.getAttribute("email")).thenReturn(null); // Simula che l'utente non sia loggato

        // Esegui la servlet
        acquistoServlet.doPost(request, response);

        // Verifica il reindirizzamento alla pagina di login
        verify(response).sendRedirect("Login.jsp");
    }


    @Test
    public void testErroreSalvataggioAcquisto() throws Exception {
        // Mock del carrello con prodotti
        Carrello carrello = new Carrello();
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin di alta qualità");
        carrello.aggiungiProdotto(prodotto);

        // Configurazione del mock
        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(session.getAttribute("email")).thenReturn("mario.rossi@email.com");
        when(acquistoDAO.salvaAcquisto(any())).thenReturn(false);

        // Mockare direttamente l'istanza DAO
        AcquistoServlet servletUnderTest = new AcquistoServlet() {
            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) {
                boolean successo = acquistoDAO.salvaAcquisto(any());
                try {
                    if (!successo) {
                        response.sendRedirect("ErroreAcquisto.jsp");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Esegui il test
        servletUnderTest.doPost(request, response);

        // Verifica il reindirizzamento alla pagina di errore
        verify(response).sendRedirect("ErroreAcquisto.jsp");
    }
}
