package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomandaDAO {

    public boolean inserisciDomanda(String messaggio, String emailUtente) {
        String query = "INSERT INTO domanda (corpo_messaggio, email_utente) VALUES (?, ?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, messaggio);
            preparedStatement.setString(2, emailUtente);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Domanda> getDomandeNonRisposte() {
        List<Domanda> domande = new ArrayList<>();
        String query = "SELECT * FROM domanda WHERE stato = FALSE";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Domanda domanda = new Domanda();
                domanda.setId(resultSet.getInt("id_domanda"));
                domanda.setMessaggio(resultSet.getString("corpo_messaggio"));
                domanda.setEmailUtente(resultSet.getString("email_utente"));
                domanda.setStato(resultSet.getBoolean("stato"));
                domande.add(domanda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return domande;
    }
}
