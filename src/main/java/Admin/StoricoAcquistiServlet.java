// Servlet: StoricoAcquistiServlet
package Admin;

import Model.Acquisto;
import Model.AcquistoDAO;
import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/StoricoAcquistiServlet")
public class StoricoAcquistiServlet extends HttpServlet {
    private AcquistoDAO acquistoDAO;

    // Costruttore predefinito per l'esecuzione reale
    public StoricoAcquistiServlet() {
        this.acquistoDAO = new AcquistoDAO();
    }

    // Costruttore per test o configurazioni personalizzate
    public StoricoAcquistiServlet(AcquistoDAO acquistoDAO) {
        this.acquistoDAO = acquistoDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String email = request.getParameter("email");
        System.out.println(email);

        List<Acquisto> storico = acquistoDAO.getAcquistiByEmail(email);

        request.setAttribute("storico", storico);
        request.getRequestDispatcher("StoricoUtente.jsp").forward(request, response);
    }
}
