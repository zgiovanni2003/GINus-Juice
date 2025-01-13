package Admin;

import Model.UtenteDAO;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class RimuoviUtenteServletTest {

    private RimuoviUtenteServlet servlet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private UtenteDAO mockUtenteDAO;

    @BeforeEach
    void setUp() {
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockUtenteDAO = mock(UtenteDAO.class);

        // Iniettiamo il mock UtenteDAO nella servlet
        servlet = new RimuoviUtenteServlet(mockUtenteDAO);
    }

    @Test
    void testRimuoviUtente_Successo() throws IOException {
        // Arrange
        String email = "test@test.com";

        when(mockRequest.getParameter("email")).thenReturn(email);
        when(mockUtenteDAO.rimuoviUtente(email)).thenReturn(true);

        // Act
        servlet.doPost(mockRequest, mockResponse);

        // Assert
        verify(mockResponse).sendRedirect("PannelloAdmin.jsp?success=1");
        verify(mockUtenteDAO).rimuoviUtente(email);
    }

    @Test
    void testRimuoviUtente_Fallimento() throws IOException {
        // Arrange
        String email = "test@test.com";

        when(mockRequest.getParameter("email")).thenReturn(email);
        when(mockUtenteDAO.rimuoviUtente(email)).thenReturn(false);

        // Act
        servlet.doPost(mockRequest, mockResponse);

        // Assert
        verify(mockResponse).sendRedirect("PannelloAdmin.jsp?error=1");
        verify(mockUtenteDAO).rimuoviUtente(email);
    }
}