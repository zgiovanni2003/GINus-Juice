package Magazzino;

import Model.ProdottoDAO;
import Shop.ProdottoSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EliminaProdottoServlet")
public class EliminaProdottoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera l'ID del prodotto dalla richiesta
            int idProdotto = Integer.parseInt(request.getParameter("id"));

            // Rimuovi il prodotto dal database
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            boolean eliminato = prodottoDAO.deleteProdottoById(idProdotto);

            if (eliminato) {
                // Aggiorna la lista dei prodotti nel contesto dell'applicazione
                ProdottoSingleton prodottoSingleton = ProdottoSingleton.getInstance();
                prodottoSingleton.getProdotti().removeIf(prodotto -> prodotto.getId() == idProdotto);

                // Aggiorna i prodotti nel contesto
                getServletContext().setAttribute("prodotti", prodottoSingleton.getProdotti());
            }

            // Reindirizza di nuovo alla pagina del magazzino
            response.sendRedirect("Magazzino.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido.");
        }
    }
}
