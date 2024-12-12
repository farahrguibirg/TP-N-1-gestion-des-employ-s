package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Employe;
import Enum.Poste;
import Enum.Role;

public class EmployeDAOimpl implements EmployeDAOI {
    private Connection connection;

    public EmployeDAOimpl() {
        this.connection = Connexion.getConnexion();
    }

    @Override
    public void ajouterEmploye(Employe employe) {
        String query = "INSERT INTO employe (nom, prenom, email, telephone, salaire, role, poste) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, employe.getNom());
            ps.setString(2, employe.getPrenom());
            ps.setString(3, employe.getEmail());
            ps.setString(4, employe.getTelephone());
            ps.setDouble(5, employe.getSalaire());
            ps.setString(6, employe.getRole().name());
            ps.setString(7, employe.getPoste().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'employé", e);
        }
    }

    @Override
    public List<Employe> getAllEmployes() {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employe";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employe employe = new Employe(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("telephone"),
                    rs.getDouble("salaire"),
                    Role.valueOf(rs.getString("role")),
                    Poste.valueOf(rs.getString("poste"))
                );
                employes.add(employe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des employés", e);
        }
        return employes;
    }

    @Override
    public void updateEmploye(Employe emp) {
        String query = "UPDATE employe SET nom = ?, prenom = ?, email = ?, telephone = ?, salaire = ?, role = ?, poste = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, emp.getNom());
            ps.setString(2, emp.getPrenom());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getTelephone());
            ps.setDouble(5, emp.getSalaire());
            ps.setString(6, emp.getRole().name());
            ps.setString(7, emp.getPoste().name());
            ps.setInt(8, emp.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'employé", e);
        }
    }

    @Override
    public void deleteEmploye(int id) {
        String query = "DELETE FROM employe WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'employé", e);
        }
    }

    @Override
    public Employe getEmployeById(int id) {
        String query = "SELECT * FROM employe WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getDouble("salaire"),
                        Role.valueOf(rs.getString("role")),
                        Poste.valueOf(rs.getString("poste"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'employé", e);
        }
        return null;
    }
}
