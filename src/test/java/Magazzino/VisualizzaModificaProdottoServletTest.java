package Magazzino;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.IOException;

public class VisualizzaModificaProdottoServletTest {

    private VisualizzaModificaProdottoServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ProdottoDAO prodottoDAO;
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    public void setUp() {
        servlet = new VisualizzaModificaProdottoServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        prodottoDAO = mock(ProdottoDAO.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void testVisualizzaModificaProdottoSuccesso() throws Exception {
        // Configura il parametro della richiesta
        when(request.getParameter("id")).thenReturn("1");

        // Configura il DAO per restituire un prodotto valido
        Prodotto prodotto = new Prodotto();
        prodotto.setId(1);
        prodotto.setNome("ProdottoTest");
        when(prodottoDAO.getProdottoById(1)).thenReturn(prodotto);

        // Configura il comportamento del RequestDispatcher
        when(request.getRequestDispatcher("ModificaProdotto.jsp")).thenReturn(requestDispatcher);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che il prodotto sia stato trovato e passato alla JSP
        verify(request).setAttribute("prodotto", prodotto);
        verify(requestDispatcher).forward(request, response);
    }



    @Test
    public void testVisualizzaModificaProdottoIdNonValido() throws Exception {
        // Configura il parametro della richiesta con un ID non valido
        when(request.getParameter("id")).thenReturn("abc");

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che venga restituito un errore 400
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido.");
    }

    @Test
    public void testVisualizzaModificaProdottoIdMancante() throws Exception {
        // Configura la richiesta senza il parametro ID
        when(request.getParameter("id")).thenReturn(null);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che venga restituito un errore 400
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido.");
    }
}
