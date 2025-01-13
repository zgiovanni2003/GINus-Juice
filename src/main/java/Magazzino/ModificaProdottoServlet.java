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
import java.nio.file.Files;
import java.nio.file.Paths;



@WebServlet("/ModificaProdottoServlet")
@MultipartConfig
public class ModificaProdottoServlet extends HttpServlet {

    private ProdottoDAO prodottoDAO = new ProdottoDAO();
    private ServletContext servletContext;

    // Metodo per iniettare un DAO mock durante i test
    public void setProdottoDAO(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    // Metodo per iniettare un ServletContext simulato durante i test
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext != null ? this.servletContext : super.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String descrizione = request.getParameter("descrizione");
            int quantita = Integer.parseInt(request.getParameter("quantita"));

            Part immaginePart = request.getPart("immagine");
            String nuovoNomeImmagine = null;

            String uploadDir = getServletContext().getRealPath("/") + "../../src/main/webapp/images/";

            String oldImageName = request.getParameter("oldImageName");

            if (immaginePart != null && immaginePart.getSize() > 0) {
                nuovoNomeImmagine = nome + ".jpg";
                File immagineFile = new File(uploadDir, nuovoNomeImmagine);

                if (oldImageName != null && !oldImageName.equals(nuovoNomeImmagine)) {
                    Files.deleteIfExists(Paths.get(uploadDir, oldImageName));
                }

                immaginePart.write(immagineFile.getAbsolutePath());
            } else if (oldImageName != null) {
                String nuovoNomeFileImmagine = nome + ".jpg";
                File oldImageFile = new File(uploadDir, oldImageName);
                File newImageFile = new File(uploadDir, nuovoNomeFileImmagine);

                if (oldImageFile.exists()) {
                    oldImageFile.renameTo(newImageFile);
                }

                nuovoNomeImmagine = nuovoNomeFileImmagine;
            }

            boolean aggiornato = prodottoDAO.updateProdotto(new Prodotto(id, nome, prezzo, descrizione, quantita));

            if (aggiornato) {
                ProdottoSingleton prodottoSingleton = ProdottoSingleton.getInstance();
                prodottoSingleton.getProdotti().stream()
                        .filter(p -> p.getId() == id)
                        .forEach(p -> {
                            p.setNome(nome);
                            p.setPrezzo(prezzo);
                            p.setDescrizione(descrizione);
                            p.setQuantita(quantita);
                        });

                getServletContext().setAttribute("prodotti", prodottoSingleton.getProdotti());
            }

            response.sendRedirect("Magazzino.jsp");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dati non validi.");
        }
    }
}



/*
@WebServlet("/ModificaProdottoServlet")
@MultipartConfig
public class ModificaProdottoServlet extends HttpServlet {
  //***************************************************+
    private ProdottoDAO prodottoDAO = new ProdottoDAO();
    private ServletContext servletContext;

    // Metodo per iniettare un DAO mock durante i test
    public void setProdottoDAO(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    // Metodo per iniettare un ServletContext simulato durante i test
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
//***********************************************************+
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera i parametri dal form
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String descrizione = request.getParameter("descrizione");
            int quantita = Integer.parseInt(request.getParameter("quantita")); // Nuovo parametro per la quantità

            // Debug: Stampa i parametri
            System.out.println("ID prodotto: " + id);
            System.out.println("Nome prodotto: " + nome);
            System.out.println("Prezzo prodotto: " + prezzo);
            System.out.println("Descrizione prodotto: " + descrizione);
            System.out.println("Quantità prodotto: " + quantita);

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
                    Files.deleteIfExists(Paths.get(uploadDir, oldImageName));
                    System.out.println("Vecchia immagine eliminata: " + oldImageName);
                }

                immaginePart.write(immagineFile.getAbsolutePath());
                System.out.println("Nuova immagine scritta: " + immagineFile.getAbsolutePath());
            } else if (oldImageName != null) {
                String nuovoNomeFileImmagine = nome + ".jpg";
                File oldImageFile = new File(uploadDir, oldImageName);
                File newImageFile = new File(uploadDir, nuovoNomeFileImmagine);

                System.out.println("Vecchio nome immagine: " + oldImageFile.getName());
                System.out.println("Nuovo nome immagine: " + newImageFile.getName());

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

                nuovoNomeImmagine = nuovoNomeFileImmagine;
            }

            // Aggiorna il prodotto nel database
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            boolean aggiornato = prodottoDAO.updateProdotto(new Prodotto(id, nome, prezzo, descrizione, quantita)); // Aggiornato con la quantità

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
                            p.setQuantita(quantita); // Aggiornato con la quantità
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
*/