package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Model.Employe;
import Model.Poste;
import Model.Role;

public class EmployeDAOImpl implements EmployeDAOI {
    private static Connexion c;

    public EmployeDAOImpl() {
        c = new Connexion();
    }

    @Override
    public void addEmploye(Employe emp) {
        String sql = "INSERT INTO Employe (id,nom, prenom, email, salaire, role, poste, telephone) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = c.getConnexion().prepareStatement(sql)) {
        	st.setInt(1, emp.getId());
            st.setString(2, emp.getNom());
            st.setString(3, emp.getPrenom());
            st.setString(4, emp.getEmail());
            st.setDouble(5, emp.getSalaire());
            st.setString(6, emp.getRole());
            st.setString(7, emp.getPoste());
            st.setString(8, emp.getTelephone());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Err: " + e.getMessage(), "Err", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void modifyEmploye(Employe emp) {
        String sql = "UPDATE Employe SET nom = ? , prenom = ? , email = ?, salaire = ?, role = ?, poste = ?, telephone = ? WHERE id = ?";
        try (PreparedStatement st = c.getConnexion().prepareStatement(sql)) {
            st.setString(1, emp.getNom());
            st.setString(2, emp.getPrenom());
            st.setString(3, emp.getEmail());
            st.setDouble(4, emp.getSalaire());
            st.setString(5, emp.getRole());
            st.setString(6, emp.getPoste());
            st.setString(7, emp.getTelephone());
            st.setInt(8, emp.getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, " successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "employe ne trouve pas.", "Alerte", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Err: " + e.getMessage(), "Err", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteEmploye(int id) {
        String sql = "DELETE FROM Employe WHERE id = ?";
        try (PreparedStatement st = c.getConnexion().prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, " successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "employe ne trouve pas .", "Information", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Err: " + e.getMessage(), "Err", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<Employe> getAllEmploye() {
        List<Employe> employes = new ArrayList<>();
        String sql = "SELECT * FROM Employe";
        try (PreparedStatement st = c.getConnexion().prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
            	employes.add(new Employe(
                		rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getDouble("salaire"),
                        Role.valueOf(rs.getString("role")),
                        Poste.valueOf(rs.getString("poste"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Err: " + e.getMessage(), "Err", JOptionPane.ERROR_MESSAGE);
        }
        return employes;
    }
}
