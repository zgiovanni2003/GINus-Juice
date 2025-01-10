package ChiediBarista;

import Model.RispostaDAO;
import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class RispondiDomandaServletTest {

    private RispondiDomandaServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RispostaDAO rispostaDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new RispondiDomandaServlet(rispostaDAO);
    }

    @Test
    public void testBaristaInviaRispostaSuccesso() throws IOException, ServletException {
        // Arrange
        when(request.getParameter("id_domanda")).thenReturn("1");
        when(request.getParameter("risposta")).thenReturn("Risposta Test");
        when(request.getSession()).thenReturn(session);

        Utente barista = new Utente("giuseppe.verdi@email.com", "Giuseppe", "Verdi", "password789", true, "barista");
        when(session.getAttribute("utente")).thenReturn(barista);

        // Mock del metodo inserisciRisposta per restituire true
        when(rispostaDAO.inserisciRisposta("Risposta Test", 1, "giuseppe.verdi@email.com"))
                .thenReturn(true);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("VisualizzaDomandeServlet");
    }

    @Test
    public void testBaristaInviaRispostaFallimento() throws IOException, ServletException {
        // Arrange
        when(request.getParameter("id_domanda")).thenReturn("1");
        when(request.getParameter("risposta")).thenReturn("Risposta Test");
        when(request.getSession()).thenReturn(session);

        Utente barista = new Utente("giuseppe.verdi@email.com", "Giuseppe", "Verdi", "password789", true, "barista");
        when(session.getAttribute("utente")).thenReturn(barista);

        // Mock del metodo inserisciRisposta per restituire false
        when(rispostaDAO.inserisciRisposta("Risposta Test", 1, "giuseppe.verdi@email.com"))
                .thenReturn(false);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("DomandeBarista.jsp?error=risposta");
    }
}

