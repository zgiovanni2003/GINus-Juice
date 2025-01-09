package Autenticazione;



import Model.Utente;
import Model.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Utente utente = utenteDAO.findUtenteByEmailAndPassword(email, password);

            if (utente != null) {
                // Login riuscito
                request.getSession().setAttribute("utente", utente);
                response.sendRedirect("index.jsp");
            } else {
                // Login fallito
                request.setAttribute("errorMessage", "Credenziali non valide");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Errore durante il login", e);
        }
    }
}


