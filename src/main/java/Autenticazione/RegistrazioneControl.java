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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero dei parametri dalla richiesta
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String password = request.getParameter("password_hash");



        // Validazione dell'email
        if (email == null || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            request.setAttribute("errore", "Errore nella registrazione. Verifica i dati inseriti.");
            request.getRequestDispatcher("/Registrazione.jsp").forward(request, response);
            return;
        }

        // Creazione di un oggetto UtenteDAO per la gestione del database
        UtenteDAO utenteDAO = new UtenteDAO();
        boolean isRegistered = false;

        if (utenteDAO.userExists(email)) { // Metodo che verifica se l'utente esiste
            request.setAttribute("errore", "Email già esistente.");
            request.setAttribute("emailEsistente", true); // Attributo specifico per il popup
            request.getRequestDispatcher("/Registrazione.jsp").forward(request, response);
            return; // Interrompe l'esecuzione
        }


        // Controllo che i dati non siano vuoti e che l'utente non esista già
        if (email != null && !email.isEmpty() && nome != null && !nome.isEmpty() && cognome != null && !cognome.isEmpty() && password != null && !password.isEmpty()) {
            // Verifica se l'utente è già registrato
            isRegistered = utenteDAO.registerUser(email, nome, cognome, password, true, "utente");

            if (!password.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                request.setAttribute("errore", "La password deve contenere almeno 8 caratteri, una lettera maiuscola e un numero.");
                request.getRequestDispatcher("/Registrazione.jsp").forward(request, response);
                return; // Interrompe l'esecuzione per non procedere oltre
            }

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

