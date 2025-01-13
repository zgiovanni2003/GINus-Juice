package Shop;

import Model.RecensioneDAO;
import Model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AggiungiRecensioneServletTest {

    private AggiungiRecensioneServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RecensioneDAO recensioneDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new AggiungiRecensioneServlet();
    }

    @Test
    public void testDoPost_RecensioneConParoleScurrili() throws Exception {
        // Mock input parameters
        when(request.getParameter("idProdotto")).thenReturn("1");
        when(request.getParameter("corpoRecensione")).thenReturn("Questo prodotto è una troia.");
        when(request.getSession()).thenReturn(session);

        Utente utente = mock(Utente.class);
        when(utente.getEmail()).thenReturn("mario.rossi@email.com");
        when(session.getAttribute("utente")).thenReturn(utente);

        // Call the servlet method
        servlet.doPost(request, response);

        // Verify redirect to error page
        verify(response).sendRedirect("ErroreRecensione.jsp");
    }

    @Test
    public void testDoPost_RecensioneValida() throws Exception {
        // Mock input parameters
        when(request.getParameter("idProdotto")).thenReturn("1");
        when(request.getParameter("corpoRecensione")).thenReturn("Ottimo prodotto!");
        when(request.getSession()).thenReturn(session);

        Utente utente = mock(Utente.class);
        when(utente.getEmail()).thenReturn("mario.rossi@email.com");
        when(session.getAttribute("utente")).thenReturn(utente);

        // Use a spy on the servlet to mock DAO behavior
        AggiungiRecensioneServlet spyServlet = spy(servlet);
        doReturn(recensioneDAO).when(spyServlet).createRecensioneDAO();

        // Mock DAO behavior
        when(recensioneDAO.inserisciRecensione("mario.rossi@email.com", 1, "Ottimo prodotto!")).thenReturn(true);

        // Call the servlet method
        spyServlet.doPost(request, response);

        // Verify redirect to success page
        verify(response).sendRedirect("VisualizzaDettagliServlet?id=1");
    }
}

