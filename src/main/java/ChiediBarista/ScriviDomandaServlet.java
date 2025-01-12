package ChiediBarista;

import Model.DomandaDAO;
import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ScriviDomandaServlet")
public class ScriviDomandaServlet extends HttpServlet {
   //**************************************************
    //aggiunta di sd
   private DomandaDAO domandaDAO;

    public ScriviDomandaServlet() {
        this.domandaDAO = new DomandaDAO();
    }

    public ScriviDomandaServlet(DomandaDAO domandaDAO) {
        this.domandaDAO = domandaDAO;
    }
//*************************************************************





    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {




        String messaggio = request.getParameter("messaggio");
        Utente u = (Utente) request.getSession().getAttribute("utente");
        //*************************************************************
        if (u == null)  { response.sendRedirect("login.jsp"); return;}
        //*************************************************************
        String emailUtente = u.getEmail();
        //************************************************************+
        //Rigo commentato da sd
        //DomandaDAO domandaDAO = new DomandaDAO();
        //**************************************************************
        if (domandaDAO.inserisciDomanda(messaggio, emailUtente)) {
            response.sendRedirect("ChiediAlBarista.jsp?success=domanda");
        } else {
            response.sendRedirect("ChiediAlBarista.jsp?error=domanda");
        }
    }
}
