/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CookHub.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author User
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public void connectToDatabase() throws SQLException, ClassNotFoundException {
        String server = "127.0.0.1";
        String port = "3306";
        String database = "user";
        String user = "CookHubSystem";
        String password = "Kirkely123";

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            connectToDatabase();
        }
        return connection;
    }
}
