package Admin;

import Model.UtenteDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class AggiornaRuoloServletTest {

    private AggiornaRuoloServlet servlet;
    private UtenteDAO utenteDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        // Mock degli oggetti necessari
        utenteDAO = mock(UtenteDAO.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        // Inizializza la servlet con il DAO mockato
        servlet = new AggiornaRuoloServlet(utenteDAO);
    }

    @Test
    void testAggiornaRuoloSuccesso() throws Exception {
        // Configura il mock per i parametri della richiesta
        when(request.getParameter("email")).thenReturn("mario.rossi@email.com");
        when(request.getParameter("ruolo")).thenReturn("admin");

        // Simula un aggiornamento riuscito
        when(utenteDAO.aggiornaRuolo("mario.rossi@email.com", "admin")).thenReturn(true);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che il DAO sia stato chiamato con i parametri corretti
        verify(utenteDAO).aggiornaRuolo("mario.rossi@email.com", "admin");

        // Verifica che il reindirizzamento avvenga con il parametro di successo
        verify(response).sendRedirect("PannelloAdmin.jsp?success=1");
    }

    @Test
    void testAggiornaRuoloFallimento() throws Exception {
        // Configura il mock per i parametri della richiesta
        when(request.getParameter("email")).thenReturn("mario.rossi@email.com");
        when(request.getParameter("ruolo")).thenReturn("admin");

        // Simula un aggiornamento fallito
        when(utenteDAO.aggiornaRuolo("mario.rossi@email.com", "admin")).thenReturn(false);

        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che il DAO sia stato chiamato con i parametri corretti
        verify(utenteDAO).aggiornaRuolo("mario.rossi@email.com", "admin");

        // Verifica che il reindirizzamento avvenga con il parametro di errore
        verify(response).sendRedirect("PannelloAdmin.jsp?error=1");
    }

}
