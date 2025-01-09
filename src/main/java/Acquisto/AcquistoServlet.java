package Acquisto;

import Model.Acquisto;
import Model.AcquistoDAO;
import Model.Carrello;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/AcquistoServlet")
public class AcquistoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello == null || carrello.getProdotti().isEmpty()) {
            response.sendRedirect("Carrello.jsp");
            return;
        }

        String emailUtente = (String) session.getAttribute("email");
        emailUtente="mario.rossi@email.com";
        String prodottiComprati = carrello.getProdotti().toString(); // Convertilo come necessario per il tuo database
        double totale = carrello.getProdotti().entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrezzo() * entry.getValue()).sum();

        // Crea un oggetto Acquisto
        Acquisto acquisto = new Acquisto(null, totale, prodottiComprati, emailUtente);

        // Usa il DAO per salvare l'acquisto
        AcquistoDAO acquistoDAO = new AcquistoDAO();
        boolean successo = acquistoDAO.salvaAcquisto(acquisto);

        if (successo) {
            session.removeAttribute("carrello"); // Svuota il carrello dopo l'acquisto
            response.sendRedirect("ConfermaAcquisto.jsp");
        } else {
            response.sendRedirect("ErroreAcquisto.jsp");
        }
    }
}
