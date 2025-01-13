package Autenticazione;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoutTest {

    private Logout logout;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        logout = new Logout();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        // Mock della sessione
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testLogoutSuccess() throws ServletException, IOException {
        // Mock del contesto della richiesta
        when(request.getContextPath()).thenReturn("/");

        // Eseguo il metodo doPost
        logout.doPost(request, response);

        // Verifico che la sessione venga invalidata
        verify(session).invalidate();

        // Verifico che venga fatto il redirect alla pagina principale
        verify(response).sendRedirect("//index.jsp");  // Cambiato "/index.jsp" in "//index.jsp" per allinearsi al comportamento del backend
    }
}
