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
                prodotto.setQuantita(resultSet.getInt("quantita"));
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
                    prodotto.setQuantita(resultSet.getInt("quantita"));
                    // Aggiungi altre informazioni come recensioni, ecc.
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodotto;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        String query = "INSERT INTO Prodotto (nome, descrizione, prezzo, quantita) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setString(2, prodotto.getDescrizione());
            preparedStatement.setDouble(3, prodotto.getPrezzo());
            preparedStatement.setInt(4, prodotto.getQuantita()); // Aggiunta gestione della quantità

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteProdottoById(int id) {
        String query = "DELETE FROM Prodotto WHERE id_prodotto = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProdotto(Prodotto prodotto) {
        String query = "UPDATE Prodotto SET nome = ?, prezzo = ?, descrizione = ?, quantita = ? WHERE id_prodotto = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setDouble(2, prodotto.getPrezzo());
            preparedStatement.setString(3, prodotto.getDescrizione());
            preparedStatement.setInt(4, prodotto.getQuantita()); // Aggiunto
            preparedStatement.setInt(5, prodotto.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean aggiornaQuantitaProdotto(int idProdotto, int quantitaDaRidurre) {
        String query = "UPDATE Prodotto SET quantita = quantita - ? WHERE id_prodotto = ? AND quantita >= ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, quantitaDaRidurre);
            preparedStatement.setInt(2, idProdotto);
            preparedStatement.setInt(3, quantitaDaRidurre);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
