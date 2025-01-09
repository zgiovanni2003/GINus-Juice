package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AcquistoDAO {

    public boolean salvaAcquisto(Acquisto acquisto) {
        String query = "INSERT INTO acquisto (data_acquisto, prezzo, prodotti_comprati, email_utente) VALUES (CURRENT_TIMESTAMP, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, acquisto.getPrezzo());
            preparedStatement.setString(2, acquisto.getProdottiComprati());
            preparedStatement.setString(3, acquisto.getEmailUtente());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
