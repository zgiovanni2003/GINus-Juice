package Magazzino;

import Model.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EliminaProdottoServlet")
public class EliminaProdottoServlet extends HttpServlet {

    private final ProdottoDAO prodottoDAO;

    // Costruttore con iniezione di dipendenza
    public EliminaProdottoServlet(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    // Costruttore di default
    public EliminaProdottoServlet() {
        this(new ProdottoDAO());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            int idProdotto = Integer.parseInt(idParam);

            boolean eliminato = prodottoDAO.deleteProdottoById(idProdotto);

            if (eliminato) {
                response.sendRedirect("Magazzino.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido.");
        }
    }
}
