package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_des_employees";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection getConnexion() {
        try {
            System.out.println("Connexion à la base de données...");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données :");
            e.printStackTrace(); 
            return null;
        }
    }
}
