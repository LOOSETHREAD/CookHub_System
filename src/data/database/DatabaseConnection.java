/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance(){
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection(){
        try {
            ConnectToDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception as per your application's requirements
        }
    }

    private void ConnectToDatabase() throws SQLException, ClassNotFoundException {
        String server = "127.0.0.1";
        String port = "3306";
        String database ="cookhub";
        String user = "root";
        String password = "root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database,user,password);
    }

    public Connection getConnection() {
        return connection;
    }
}
