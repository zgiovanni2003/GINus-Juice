package Carrello;

import Model.Carrello;
import Model.Prodotto;
import Model.ProdottoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Otteniamo la sessione
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        // Se il carrello non esiste, lo inizializziamo
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
            System.out.println("STO CREANDO IL CARRELLO  E LO METTO IN SESSIONE");
        }

        // Recuperiamo i parametri dalla richiesta
        String action = request.getParameter("action");
        System.out.println("l'action vale   "+ action);
        int prodottoId = Integer.parseInt(request.getParameter("id"));

        // Troviamo il prodotto tramite il DAO
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto = prodottoDAO.getProdottoById(prodottoId);
        System.out.println("il prodotto recuperarto dalla servlet è : " + prodotto);

        // Eseguiamo l'azione richiesta
        if (action != null && prodotto != null) {
            switch (action) {
                case "aggiungi":
                    System.out.println("sto eseguendo il primo action e action vale : " + action);
                    carrello.aggiungiProdotto(prodotto);
                    break;
                case "diminuisci":
                    carrello.diminuisciQuantita(prodotto);
                    break;
                case "acquista":
                    carrello.aggiungiProdotto(prodotto); // Comportamento identico ad "aggiungi"
                    break;
            }
        }

        // Reindirizziamo alla pagina del carrello
        response.sendRedirect("Carrello.jsp");
    }
}
