package Autenticazione;

import Model.UtenteDAO;
import Model.Utente;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/RegistrazioneControl")
public class RegistrazioneControl extends HttpServlet {

    // Metodo doPost per gestire la registrazione dell'utente
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero dei parametri dalla richiesta
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String password = request.getParameter("password_hash");

        // Creazione di un oggetto UtenteDAO per la gestione del database
        UtenteDAO utenteDAO = new UtenteDAO();
        boolean isRegistered = false;

        // Controllo che i dati non siano vuoti e che l'utente non esista già
        if (email != null && !email.isEmpty() && nome != null && !nome.isEmpty() && cognome != null && !cognome.isEmpty() && password != null && !password.isEmpty()) {
            // Verifica se l'utente è già registrato
            isRegistered = utenteDAO.registerUser(email, nome, cognome, password, true, "utente");
        }

        // Se l'utente è stato registrato correttamente, eseguo un redirect alla pagina di login o success
        if (isRegistered) {
            response.sendRedirect("Login.jsp");  // Puoi cambiare questa URL con la pagina che preferisci
        } else {
            // Altrimenti, invio un errore di registrazione
            request.setAttribute("errore", "Errore nella registrazione. Verifica i dati inseriti.");
            request.getRequestDispatcher("/Registrazione.jsp").forward(request, response);
        }
    }
}

