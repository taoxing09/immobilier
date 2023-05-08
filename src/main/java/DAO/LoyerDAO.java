package DAO;

import java.sql.*;
import java.util.*;
import java.util.Date;

import models.*;

public class LoyerDAO {

    private static final String INSERT_LOYER_SQL = "INSERT INTO loyer (id_bien, id_locataire, date_debut, date_fin, montant) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_LOYER_BY_ID_SQL = "SELECT id_bien, id_locataire, date_debut, date_fin, montant FROM loyer WHERE id = ?;";
    private static final String SELECT_ALL_LOYERS_SQL = "SELECT * FROM loyer;";
    private static final String DELETE_LOYER_SQL = "DELETE FROM loyer WHERE id = ?;";
    private static final String UPDATE_LOYER_SQL = "UPDATE loyer SET id_bien = ?, id_locataire = ?, date_debut = ?, date_fin = ?, montant = ? WHERE id = ?;";

    public static void insertLoyer(Loyer loyer) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOYER_SQL)) {
            preparedStatement.setInt(1, loyer.getIdBien());
            preparedStatement.setInt(2, loyer.getIdLocataire());
            preparedStatement.setString(3, loyer.getDateDebut().toString());
            preparedStatement.setString(4, loyer.getDateFin().toString());
            preparedStatement.setDouble(5, loyer.getMontant());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }

    public static Loyer getLoyer(int id) {
        Loyer loyer = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOYER_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idBien = resultSet.getInt("id_bien");
                int idLocataire = resultSet.getInt("id_locataire");
                String dateDebut = resultSet.getString("date_debut");
                String dateFin = resultSet.getString("date_fin");
                float montant = resultSet.getFloat("montant");
                Date dateDebutObj = new Date(dateDebut);
                Date dateFinObj = new Date(dateFin);
                loyer = new Loyer(id, idBien, idLocataire, dateDebutObj, dateFinObj, montant);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return loyer;
    }

    public static List<Loyer> getAllLoyers() {
        List<Loyer> loyers = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOYERS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idBien = resultSet.getInt("id_bien");
                int idLocataire = resultSet.getInt("id_locataire");
                String dateDebut = resultSet.getString("date_debut");
                String dateFin = resultSet.getString("date_fin");
                float montant = resultSet.getFloat("montant");
                Date dateDebutObj = new Date(dateDebut);
                Date dateFinObj = new Date(dateFin);
                loyers.add(new Loyer(id, idBien, idLocataire, dateDebutObj, dateFinObj, montant));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return loyers;
    }
    public static boolean deleteLoyer(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_LOYER_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public static boolean updateLoyer(Loyer loyer) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_LOYER_SQL);) {
            statement.setInt(1, loyer.getIdBien());
            statement.setInt(2, loyer.getIdLocataire());
            statement.setString(3, loyer.getDateDebut().toString());
            statement.setString(4, loyer.getDateFin().toString());
            statement.setDouble(5, loyer.getMontant());
            statement.setInt(6, loyer.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
