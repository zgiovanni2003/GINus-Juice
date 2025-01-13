package Shop;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.Recensione;
import Model.RecensioneDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VisualizzaDettagliServletTest {

    private VisualizzaDettagliServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ProdottoDAO prodottoDAO;

    @Mock
    private RecensioneDAO recensioneDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new VisualizzaDettagliServlet();
        servlet.setProdottoDAO(prodottoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
    }

    @Test
    public void testDoGet_ProdottoTrovato() throws Exception {
        // Mock input parameters
        when(request.getParameter("id")).thenReturn("1");

        // Mock DAO behavior
        Prodotto prodotto = new Prodotto(1, "Gin Premium", 25.99, "Gin artigianale di alta qualità.", 50);
        when(prodottoDAO.getProdottoById(1)).thenReturn(prodotto);

        Recensione recensione1 = new Recensione("mario.rossi@email.com", 1, "Ottimo prodotto!", new Date());
        Recensione recensione2 = new Recensione("lucia.bianchi@email.com", 1, "Molto buono.", new Date());
        List<Recensione> recensioni = Arrays.asList(recensione1, recensione2);
        when(recensioneDAO.getRecensioniByIdProdotto(1)).thenReturn(recensioni);

        // Mock dispatcher
        when(request.getRequestDispatcher("/DettagliProdotto.jsp")).thenReturn(dispatcher);

        // Call the servlet method
        servlet.doGet(request, response);

        // Capture and verify attributes set
        ArgumentCaptor<List> recensioniCaptor = ArgumentCaptor.forClass(List.class);
        verify(request).setAttribute(eq("prodotto"), eq(prodotto));
        verify(request).setAttribute(eq("recensioni"), recensioniCaptor.capture());

        List capturedRecensioni = recensioniCaptor.getValue();
        assertEquals(2, capturedRecensioni.size());
        assertEquals("Ottimo prodotto!", ((Recensione) capturedRecensioni.get(0)).getCorpoRecensione());

        // Verify forward
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoGet_ProdottoNonTrovato() throws Exception {
        // Mock input parameters
        when(request.getParameter("id")).thenReturn("999");

        // Mock DAO behavior
        when(prodottoDAO.getProdottoById(999)).thenReturn(null);

        // Call the servlet method
        servlet.doGet(request, response);

        // Verify redirection
        verify(response).sendRedirect("ErroreProdotto.jsp");
    }
}