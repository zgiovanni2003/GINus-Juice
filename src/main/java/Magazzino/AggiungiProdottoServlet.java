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
import java.util.List;

@WebServlet("/AggiungiProdottoServlet")
@MultipartConfig
public class AggiungiProdottoServlet extends HttpServlet {

    //private ProdottoDAO prodottoDAO = new ProdottoDAO(); // Inizializzazione predefinita

    // Metodo setter per iniettare un DAO mock durante i test
    /*
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
            response.sendRedirect("Magazzino.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ErroreInserimento.jsp");
        }
    }

     */



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera i parametri del prodotto
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));

            // Percorso della cartella 'images'
            String uploadDir = getServletContext().getRealPath("/") + "../../src/main/webapp/images/";

            // Creazione della cartella 'images' se non esiste
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            // Recupero del file immagine
            Part filePart = request.getPart("immagine");
            String fileName = nome.replaceAll("\\s+", "_") + ".jpg";
            String filePath = uploadDir + File.separator + fileName;

            // Scrittura del file nella cartella 'images'
            filePart.write(filePath);

            // Salvataggio del prodotto nel database
            Prodotto prodotto = new Prodotto();
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);

            ProdottoDAO prodottoDAO = new ProdottoDAO();
            prodottoDAO.aggiungiProdotto(prodotto);

            // Aggiorna la lista dei prodotti nel contesto dell'applicazione
            ServletContext context = getServletContext();
            List<Prodotto> prodottiAggiornati = prodottoDAO.getAllProdotti();
            context.setAttribute("prodotti", prodottiAggiornati);

            // Redirect alla pagina di successo
            response.sendRedirect("Successo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ErroreInserimento.jsp");
        }
    }

}
