package ChiediBarista;

import Model.RispostaDAO;
import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RispondiDomandaServlet")
public class RispondiDomandaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        int idDomanda = Integer.parseInt(request.getParameter("id_domanda"));
        String risposta = request.getParameter("risposta");
        Utente barista = (Utente) request.getSession().getAttribute("utente");
        String emailBarista = barista.getEmail();

        RispostaDAO rispostaDAO = new RispostaDAO();
        if (rispostaDAO.inserisciRisposta(risposta, idDomanda, emailBarista)) {
            response.sendRedirect("VisualizzaDomandeServlet");
        } else {
            response.sendRedirect("DomandeBarista.jsp?error=risposta");
        }
    }
}

