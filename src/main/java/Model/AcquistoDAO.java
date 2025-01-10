package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public List<Acquisto> getAcquistiByEmail(String email) {
        List<Acquisto> acquisti = new ArrayList<>();
        String query = "SELECT * FROM acquisto WHERE email_utente = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Acquisto acquisto = new Acquisto();
                    acquisto.setId(rs.getInt("id_acquisto"));
                    acquisto.setDataAcquisto(rs.getTimestamp("data_acquisto"));
                    acquisto.setPrezzo(rs.getDouble("prezzo"));
                    acquisto.setProdottiComprati(rs.getString("prodotti_comprati"));
                    acquisti.add(acquisto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return acquisti;
    }

}
