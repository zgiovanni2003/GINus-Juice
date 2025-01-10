package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public Utente findUtenteByEmailAndPassword(String email, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getConnection();
            String query = "SELECT * FROM Utente WHERE email = ? AND password_d = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Utente utente = new Utente();
                utente.setEmail(resultSet.getString("email"));
                utente.setNome(resultSet.getString("nome"));
                utente.setCognome(resultSet.getString("cognome"));
                utente.setPassword(resultSet.getString("password_d"));
                utente.setStato(resultSet.getBoolean("stato"));
                utente.setRuolo(resultSet.getString("ruolo"));
                return utente;
            }

            return null; // Utente non trovato
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) ConnectionPool.releaseConnection(connection);
        }
    }

    public boolean registerUser(String email, String nome, String cognome, String password_d, boolean stato, String ruolo) {
        Connection conn = null;
        PreparedStatement ps = null;
        stato=true;
        ruolo="utente";
        try {
            conn = ConnectionPool.getConnection();
            String query = "INSERT INTO Utente (email, nome, cognome, password_d, stato, ruolo) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setString(4, password_d);  // Password dovrebbe essere cifrata prima di essere salvata nel database
            ps.setBoolean(5, stato);
            ps.setString(6, ruolo);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) ConnectionPool.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteUserByEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionPool.getConnection();
            String query = "DELETE FROM Utente WHERE email = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) ConnectionPool.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected UtenteDAO getUtenteDAO() {
        return new UtenteDAO();
    }
    // Metodo in UtenteDAO per rimuovere un utente dal database
    public boolean rimuoviUtente(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getConnection();
            String query = "DELETE FROM Utente WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) ConnectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean aggiornaRuolo(String email, String nuovoRuolo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionPool.getConnection();
            String query = "UPDATE Utente SET ruolo = ? WHERE email = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nuovoRuolo);
            ps.setString(2, email);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) ConnectionPool.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Utente> getAllUtenti() {
        List<Utente> utenti = new ArrayList<>();
        String query = "SELECT * FROM Utente";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setPassword(rs.getString("password_d"));
                utente.setStato(rs.getBoolean("stato"));
                utente.setRuolo(rs.getString("ruolo"));
                utenti.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utenti;
    }


}

