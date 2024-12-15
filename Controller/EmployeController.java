package Controller;

import Model.EmployeModel;
import Model.Poste;
import Model.Role;
import View.EmployeView;

public class EmployeController {
    private EmployeModel model;
    private EmployeView view;

    public EmployeController(EmployeModel model, EmployeView view) {
        this.model = model;
        this.view = view;

        // Ajout des listeners pour les boutons
        this.view.getAjouterButton().addActionListener(e -> insertEmp());
        this.view.getModifierButton().addActionListener(e -> updateEmp());
        this.view.getSupprimerButton().addActionListener(e -> deleteEmp());
        this.view.getAfficherButton().addActionListener(e -> afficherEmployes());
    }

    private void insertEmp() {
        try {
            int id = view.getId();
            String nom = view.getNom();
            String prenom = view.getPrenom();
            String email = view.getEmail();
            String telephone = view.getTelephone();
            double salaire = view.getSalaire();
            Role role = view.getRole();
            Poste poste = view.getPoste();

            boolean ajoutReussi = model.insertEmp(id, nom, prenom, email, telephone, salaire, role, poste);

            if (ajoutReussi) {
                Object[] row = {id, nom, prenom, email, telephone, salaire, role, poste};
                view.getModel().addRow(row);  // Ajoutez l'employé dans le modèle du tableau
            } else {
                view.afficherMessageErreur("Échec de l'ajout de l'employé.");
            }
        } catch (NumberFormatException e) {
            view.afficherMessageErreur("Veuillez entrer un nombre valide !");
        } catch (Exception e) {
            view.afficherMessageErreur("Erreur inattendue : " + e.getMessage());
        }
    }

    private void updateEmp() {
        try {
            int id = view.getId();
            String nom = view.getNom();
            String prenom = view.getPrenom();
            String email = view.getEmail();
            String telephone = view.getTelephone();
            double salaire = view.getSalaire();
            Role role = view.getRole();
            Poste poste = view.getPoste();

            boolean modifReussie = model.updateEmp(id, nom, prenom, email, telephone, salaire, role, poste);

            if (modifReussie) {
                view.afficherMessageSucces("L'employé a été modifié avec succès !");
            } else {
                view.afficherMessageErreur("Échec de la modification de l'employé.");
            }
        } catch (NumberFormatException e) {
            view.afficherMessageErreur("Veuillez entrer un nombre valide !");
        } catch (Exception e) {
            view.afficherMessageErreur("Erreur inattendue : " + e.getMessage());
        }
    }

    private void deleteEmp() {
        try {
            int id = view.getId();
            boolean suppressionReussie = model.deleteEmp(id);

            if (suppressionReussie) {
                view.afficherMessageSucces("L'employé a été supprimé avec succès !");
            } else {
                view.afficherMessageErreur("Échec de la suppression de l'employé.");
            }
        } catch (NumberFormatException e) {
            view.afficherMessageErreur("Veuillez entrer un identifiant valide !");
        } catch (Exception e) {
            view.afficherMessageErreur("Erreur inattendue : " + e.getMessage());
        }
    }

    private void afficherEmployes() {
        Object[][] all = model.getAll();
        view.getModel().setRowCount(0);  // Réinitialise le modèle de tableau
        for (Object[] employee : all) {
            view.getModel().addRow(employee);  // Ajoute chaque employé dans le tableau
        }
    }
}
