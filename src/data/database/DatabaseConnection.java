package data.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;

//    private static final String SERVER = "192.168.133.150";
//    private static final String PORT = "3306";
//    private static final String DATABASE = "cookhub";
//    private static final String USER = "cookhub";
//    private static final String PASSWORD = "cookhub@123";
    private static final String SERVER = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE = "cookhub";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {
        try {
            connectToDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    private void connectToDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}
