package Magazzino;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/VisualizzaModificaProdottoServlet")
public class VisualizzaModificaProdottoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera l'ID del prodotto dalla richiesta
            int id = Integer.parseInt(request.getParameter("id"));

            // Trova il prodotto nel database
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            Prodotto prodotto = prodottoDAO.getProdottoById(id);

            if (prodotto != null) {
                // Passa il prodotto alla JSP
                request.setAttribute("prodotto", prodotto);
                request.getRequestDispatcher("ModificaProdotto.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido.");
        }
    }
}
