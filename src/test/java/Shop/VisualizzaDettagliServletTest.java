package Shop;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.Recensione;
import Model.RecensioneDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VisualizzaDettagliServletTest {

    @Mock
    private ProdottoDAO prodottoDAO;

    @Mock
    private RecensioneDAO recensioneDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private VisualizzaDettagliServlet servlet;

    @BeforeEach
    void setUp() {
        // Inizializza manualmente i mock, in assenza di @ExtendWith(MockitoExtension.class)
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet_ProdottoTrovato() throws ServletException, IOException {
        // GIVEN
        when(request.getParameter("id")).thenReturn("1");

        // Simuliamo un Prodotto recuperato dal DAO
        Prodotto fintoProdotto = new Prodotto(1, "Gin Premium", 25.99, "Gin artigianale di alta qualità");
        when(prodottoDAO.getProdottoById(1)).thenReturn(fintoProdotto);
        // Simuliamo una lista di Recensioni
        Recensione rec1 = new Recensione("mario.rossi@email.com",
                1,
                "Gin di altissima qualità, perfetto per cocktail raffinati.",
                new Date());
        Recensione rec2 = new Recensione("altro.utente@email.com",
                1,
                "Buon gin, un po’ caro.",
                new Date());
        List<Recensione> recensioniFinte = Arrays.asList(rec1, rec2);
        when(recensioneDAO.getRecensioniByIdProdotto(1)).thenReturn(recensioniFinte);

        when(request.getRequestDispatcher("/DettagliProdotto.jsp")).thenReturn(requestDispatcher);

        // WHEN
        servlet.doGet(request, response);

        // THEN
        // Verifichiamo che i DAO siano stati chiamati correttamente
        verify(prodottoDAO, times(1)).getProdottoById(1);
        verify(recensioneDAO, times(1)).getRecensioniByIdProdotto(1);

        // Verifichiamo che la servlet abbia settato i giusti attributi nella request
        verify(request).setAttribute("prodotto", fintoProdotto);
        verify(request).setAttribute("recensioni", recensioniFinte);

        // Verifichiamo che avvenga il forward alla JSP
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGet_NessunParametroId() throws ServletException, IOException {
        // GIVEN
        when(request.getParameter("id")).thenReturn(null);

        // WHEN - THEN
        // Ci aspettiamo NumberFormatException da Integer.parseInt(null)
        assertThrows(NumberFormatException.class, () -> {
            servlet.doGet(request, response);
        });

        // Verifichiamo che i DAO NON siano stati chiamati
        verify(prodottoDAO, never()).getProdottoById(anyInt());
        verify(recensioneDAO, never()).getRecensioniByIdProdotto(anyInt());
    }

    @Test
    void testDoGet_IdNonValido() throws ServletException, IOException {
        // GIVEN
        when(request.getParameter("id")).thenReturn("abc"); // "abc" non è un intero

        // WHEN - THEN
        // Ci aspettiamo NumberFormatException da Integer.parseInt("abc")
        assertThrows(NumberFormatException.class, () -> {
            servlet.doGet(request, response);
        });

        // Verifichiamo che i DAO NON siano stati chiamati
        verify(prodottoDAO, never()).getProdottoById(anyInt());
        verify(recensioneDAO, never()).getRecensioniByIdProdotto(anyInt());
    }

    @Test
    void testDoGet_ProdottoNonEsistente() throws ServletException, IOException {
        // GIVEN
        when(request.getParameter("id")).thenReturn("999");
        // Simuliamo che non venga trovato alcun Prodotto con id 999
        when(prodottoDAO.getProdottoById(999)).thenReturn(null);

        when(request.getRequestDispatcher("/DettagliProdotto.jsp")).thenReturn(requestDispatcher);

        // WHEN
        servlet.doGet(request, response);

        // THEN
        // Verifichiamo che la servlet abbia provato a cercare il prodotto (null)
        verify(prodottoDAO, times(1)).getProdottoById(999);

        // Se la servlet gestisce il “prodotto non trovato” settando un attributo a null
        verify(request).setAttribute(eq("prodotto"), isNull());

        // Potresti anche verificare le recensioni (se la servlet imposta una lista vuota o altro)
        // Infine, verifichiamo il forward
        verify(requestDispatcher).forward(request, response);
    }
}
