package Magazzino;

import Model.Prodotto;
import Model.ProdottoDAO;
import Shop.ProdottoSingleton;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/AggiungiProdottoServlet")
@MultipartConfig
public class AggiungiProdottoServlet extends HttpServlet {

    private ProdottoDAO prodottoDAO = new ProdottoDAO(); // Inizializzazione predefinita

    // Metodo setter per iniettare un DAO mock durante i test
    public void setProdottoDAO(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera i parametri del prodotto
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));

            if (nome == null || descrizione==null|| prezzo<=0 || quantita<=0) {
                response.sendRedirect("ErroreInserimento.jsp");
                return;
            }
            // Salvataggio del prodotto nel database
            Prodotto prodotto = new Prodotto();
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);

            prodottoDAO.aggiungiProdotto(prodotto);

            // Redirect alla pagina di successo
            response.sendRedirect("Successo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ErroreInserimento.jsp");
        }
    }
}
