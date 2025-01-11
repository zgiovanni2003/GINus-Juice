package Shop;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.Recensione;
import Model.RecensioneDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/VisualizzaDettagliServlet")
public class VisualizzaDettagliServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero dell'ID del prodotto dalla richiesta
        int idProdotto = Integer.parseInt(request.getParameter("id"));

        // DAO per recensioni e prodotti
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        ProdottoDAO prodottoDAO = new ProdottoDAO();

        // Recupera il prodotto dal database
        Prodotto prodotto = prodottoDAO.getProdottoById(idProdotto);

        if (prodotto != null) {
            // Recupera le recensioni del prodotto
            List<Recensione> recensioni = recensioneDAO.getRecensioniByIdProdotto(idProdotto);

            // Imposta attributi per la pagina JSP
            request.setAttribute("prodotto", prodotto);
            request.setAttribute("recensioni", recensioni);

            // Log per debug
            System.out.println("Prodotto recuperato: " + prodotto);
            System.out.println("Recensioni recuperate: " + recensioni);

            // Forward alla pagina JSP dei dettagli
            request.getRequestDispatcher("/DettagliProdotto.jsp").forward(request, response);
        } else {
            // Se il prodotto non esiste, reindirizza a una pagina di errore o allo shop
            System.out.println("Prodotto non trovato con ID: " + idProdotto);
            response.sendRedirect("ErroreProdotto.jsp"); // Puoi sostituire con una pagina personalizzata
        }
    }
}
