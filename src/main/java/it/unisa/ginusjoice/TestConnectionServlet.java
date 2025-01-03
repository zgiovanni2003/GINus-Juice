package it.unisa.ginusjoice;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/TestConnectionServlet")
public class TestConnectionServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cineteca";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                // Assicura che il driver venga caricato
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    if (connection != null && !connection.isClosed()) {
                        out.println("<h1>Connection to the database was successful!</h1>");
                    } else {
                        out.println("<h1>Connection to the database failed.</h1>");
                    }
                }
            } catch (ClassNotFoundException e) {
                out.println("<h1>Class not found</h1>");
                out.println("<p>" + e.getMessage() + "</p>");
            }
            catch (SQLException e) {
                out.println("<h1>Error connecting to the database</h1>");
                out.println("<p>" + e.getMessage() + "</p>");
            }
        }
    }
}
