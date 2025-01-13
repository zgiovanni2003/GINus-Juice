package Admin;

import Model.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AggiornaRuoloServlet")
public class AggiornaRuoloServlet extends HttpServlet {
    private UtenteDAO utenteDAO;

    // Costruttore predefinito
    public AggiornaRuoloServlet() {
        this.utenteDAO = new UtenteDAO();
    }

    // Costruttore per iniezione di dipendenze (utile per il testing)
    public AggiornaRuoloServlet(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String email = request.getParameter("email");
            String ruolo = request.getParameter("ruolo");

            boolean success = utenteDAO.aggiornaRuolo(email, ruolo);

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
