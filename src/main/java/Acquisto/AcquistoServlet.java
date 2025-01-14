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
        System.out.println("boh");

        if (carrello == null || carrello.getProdotti().isEmpty()) {
            response.sendRedirect("Carrello.jsp");
            return;
        }

        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        boolean erroreQuantita = false;

        for (Map.Entry<Prodotto, Integer> entry : carrello.getProdotti().entrySet()) {
            Prodotto prodotto = entry.getKey();
            int quantitaRichiesta = entry.getValue();
            System.out.println("ciao io sto qui");

            // Controlla se la quantità richiesta supera quella disponibile
            Prodotto p= prodottoDAO.getProdottoById(prodotto.getId());
            int quantitaDisponibile=p.getQuantita();
            System.out.println(quantitaDisponibile);
            if (quantitaRichiesta > quantitaDisponibile) {
                erroreQuantita = true;
                session.setAttribute("erroreQuantita", "La quantità richiesta per il prodotto \""
                        + prodotto.getNome() + "\" supera la disponibilità attuale.");
                response.sendRedirect("Carrello.jsp");
                return;
            }
        }

        if (!erroreQuantita) {
            // Procedi con l'acquisto
            String prodottiComprati = carrello.getProdotti().toString();
            double totale = carrello.getProdotti().entrySet().stream()
                    .mapToDouble(entry -> entry.getKey().getPrezzo() * entry.getValue()).sum();

            Acquisto acquisto = new Acquisto(null, totale, prodottiComprati, utente.getEmail());
            AcquistoDAO acquistoDAO = new AcquistoDAO();

            if (acquistoDAO.salvaAcquisto(acquisto)) {
                session.removeAttribute("carrello");
                response.sendRedirect("ConfermaAcquisto.jsp");
            } else {
                response.sendRedirect("Carrello.jsp.jsp");
            }
        }
    }

}
