package Magazzino;

import Model.Prodotto;
import Model.ProdottoDAO;
import Shop.ProdottoSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/ModificaProdottoServlet")
@MultipartConfig
public class ModificaProdottoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera i parametri dal form
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String descrizione = request.getParameter("descrizione");

            // Debug: Stampa i parametri
            System.out.println("ID prodotto: " + id);
            System.out.println("Nome prodotto: " + nome);
            System.out.println("Prezzo prodotto: " + prezzo);
            System.out.println("Descrizione prodotto: " + descrizione);

            // Recupera la parte dell'immagine
            Part immaginePart = request.getPart("immagine");
            String nuovoNomeImmagine = null;

            // Percorso statico alla cartella images
            String uploadDir = getServletContext().getRealPath("/") + "../../src/main/webapp/images/";

            // Ottieni il nome dell'immagine esistente (se presente)
            String oldImageName = request.getParameter("oldImageName");
            System.out.println("Nome immagine esistente: " + oldImageName);

            // Se viene caricata una nuova immagine, la salviamo
            if (immaginePart != null && immaginePart.getSize() > 0) {
                nuovoNomeImmagine = nome + ".jpg";  // Nuovo nome dell'immagine
                File immagineFile = new File(uploadDir, nuovoNomeImmagine);

                System.out.println("Nuovo nome immagine (con caricamento): " + nuovoNomeImmagine);

                // Se il nome del prodotto è cambiato e l'immagine esistente è diversa, rinominiamo l'immagine
                if (oldImageName != null && !oldImageName.equals(nuovoNomeImmagine)) {
                    // Rimuoviamo la vecchia immagine
                    Files.deleteIfExists(Paths.get(uploadDir, oldImageName));
                    System.out.println("Vecchia immagine eliminata: " + oldImageName);
                }

                // Scriviamo la nuova immagine nella cartella
                immaginePart.write(immagineFile.getAbsolutePath());
                System.out.println("Nuova immagine scritta: " + immagineFile.getAbsolutePath());
            } else {
                // Se non viene fornita una nuova immagine, dobbiamo rinominare l'immagine esistente
                if (oldImageName != null) {
                    // Rinomina l'immagine esistente con il nuovo nome del prodotto
                    String nuovoNomeFileImmagine = nome + ".jpg";
                    File oldImageFile = new File(uploadDir, oldImageName);
                    File newImageFile = new File(uploadDir, nuovoNomeFileImmagine);

                    // Debug: Rinomina il file immagine
                    System.out.println("Vecchio nome immagine: " + oldImageFile.getName());
                    System.out.println("Nuovo nome immagine: " + newImageFile.getName());

                    // Rinomina il file immagine
                    if (oldImageFile.exists()) {
                        boolean rinominato = oldImageFile.renameTo(newImageFile);
                        if (rinominato) {
                            System.out.println("Immagine rinominata con successo");
                        } else {
                            System.out.println("Errore durante il rinominare l'immagine");
                        }
                    } else {
                        System.out.println("Errore: il file immagine " + oldImageName + " non esiste.");
                    }

                    nuovoNomeImmagine = nuovoNomeFileImmagine;  // Impostiamo il nuovo nome per l'immagine
                }
            }

            // Aggiorna il prodotto nel database (senza toccare l'immagine)
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            boolean aggiornato = prodottoDAO.updateProdotto(new Prodotto(id, nome, prezzo, descrizione));

            // Debug: Verifica se il prodotto è stato aggiornato
            System.out.println("Prodotto aggiornato nel database: " + aggiornato);

            if (aggiornato) {
                // Aggiorna il contesto
                ProdottoSingleton prodottoSingleton = ProdottoSingleton.getInstance();
                prodottoSingleton.getProdotti().stream()
                        .filter(p -> p.getId() == id)
                        .forEach(p -> {
                            p.setNome(nome);
                            p.setPrezzo(prezzo);
                            p.setDescrizione(descrizione);
                        });

                getServletContext().setAttribute("prodotti", prodottoSingleton.getProdotti());
                System.out.println("Prodotto aggiornato nel contesto.");
            }

            // Reindirizza alla pagina del magazzino
            response.sendRedirect("Magazzino.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dati non validi.");
        }
    }
}
