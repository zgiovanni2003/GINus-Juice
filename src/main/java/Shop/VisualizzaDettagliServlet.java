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
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int idProdotto = Integer.parseInt(request.getParameter("id"));
       //System.out.println(idProdotto);
       RecensioneDAO recensioneDAO = new RecensioneDAO();
       ProdottoDAO prodottoDAO = new ProdottoDAO();
       Prodotto prodotto = prodottoDAO.getProdottoById(idProdotto);

       System.out.println(prodotto);

       List<Recensione> recensioni=recensioneDAO.getRecensioniByIdProdotto(idProdotto);
       request.setAttribute("recensioni", recensioni);
       request.setAttribute("prodotto", prodotto);
       System.out.println(recensioni);
       request.getRequestDispatcher("/DettagliProdotto.jsp").forward(request, response);
   }
}
