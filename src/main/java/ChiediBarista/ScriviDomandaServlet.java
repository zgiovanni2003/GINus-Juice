package ChiediBarista;

import Model.DomandaDAO;
import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ScriviDomandaServlet")
public class ScriviDomandaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String messaggio = request.getParameter("messaggio");
        Utente u = (Utente) request.getSession().getAttribute("utente");
        String emailUtente = u.getEmail();
        DomandaDAO domandaDAO = new DomandaDAO();
        if (domandaDAO.inserisciDomanda(messaggio, emailUtente)) {
            response.sendRedirect("index.jsp?success=domanda");
        } else {
            response.sendRedirect("index.jsp?error=domanda");
        }
    }
}
