package Admin;

import Model.Acquisto;
import Model.AcquistoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class StoricoAcquistiServletTest {

    private StoricoAcquistiServlet servlet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private HttpSession mockSession;
    private AcquistoDAO mockAcquistoDAO;
    private RequestDispatcher mockDispatcher;

    @BeforeEach
    void setUp() {
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockSession = mock(HttpSession.class);
        mockAcquistoDAO = mock(AcquistoDAO.class);
        mockDispatcher = mock(RequestDispatcher.class);

        servlet = new StoricoAcquistiServlet(mockAcquistoDAO);

        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockDispatcher);
    }

    @Test
    void testStoricoAcquisti_Presenti() throws Exception {
        // Arrange
        String email = "test@test.com";
        List<Acquisto> mockStorico = new ArrayList<>();
        mockStorico.add(new Acquisto(1, email, "Prodotto 1", 50.0, "2025-01-01"));
        mockStorico.add(new Acquisto(2, email, "Prodotto 2", 30.0, "2025-01-02"));

        when(mockRequest.getParameter("email")).thenReturn(email);
        when(mockAcquistoDAO.getAcquistiByEmail(email)).thenReturn(mockStorico);

        // Act
        servlet.doGet(mockRequest, mockResponse);

        // Assert
        verify(mockRequest).setAttribute("storico", mockStorico);
        verify(mockRequest).getRequestDispatcher("StoricoUtente.jsp");
        verify(mockDispatcher).forward(mockRequest, mockResponse);
    }

    @Test
    void testStoricoAcquisti_NonPresenti() throws Exception {
        // Arrange
        String email = "test@test.com";

        when(mockRequest.getParameter("email")).thenReturn(email);
        when(mockAcquistoDAO.getAcquistiByEmail(email)).thenReturn(List.of());

        // Act
        servlet.doGet(mockRequest, mockResponse);

        // Assert
        verify(mockRequest).setAttribute("storico", List.of());
        verify(mockRequest).getRequestDispatcher("StoricoUtente.jsp");
        verify(mockDispatcher).forward(mockRequest, mockResponse);
    }
}
