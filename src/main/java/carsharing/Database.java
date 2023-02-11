package carsharing;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/carsharing";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            System.err.println("Failed to get connection with db, check the url: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    public static void closePreparedStatement(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    public static void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void closeStatement(Statement state) throws SQLException {
        state.close();
    }

}