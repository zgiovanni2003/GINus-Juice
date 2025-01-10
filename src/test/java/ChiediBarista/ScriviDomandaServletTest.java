package ChiediBarista;

import Model.DomandaDAO;
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

public class ScriviDomandaServletTest {

    private ScriviDomandaServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private DomandaDAO domandaDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new ScriviDomandaServlet(domandaDAO);
    }

    @Test
    public void testInvioDomandaSuccesso() throws IOException, ServletException {
        // Arrange
        when(request.getParameter("messaggio")).thenReturn("Questo è un messaggio di prova.");
        when(request.getSession()).thenReturn(session);

        Utente utente = new Utente("mario.rossi@email.com", "Mario", "Rossi", "password123", true, "utente");
        when(session.getAttribute("utente")).thenReturn(utente);

        // Mock del metodo inserisciDomanda per restituire true
        when(domandaDAO.inserisciDomanda("Questo è un messaggio di prova.", "mario.rossi@email.com"))
                .thenReturn(true);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("index.jsp?success=domanda");
    }

    @Test
    public void testInvioDomandaFallimento() throws IOException, ServletException {
        // Arrange
        when(request.getParameter("messaggio")).thenReturn("Questo è un messaggio di prova.");
        when(request.getSession()).thenReturn(session);

        Utente utente = new Utente("mario.rossi@email.com", "Mario", "Rossi", "password123", true, "utente");
        when(session.getAttribute("utente")).thenReturn(utente);

        // Mock del metodo inserisciDomanda per restituire false
        when(domandaDAO.inserisciDomanda("Questo è un messaggio di prova.", "mario.rossi@email.com"))
                .thenReturn(false);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("index.jsp?error=domanda");
    }

    @Test
    public void testUtenteNonLoggato() throws IOException, ServletException {
        // Arrange
        when(request.getParameter("messaggio")).thenReturn("Questo è un messaggio di prova.");
        when(request.getSession()).thenReturn(session);

        // L'utente non è loggato
        when(session.getAttribute("utente")).thenReturn(null);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("login.jsp");
    }
}
