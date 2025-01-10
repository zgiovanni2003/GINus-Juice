package ChiediBarista;

import Model.Risposta;
import Model.RispostaDAO;
import Model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class VisualizzaRisposteServletTest {

    private VisualizzaRisposteServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private RispostaDAO rispostaDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new VisualizzaRisposteServlet() {
            @Override
            protected RispostaDAO createRispostaDAO() {
                return rispostaDAO; // Restituisce il mock
            }
        };
    }


    @Test
    public void testVisualizzaRisposteSuccesso() throws ServletException, IOException {
        // Arrange
        when(request.getSession()).thenReturn(session);

        Utente utente = new Utente("utente@email.com", "Mario", "Rossi", "password123", true, "utente");
        when(session.getAttribute("utente")).thenReturn(utente);

        List<Risposta> risposteMock = Arrays.asList(
                new Risposta(1, "Risposta 1", LocalDateTime.now(), "barista@email.com", 101),
                new Risposta(2, "Risposta 2", LocalDateTime.now(), "barista@email.com", 102)
        );

        when(rispostaDAO.getRisposteByEmailUtente("utente@email.com")).thenReturn(risposteMock);
        when(request.getRequestDispatcher("/VisualizzaRisposte.jsp")).thenReturn(requestDispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("risposte", risposteMock);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testVisualizzaRisposteVuoto() throws ServletException, IOException {
        // Arrange
        when(request.getSession()).thenReturn(session);

        Utente utente = new Utente("utente@email.com", "Mario", "Rossi", "password123", true, "utente");
        when(session.getAttribute("utente")).thenReturn(utente);

        when(rispostaDAO.getRisposteByEmailUtente("utente@email.com")).thenReturn(Arrays.asList());
        when(request.getRequestDispatcher("/VisualizzaRisposte.jsp")).thenReturn(requestDispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("risposte", Arrays.asList());
        verify(requestDispatcher).forward(request, response);
    }
}
