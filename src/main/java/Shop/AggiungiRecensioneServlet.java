package Shop;

import Model.RecensioneDAO;
import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/AggiungiRecensioneServlet")
public class AggiungiRecensioneServlet extends HttpServlet {
    private static final List<String> PAROLE_SCURRILI = Arrays.asList("puttana", "troia", "zoccola");


    protected RecensioneDAO createRecensioneDAO() {
        return new RecensioneDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        String corpoRecensione = request.getParameter("corpoRecensione");
        Utente u=(Utente) request.getSession().getAttribute("utente");
        String emailUtente = u.getEmail();

        // Controllo per parole scurrili
        for (String parola : PAROLE_SCURRILI) {
            if (corpoRecensione.toLowerCase().contains(parola)) {
                response.sendRedirect("ErroreRecensione.jsp");
                return;
            }
        }

        // Inserimento della recensione nel database
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        boolean success = recensioneDAO.inserisciRecensione(emailUtente, idProdotto, corpoRecensione);

        if (success) {
            response.sendRedirect("VisualizzaDettagliServlet?id=" + idProdotto);
        } else {
            response.sendRedirect("ErroreInserimento.jsp");
        }
    }
}
