package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static String url = "jdbc:mysql://localhost/agence_immobiliere";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
    }

    public static void printSQLException(SQLException e) {
        for (Throwable ex : e) {
            if (ex instanceof SQLException) {
                ex.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) ex).getSQLState());
                System.err.println("Error Code: " + ((SQLException) ex).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
