package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_des_employees";
    private static final String USERNAME = "root"; // Remplacez par votre nom d'utilisateur
    private static final String PASSWORD = ""; // Remplacez par votre mot de passe

    public static Connection getConnexion() {
        try {
            System.out.println("Connexion � la base de donn�es...");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erreur de connexion � la base de donn�es :");
            e.printStackTrace(); // Affiche les d�tails de l'erreur
            return null;
        }
    }
}
