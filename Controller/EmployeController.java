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

        // Validation du t�l�phone
        if (!isValidPhoneNumber(telephone)) {
            showErrorMessage("Num�ro de t�l�phone invalide");
            return false;
        }

        // Validation du salaire
        try {
            double salaryValue = Double.parseDouble(salaire);
            if (salaryValue <= 0) {
                showErrorMessage("Le salaire doit �tre positif");
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
        // Validation pour un num�ro de t�l�phone fran�ais
        return telephone.matches("^(\\+33|0)[1-9](\\d{2}){4}$");
    }

    private void performAddEmployee() {
        // R�cup�rer les valeurs des champs
        String nom = view.getNomField().getText().trim();
        String prenom = view.getPrenomField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String telephone = view.getTelephoneField().getText().trim();
        String salaire = view.getSalaireField().getText().trim();
        Role role = (Role) view.getRoleComboBox().getSelectedItem();
        Poste poste = (Poste) view.getPosteComboBox().getSelectedItem();

        // Validation des donn�es
        if (!validateInput(nom, prenom, email, telephone, salaire)) {
            return;
        }

        try {
            // Cr�er un nouvel employ�
            Employe nouvelEmploye = new Employe(0, nom, prenom, email, telephone, 
                                                Double.parseDouble(salaire), role, poste);
            
            // Ajouter l'employ� via le mod�le
            model.ajouterEmploye(nouvelEmploye);
            
            // Actualiser la liste
            loadEmployeeList();
            
            // R�initialiser les champs
            clearInputFields();
            
            // Message de confirmation
            showSuccessMessage("Employ� ajout� avec succ�s");
        } catch (Exception e) {
            showErrorMessage("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    private void performUpdateEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            showErrorMessage("S�lectionnez un employ� � modifier");
            return;
        }

        // R�cup�rer les valeurs des champs
        String nom = view.getNomField().getText().trim();
        String prenom = view.getPrenomField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String telephone = view.getTelephoneField().getText().trim();
        String salaire = view.getSalaireField().getText().trim();
        Role role = (Role) view.getRoleComboBox().getSelectedItem();
        Poste poste = (Poste) view.getPosteComboBox().getSelectedItem();

        // Validation des donn�es
        if (!validateInput(nom, prenom, email, telephone, salaire)) {
            return;
        }

        try {
            // R�cup�rer l'ID de l'employ� s�lectionn�
            int employeId = (int) view.getTableModel().getValueAt(selectedRow, 0);
            
            // Cr�er l'objet employ� � mettre � jour
            Employe employeModifie = new Employe(employeId, nom, prenom, email, telephone, 
                                                 Double.parseDouble(salaire), role, poste);
            
            // Mettre � jour l'employ�
            model.updateEmploye(employeModifie);
            
            // Actualiser la liste
            loadEmployeeList();
            
            // R�initialiser les champs
            clearInputFields();
            
            // Message de confirmation
            showSuccessMessage("Employ� modifi� avec succ�s");
        } catch (Exception e) {
            showErrorMessage("Erreur lors de la modification : " + e.getMessage());
        }
    }

    private void performDeleteEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            showErrorMessage("S�lectionnez un employ� � supprimer");
            return;
        }

        try {
            // R�cup�rer l'ID de l'employ� s�lectionn�
            int employeId = (int) view.getTableModel().getValueAt(selectedRow, 0);
            
            // Confirmation de suppression
            int confirmation = JOptionPane.showConfirmDialog(
                view, 
                "�tes-vous s�r de vouloir supprimer cet employ� ?", 
                "Confirmation", 
                JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                // Supprimer l'employ�
                model.deleteEmploye(employeId);
                
                // Actualiser la liste
                loadEmployeeList();
                
                // R�initialiser les champs
                clearInputFields();
                
                // Message de confirmation
                showSuccessMessage("Employ� supprim� avec succ�s");
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
        JOptionPane.showMessageDialog(view, message, "Succ�s", JOptionPane.INFORMATION_MESSAGE);
    }
}