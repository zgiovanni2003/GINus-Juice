package ChiediBarista;

import Model.Domanda;
import Model.DomandaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/VisualizzaDomandeServlet")
public class VisualizzaDomandeServlet extends HttpServlet {
//*************************************************
    //mod
     protected DomandaDAO createDomandaDAO() {
    return new DomandaDAO();
}
    //***************************************************++
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
       //*********************************************************+
        //DomandaDAO domandaDAO = new DomandaDAO();
        DomandaDAO domandaDAO = createDomandaDAO();
        //****************************************************
        List<Domanda> domande = domandaDAO.getDomandeNonRisposte();

        request.setAttribute("domande", domande);
        request.getRequestDispatcher("/DomandeBarista.jsp").forward(request, response);
    }
}
