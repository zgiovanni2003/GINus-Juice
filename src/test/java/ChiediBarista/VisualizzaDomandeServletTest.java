package ChiediBarista;

import Model.Domanda;
import Model.DomandaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class VisualizzaDomandeServletTest {

    private VisualizzaDomandeServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private DomandaDAO domandaDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new VisualizzaDomandeServlet() {
            @Override
            protected DomandaDAO createDomandaDAO() {
                return domandaDAO; // Restituisce il mock per i test
            }
        };
    }

    @Test
    public void testVisualizzaDomandeSuccesso() throws ServletException, IOException {
        // Arrange
        List<Domanda> domandeMock = Arrays.asList(
                new Domanda(1, "Domanda 1", LocalDateTime.now(), false, "utente1@email.com"),
                new Domanda(2, "Domanda 2", LocalDateTime.now(), false, "utente2@email.com")
        );
        when(domandaDAO.getDomandeNonRisposte()).thenReturn(domandeMock);
        when(request.getRequestDispatcher("/DomandeBarista.jsp")).thenReturn(requestDispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("domande", domandeMock);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testVisualizzaDomandeVuoto() throws ServletException, IOException {
        // Arrange
        when(domandaDAO.getDomandeNonRisposte()).thenReturn(Arrays.asList());
        when(request.getRequestDispatcher("/DomandeBarista.jsp")).thenReturn(requestDispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("domande", Arrays.asList());
        verify(requestDispatcher).forward(request, response);
    }
}
