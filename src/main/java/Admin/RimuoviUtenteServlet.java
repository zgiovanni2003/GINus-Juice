// Servlet: RimuoviUtenteServlet
package Admin;

import Model.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RimuoviUtenteServlet")
public class RimuoviUtenteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        UtenteDAO utenteDAO = new UtenteDAO();
        boolean success = utenteDAO.rimuoviUtente(email);

        if (success) {
            response.sendRedirect("PannelloAdmin.jsp?success=1");
        } else {
            response.sendRedirect("PannelloAdmin.jsp?error=1");
        }
    }
}
