package Controller;

import view.EmployeView;
import Model.Employe;
import Model.EmployeModel;
import Enum.Role;
import Enum.Poste;
import javax.swing.*;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeController {
    private final EmployeView view;
    private final EmployeModel model;

    public EmployeController(EmployeView view, EmployeModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> performAddEmployee());
        view.getUpdateButton().addActionListener(e -> performUpdateEmployee());
        view.getDeleteButton().addActionListener(e -> performDeleteEmployee());
        view.getSearchButton().addActionListener(e -> performSearchEmployees());
       
        loadEmployeeList();
    }

    private boolean validateInput(String nom, String prenom, String email, String telephone, String salaire) {
        // Validation des champs
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || salaire.isEmpty()) {
            showErrorMessage("Tous les champs sont obligatoires");
            return false;
        }

        // Validation de l'email
        if (!isValidEmail(email)) {
            showErrorMessage("Format d'email invalide");
            return false;
        }

        // Validation du téléphone
        if (!isValidPhoneNumber(telephone)) {
            showErrorMessage("Numéro de téléphone invalide");
            return false;
        }

        // Validation du salaire
        try {
            double salaryValue = Double.parseDouble(salaire);
            if (salaryValue <= 0) {
                showErrorMessage("Le salaire doit être positif");
                return false;
            }
        } catch (NumberFormatException e) {
            showErrorMessage("Salaire invalide");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidPhoneNumber(String telephone) {
        // Validation pour un numéro de téléphone français
        return telephone.matches("^(\\+33|0)[1-9](\\d{2}){4}$");
    }

    private void performAddEmployee() {
        // Récupérer les valeurs des champs
        String nom = view.getNomField().getText().trim();
        String prenom = view.getPrenomField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String telephone = view.getTelephoneField().getText().trim();
        String salaire = view.getSalaireField().getText().trim();
        Role role = (Role) view.getRoleComboBox().getSelectedItem();
        Poste poste = (Poste) view.getPosteComboBox().getSelectedItem();

        // Validation des données
        if (!validateInput(nom, prenom, email, telephone, salaire)) {
            return;
        }

        try {
            // Créer un nouvel employé
            Employe nouvelEmploye = new Employe(0, nom, prenom, email, telephone, 
                                                Double.parseDouble(salaire), role, poste);
            
            // Ajouter l'employé via le modèle
            model.ajouterEmploye(nouvelEmploye);
            
            // Actualiser la liste
            loadEmployeeList();
            
            // Réinitialiser les champs
            clearInputFields();
            
            // Message de confirmation
            showSuccessMessage("Employé ajouté avec succès");
        } catch (Exception e) {
            showErrorMessage("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    private void performUpdateEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            showErrorMessage("Sélectionnez un employé à modifier");
            return;
        }

        // Récupérer les valeurs des champs
        String nom = view.getNomField().getText().trim();
        String prenom = view.getPrenomField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String telephone = view.getTelephoneField().getText().trim();
        String salaire = view.getSalaireField().getText().trim();
        Role role = (Role) view.getRoleComboBox().getSelectedItem();
        Poste poste = (Poste) view.getPosteComboBox().getSelectedItem();

        // Validation des données
        if (!validateInput(nom, prenom, email, telephone, salaire)) {
            return;
        }

        try {
            // Récupérer l'ID de l'employé sélectionné
            int employeId = (int) view.getTableModel().getValueAt(selectedRow, 0);
            
            // Créer l'objet employé à mettre à jour
            Employe employeModifie = new Employe(employeId, nom, prenom, email, telephone, 
                                                 Double.parseDouble(salaire), role, poste);
            
            // Mettre à jour l'employé
            model.updateEmploye(employeModifie);
            
            // Actualiser la liste
            loadEmployeeList();
            
            // Réinitialiser les champs
            clearInputFields();
            
            // Message de confirmation
            showSuccessMessage("Employé modifié avec succès");
        } catch (Exception e) {
            showErrorMessage("Erreur lors de la modification : " + e.getMessage());
        }
    }

    private void performDeleteEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            showErrorMessage("Sélectionnez un employé à supprimer");
            return;
        }

        try {
            // Récupérer l'ID de l'employé sélectionné
            int employeId = (int) view.getTableModel().getValueAt(selectedRow, 0);
            
            // Confirmation de suppression
            int confirmation = JOptionPane.showConfirmDialog(
                view, 
                "Êtes-vous sûr de vouloir supprimer cet employé ?", 
                "Confirmation", 
                JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                // Supprimer l'employé
                model.deleteEmploye(employeId);
                
                // Actualiser la liste
                loadEmployeeList();
                
                // Réinitialiser les champs
                clearInputFields();
                
                // Message de confirmation
                showSuccessMessage("Employé supprimé avec succès");
            }
        } catch (Exception e) {
            showErrorMessage("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    private void performSearchEmployees() {
        String recherche = JOptionPane.showInputDialog(view, "Entrez un terme de recherche :");
        if (recherche != null && !recherche.trim().isEmpty()) {
            List<Employe> resultats = model.rechercherEmployes(recherche);
            view.updateEmployeList(resultats);
        } else {
            loadEmployeeList();
        }
    }

    private void loadEmployeeList() {
        List<Employe> employes = model.getAllEmployes();
        view.updateEmployeList(employes);
    }

    private void clearInputFields() {
        view.getNomField().setText("");
        view.getPrenomField().setText("");
        view.getEmailField().setText("");
        view.getTelephoneField().setText("");
        view.getSalaireField().setText("");
        view.getRoleComboBox().setSelectedIndex(0);
        view.getPosteComboBox().setSelectedIndex(0);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(view, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(view, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
}