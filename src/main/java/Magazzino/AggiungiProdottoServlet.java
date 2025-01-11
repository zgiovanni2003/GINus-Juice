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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera i parametri del prodotto
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita")); // Recupero quantità

            System.out.println("Nome prodotto: " + nome);
            System.out.println("Descrizione prodotto: " + descrizione);
            System.out.println("Prezzo prodotto: " + prezzo);
            System.out.println("Quantità prodotto: " + quantita);

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
            System.out.println("Immagine salvata con successo.");

            // Salvataggio del prodotto nel database
            Prodotto prodotto = new Prodotto();
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita); // Imposta quantità

            ProdottoDAO prodottoDAO = new ProdottoDAO();
            prodottoDAO.aggiungiProdotto(prodotto);
            System.out.println("Prodotto salvato nel database.");

            // Aggiorna la lista dei prodotti nel contesto applicativo
            ServletContext context = request.getServletContext();
            ProdottoSingleton singleton = ProdottoSingleton.getInstance();
            singleton.getProdotti().add(prodotto);
            singleton.salvaProdottiNelContext(context);
            System.out.println("Lista dei prodotti aggiornata nel contesto applicativo.");

            // Redirect alla pagina di successo
            response.sendRedirect("Successo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore durante l'aggiunta del prodotto: " + e.getMessage());
        }
    }
}
