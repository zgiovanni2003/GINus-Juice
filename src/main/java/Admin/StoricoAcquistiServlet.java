// Servlet: StoricoAcquistiServlet
package Admin;

import Model.Acquisto;
import Model.AcquistoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StoricoAcquistiServlet")
public class StoricoAcquistiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        AcquistoDAO acquistoDAO = new AcquistoDAO();
        List<Acquisto> storico = acquistoDAO.getAcquistiByEmail(email);

        request.setAttribute("storico", storico);
        request.getRequestDispatcher("StoricoUtente.jsp").forward(request, response);
    }
}
