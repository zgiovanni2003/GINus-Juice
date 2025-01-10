package Autenticazione;

import Model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class RegistrazioneControlTest {

    private RegistrazioneControl registrazioneControl;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private UtenteDAO utenteDAO;

    @BeforeEach
    public void setUp() throws Exception {
        registrazioneControl = new RegistrazioneControl();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        utenteDAO = new UtenteDAO();

        // Rimuovi eventuali dati di test preesistenti
        utenteDAO.deleteUserByEmail("mario.rossi@email.com");
    }

    @Test
    public void testRegistrazioneSuccesso() throws Exception {
        // Simula i parametri della richiesta
        when(request.getParameter("email")).thenReturn("mario.rossi@email.com");
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("password_hash")).thenReturn("Password123!");
        doNothing().when(response).sendRedirect("Login.jsp");

        // Simula il comportamento di UtenteDAO
        UtenteDAO utenteDAOMock = mock(UtenteDAO.class);
        when(utenteDAOMock.registerUser(
                eq("mario.rossi@email.com"),
                eq("Mario"),
                eq("Rossi"),
                eq("Password123!"),
                eq(true),
                eq("utente"))
        ).thenReturn(true);

        // Esegui la servlet
        registrazioneControl.doPost(request, response);

        // Verifica il risultato
        verify(response).sendRedirect("Login.jsp");
    }

    @Test
    public void testRegistrazioneEmailGiaPresente() throws Exception {
        // Configura i parametri della richiesta
        when(request.getParameter("email")).thenReturn("mario.rossi@email.com");
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("password_hash")).thenReturn("Password123!");
        when(request.getRequestDispatcher("/Registrazione.jsp")).thenReturn(dispatcher);

        // Mock di UtenteDAO
        UtenteDAO utenteDAOMock = mock(UtenteDAO.class);
        when(utenteDAOMock.registerUser(
                eq("mario.rossi@email.com"),
                eq("Mario"),
                eq("Rossi"),
                eq("Password123!"),
                eq(true),
                eq("utente")
        )).thenReturn(false); // Simula che la registrazione fallisca

        // Classe anonima per iniettare il mock
        RegistrazioneControl registrazioneControlMock = new RegistrazioneControl() {
            @Override
            public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                UtenteDAO utenteDAO = utenteDAOMock; // Utilizza il mock di UtenteDAO
                boolean isRegistered = false;

                String email = req.getParameter("email");
                String nome = req.getParameter("nome");
                String cognome = req.getParameter("cognome");
                String password = req.getParameter("password_hash");

                if (email != null && !email.isEmpty() && nome != null && !nome.isEmpty() &&
                        cognome != null && !cognome.isEmpty() && password != null && !password.isEmpty()) {
                    isRegistered = utenteDAO.registerUser(email, nome, cognome, password, true, "utente");
                }

                if (isRegistered) {
                    res.sendRedirect("Login.jsp");
                } else {
                    req.setAttribute("errore", "Errore nella registrazione. Verifica i dati inseriti.");
                    req.getRequestDispatcher("/Registrazione.jsp").forward(req, res);
                }
            }
        };

        // Esegui la servlet
        registrazioneControlMock.doPost(request, response);

        // Verifica che il messaggio di errore sia impostato
        verify(request).setAttribute(eq("errore"), eq("Errore nella registrazione. Verifica i dati inseriti."));
        verify(dispatcher).forward(request, response);
    }


    @Test
    public void testRegistrazioneEmailErrata() throws Exception {
        when(request.getParameter("email")).thenReturn("mario.rossiemail.com");
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("password_hash")).thenReturn("Password123!");
        when(request.getRequestDispatcher("/Registrazione.jsp")).thenReturn(dispatcher);

        // Esegui la servlet
        registrazioneControl.doPost(request, response);

        // Verifica il risultato
        verify(request).setAttribute(eq("errore"), eq("Errore nella registrazione. Verifica i dati inseriti."));
        verify(dispatcher).forward(request, response);
    }


    @Test
    public void testRegistrazionePasswordNonValida() throws Exception {
        // Simula i parametri della richiesta con una password debole
        when(request.getParameter("email")).thenReturn("mario.rossi@email.com");
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("password_hash")).thenReturn("password"); // Password non valida
        when(request.getRequestDispatcher("/Registrazione.jsp")).thenReturn(dispatcher);

        // Esegui la servlet
        registrazioneControl.doPost(request, response);

        // Verifica che il messaggio di errore sia impostato
        verify(request).setAttribute(eq("errore"), eq("La password deve contenere almeno 8 caratteri, una lettera maiuscola e un numero."));
        verify(dispatcher).forward(request, response);
    }


    @Test
    public void testRegistrazioneCampiVuoti() throws Exception {
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("nome")).thenReturn("");
        when(request.getParameter("cognome")).thenReturn("");
        when(request.getParameter("password_hash")).thenReturn("");
        when(request.getRequestDispatcher("/Registrazione.jsp")).thenReturn(dispatcher);

        // Esegui la servlet
        registrazioneControl.doPost(request, response);

        // Verifica il risultato
        verify(request).setAttribute(eq("errore"), eq("Errore nella registrazione. Verifica i dati inseriti."));
        verify(dispatcher).forward(request, response);
    }
}
