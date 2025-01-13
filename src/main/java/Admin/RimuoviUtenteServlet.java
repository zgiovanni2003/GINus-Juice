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
    private UtenteDAO utenteDAO;

    // Costruttore predefinito
    public RimuoviUtenteServlet() {
        this.utenteDAO = new UtenteDAO();
    }

    // Costruttore per iniezione di dipendenze (utile per il testing)
    public RimuoviUtenteServlet(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String email = request.getParameter("email");

            boolean success = utenteDAO.rimuoviUtente(email);

            if (success) {
                response.sendRedirect("PannelloAdmin.jsp?success=1");
            } else {
                response.sendRedirect("PannelloAdmin.jsp?error=1");
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la gestione della richiesta", e);
        }
    }
}
