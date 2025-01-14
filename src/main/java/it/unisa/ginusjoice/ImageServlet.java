package it.unisa.ginusjoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera il nome del file dall'URL
        String imageName = request.getPathInfo() != null ? request.getPathInfo().substring(1) : null;
       // System.out.println("Richiesta immagine: " + imageName);

        // Percorso statico alla directory delle immagini in webapp
        String imagePath = getServletContext().getRealPath("/") + "../../src/main/webapp/images/";
        //System.out.println("Percorso assoluto directory immagini (webapp): " + imagePath);

        if (imageName == null || imageName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nessun nome immagine fornito.");
            //System.out.println("Errore: Nessun nome immagine fornito.");
            return;
        }

        // File immagine
        File imageFile = new File(imagePath, imageName);
        System.out.println("Percorso assoluto file immagine: " + imageFile.getAbsolutePath());

        // Verifica se il file esiste
        if (!imageFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Immagine non trovata.");
           // System.out.println("Errore: Immagine non trovata nel percorso " + imageFile.getAbsolutePath());
            return;
        }

        // Configura la risposta HTTP
        response.setContentType(getServletContext().getMimeType(imageFile.getName()));
        response.setContentLengthLong(imageFile.length());

        // Scrive il file immagine nella risposta
        try (FileInputStream inputStream = new FileInputStream(imageFile);
             OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            //System.out.println("Immagine inviata correttamente.");
        }
    }
}
