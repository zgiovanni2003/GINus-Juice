package ChiediBarista;

import Model.Risposta;
import Model.RispostaDAO;

import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/VisualizzaRisposteServlet")
public class VisualizzaRisposteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
   //***********************************************+
    protected RispostaDAO createRispostaDAO() {
        return new RispostaDAO();
    }
    //********************************************************
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera l'email dell'utente dalla sessione
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String email= utente.getEmail();
        // Controllo che l'utente sia autenticato
        if (email == null) {
            response.sendRedirect("Login.jsp?error=not_logged_in");
            return;
        }

        // Recupera le risposte dal database tramite il DAO
      //*****************************************************+
       // RispostaDAO rispostaDAO = new RispostaDAO();
        RispostaDAO rispostaDAO = createRispostaDAO();
        //****************************************************
        List<Risposta> risposte = rispostaDAO.getRisposteByEmailUtente(email);

        // Passa la lista di risposte alla pagina JSP
        request.setAttribute("risposte", risposte);
        request.getRequestDispatcher("/VisualizzaRisposte.jsp").forward(request, response);
    }
}
