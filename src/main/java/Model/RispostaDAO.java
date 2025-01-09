package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RispostaDAO {

    // Inserisce una risposta nel database
    public boolean inserisciRisposta(String messaggio, int idDomanda, String emailBarista) {
        String query = "INSERT INTO risposta (corpo_messaggio, email_barista) VALUES (?, ?)";
        String updateDomandaQuery = "UPDATE domanda SET stato = TRUE WHERE id_domanda = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(query);
             PreparedStatement updateStmt = connection.prepareStatement(updateDomandaQuery)) {

            // Inserimento risposta
            insertStmt.setString(1, messaggio);
            insertStmt.setString(2, emailBarista);
            insertStmt.executeUpdate();

            // Aggiorna lo stato della domanda
            updateStmt.setInt(1, idDomanda);
            return updateStmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Recupera tutte le risposte di un utente
    public List<Risposta> getRisposteByEmailUtente(String emailUtente) {
        List<Risposta> risposte = new ArrayList<>();
        String query = "SELECT r.*, d.corpo_messaggio AS domanda FROM risposta r " +
                "JOIN domanda d ON d.id_domanda = r.id_risposta " +
                "WHERE d.email_utente = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, emailUtente);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Risposta risposta = new Risposta();
                    risposta.setId(resultSet.getInt("id_risposta"));
                    risposta.setMessaggio(resultSet.getString("corpo_messaggio"));
                    risposta.setDataRicezione(resultSet.getTimestamp("data_rec").toLocalDateTime());
                    risposta.setEmailBarista(resultSet.getString("email_barista"));
                    risposta.setIdDomanda(resultSet.getInt("id_risposta"));
                    risposte.add(risposta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return risposte;
    }
}
