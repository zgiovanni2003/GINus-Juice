package Magazzino;

import Model.Prodotto;
import Model.ProdottoDAO;
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
import java.util.List;

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
                // Recupera la lista aggiornata dei prodotti dal database
                List<Prodotto> prodottiAggiornati = prodottoDAO.getAllProdotti();

                // Aggiorna la lista dei prodotti nel contesto dell'applicazione
                getServletContext().setAttribute("prodotti", prodottiAggiornati);
                System.out.println("Lista dei prodotti aggiornata nel contesto dell'applicazione.");
            }

            response.sendRedirect("Magazzino.jsp");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dati non validi.");
        }
    }
}
