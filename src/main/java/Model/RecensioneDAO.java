package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

    // Metodo per ottenere tutte le recensioni per un prodotto dato l'ID
    public List<Recensione> getRecensioniByIdProdotto(int idProdotto) {
        List<Recensione> recensioni = new ArrayList<>();

        String query = "SELECT * FROM recensione WHERE id_prod = ?";  // Query per recuperare le recensioni per un prodotto

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idProdotto);  // Impostiamo l'ID del prodotto nella query
            ResultSet rs = stmt.executeQuery();

            // Iteriamo attraverso i risultati e creiamo gli oggetti Recensione
            while (rs.next()) {
                String emailUtente = rs.getString("email_ut");
                String corpoRecensione = rs.getString("corpo_recensione");
                Date dataRecensione = rs.getDate("data_rec");

                // Aggiungiamo la recensione alla lista
                recensioni.add(new Recensione(emailUtente, idProdotto, corpoRecensione, dataRecensione));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Gestione errori, puoi personalizzare la gestione delle eccezioni
        }

        return recensioni;
    }
}
