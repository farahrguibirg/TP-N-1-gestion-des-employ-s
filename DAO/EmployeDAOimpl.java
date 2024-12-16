package DAO;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EmployeDAOImpl implements EmployeDAOI {

    public void insertEmp(Employe emp) {
        String sql = "INSERT INTO Employe (id, nom, prenom, email, salaire, role, poste, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getNom());
            ps.setString(3, emp.getPrenom());
            ps.setString(4, emp.getEmail());
            ps.setDouble(5, emp.getSalaire());
            ps.setString(6, emp.getRole().toString());
            ps.setString(7, emp.getPoste().toString());
            ps.setString(8, emp.getTelephone());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "L'employé a été inséré avec succès !", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "L'insertion a échoué.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion de l'employé : " + e.getMessage(),
                    "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void deleteEmp(int id) {
        String sql = "DELETE FROM Employe WHERE id = ?";
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "L'employé a été supprimé avec succès !", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La suppression a échoué.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'employé : " + e.getMessage(),
                    "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void updateEmp(Employe emp) {
        String sql = "UPDATE Employe SET nom = ?, prenom = ?, email = ?, salaire = ?, role = ?, poste = ?, telephone = ? WHERE id = ?";
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, emp.getNom());
            ps.setString(2, emp.getPrenom());
            ps.setString(3, emp.getEmail());
            ps.setDouble(4, emp.getSalaire());
            ps.setString(5, emp.getRole().toString());
            ps.setString(6, emp.getPoste().toString());
            ps.setString(7, emp.getTelephone());
            ps.setInt(8, emp.getId());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "L'employé a été modifié avec succès !", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La modification a échoué.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'employé : " + e.getMessage(),
                    "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public List<Employe> getEmployes() {
        List<Employe> list = new ArrayList<>();
        String sql = "SELECT * FROM Employe";
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employe emp = new Employe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getDouble("salaire"),
                        Role.valueOf(rs.getString("role")),
                        Poste.valueOf(rs.getString("poste"))
                );
                list.add(emp);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des employés : " + e.getMessage(),
                    "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return list;
    }

}
