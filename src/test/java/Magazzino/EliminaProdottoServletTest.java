package Magazzino;

import Model.ProdottoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class EliminaProdottoServletTest {

    private EliminaProdottoServlet servlet;
    private ProdottoDAO prodottoDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        prodottoDAO = mock(ProdottoDAO.class);
        servlet = new EliminaProdottoServlet(prodottoDAO);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testEliminaProdottoSuccesso() throws Exception {
        // Configura il parametro della richiesta
        when(request.getParameter("id")).thenReturn("1");
        when(prodottoDAO.deleteProdottoById(1)).thenReturn(true);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che il prodotto sia stato eliminato e che ci sia il redirect
        verify(prodottoDAO).deleteProdottoById(1);
        verify(response).sendRedirect("Magazzino.jsp");
    }

    @Test
    public void testEliminaProdottoNonTrovato() throws Exception {
        // Configura il parametro della richiesta
        when(request.getParameter("id")).thenReturn("2");
        when(prodottoDAO.deleteProdottoById(2)).thenReturn(false);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che la servlet gestisca correttamente la mancata eliminazione
        verify(prodottoDAO).deleteProdottoById(2);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato.");
    }

    @Test
    public void testEliminaProdottoIdNonValido() throws Exception {
        // Configura il parametro della richiesta con un ID non valido
        when(request.getParameter("id")).thenReturn("abc");

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che la servlet gestisca correttamente l'errore di formato
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido.");
        verifyNoInteractions(prodottoDAO);
    }

    @Test
    public void testEliminaProdottoIdMancante() throws Exception {
        // Configura il parametro della richiesta senza specificare l'ID
        when(request.getParameter("id")).thenReturn(null);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che la servlet gestisca correttamente l'assenza del parametro
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido.");
        verifyNoInteractions(prodottoDAO);
    }
}
