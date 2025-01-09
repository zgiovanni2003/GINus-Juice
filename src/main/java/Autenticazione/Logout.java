package Autenticazione;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/Logout"})
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 5L;

    public Logout() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidare la sessione dell'utente
        request.getSession().invalidate();

        // Redirect alla pagina principale (index.jsp)
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
