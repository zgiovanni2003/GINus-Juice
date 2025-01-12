package Acquisto;

import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

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

        Utente utente = (Utente) session.getAttribute("utente");
        String emailUtente = utente.getEmail();
        if (emailUtente == null || emailUtente.isEmpty()) {
            response.sendRedirect("Login.jsp");
            return;
        }

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        boolean quantitaInsufficiente = false;
        String nomeProdottoFallito = "";

        for (Map.Entry<Prodotto, Integer> entry : carrello.getProdotti().entrySet()) {
            Prodotto prodotto = entry.getKey();
            int quantita = entry.getValue();

            if (!prodottoDAO.aggiornaQuantitaProdotto(prodotto.getId(), quantita)) {
                quantitaInsufficiente = true;
                nomeProdottoFallito = prodotto.getNome();
                break;
            }
        }

        if (quantitaInsufficiente) {
            session.setAttribute("erroreCarrello", "Quantità non disponibile per il prodotto: " + nomeProdottoFallito);
            response.sendRedirect("Carrello.jsp");
            return;
        }

        // Salva l'acquisto
        String prodottiComprati = carrello.getProdotti().toString();
        double totale = carrello.getProdotti().entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrezzo() * entry.getValue()).sum();

        Acquisto acquisto = new Acquisto(null, totale, prodottiComprati, emailUtente);
        AcquistoDAO acquistoDAO = new AcquistoDAO();
        boolean successo = acquistoDAO.salvaAcquisto(acquisto);

        if (successo) {
            session.removeAttribute("carrello");
            response.sendRedirect("ConfermaAcquisto.jsp");
        } else {
            response.sendRedirect("ErroreAcquisto.jsp");
        }
    }
}
