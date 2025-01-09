package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    public List<Prodotto> getAllProdotti() {
        System.out.println("ciaoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        List<Prodotto> prodotti = new ArrayList<>();
        String query = "SELECT * FROM Prodotto";  // Aggiungi la query corretta
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setId(resultSet.getInt("id_prodotto"));
                prodotto.setNome(resultSet.getString("nome"));
                prodotto.setPrezzo(resultSet.getDouble("prezzo"));
                prodotti.add(prodotto);
            }
            System.out.println(prodotti);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    public Prodotto getProdottoById(int id) {
        Prodotto prodotto = null;
        String query = "SELECT * FROM Prodotto WHERE id_prodotto = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    prodotto = new Prodotto();
                    prodotto.setId(resultSet.getInt("id_prodotto"));
                    prodotto.setNome(resultSet.getString("nome"));
                    prodotto.setPrezzo(resultSet.getDouble("prezzo"));
                    prodotto.setDescrizione(resultSet.getString("descrizione"));
                    // Aggiungi altre informazioni come recensioni, ecc.
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodotto;
    }
}
