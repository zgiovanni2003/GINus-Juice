package Magazzino;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ModificaProdottoServletTest {

    private ModificaProdottoServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ProdottoDAO prodottoDAO;
    private ServletContext servletContext;

    @BeforeEach
    public void setUp() {
        servlet = new ModificaProdottoServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        prodottoDAO = mock(ProdottoDAO.class);
        servletContext = mock(ServletContext.class);

        servlet.setProdottoDAO(prodottoDAO);
        servlet.setServletContext(servletContext);
    }

    @Test
    public void testModificaProdottoSuccesso() throws Exception {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("ProdottoTest");
        when(request.getParameter("prezzo")).thenReturn("19.99");
        when(request.getParameter("descrizione")).thenReturn("Descrizione aggiornata");
        when(request.getParameter("quantita")).thenReturn("10");

        when(prodottoDAO.updateProdotto(any(Prodotto.class))).thenReturn(true);

        servlet.doPost(request, response);

        verify(prodottoDAO).updateProdotto(argThat(prodotto ->
                prodotto.getId() == 1 &&
                        prodotto.getNome().equals("ProdottoTest") &&
                        prodotto.getPrezzo() == 19.99 &&
                        prodotto.getDescrizione().equals("Descrizione aggiornata") &&
                        prodotto.getQuantita() == 10
        ));

        verify(response).sendRedirect("Magazzino.jsp");
    }

    @Test
    public void testModificaProdottoIdNonValido() throws Exception {
        when(request.getParameter("id")).thenReturn("abc");

        servlet.doPost(request, response);

        verify(prodottoDAO, never()).updateProdotto(any(Prodotto.class));

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Dati non validi.");
    }

}
