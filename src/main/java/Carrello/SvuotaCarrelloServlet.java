package Carrello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.Carrello;

import java.io.IOException;

@WebServlet("/SvuotaCarrelloServlet")
public class SvuotaCarrelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni la sessione e il carrello
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        // Svuota il carrello se esiste
        if (carrello != null) {
            carrello.svuotaCarrello();
        }

        // Reindirizza alla pagina del carrello
        response.sendRedirect("Carrello.jsp");
    }
}
