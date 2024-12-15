package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.*;

public class EmployeView extends JFrame {
    public JPanel pane1, pane2, pane3;
    public JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    public JTextField idf, nomf, prenomf, emailf, telephonef, salairef;
    public JComboBox<Role> roles; // ComboBox pour les rôles
    public JComboBox<Poste> postes; // ComboBox pour les postes
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton ajouterButton, modifierButton,supprimerButton, afficherButton;

    public EmployeView() {
        setTitle("Gestion Des Employés");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        this.setLocationRelativeTo(null);

        pane1 = new JPanel(new BorderLayout());
        pane2 = new JPanel(new GridLayout(8, 2, 5, 5));
        pane3 = new JPanel(new FlowLayout());

        label1 = new JLabel("ID:");
        idf = new JTextField(10);

        label2 = new JLabel("Nom:");
        nomf = new JTextField(10);

        label3 = new JLabel("Prenom:");
        prenomf = new JTextField(10);

        label4 = new JLabel("Email:");
        emailf = new JTextField(10);

        label5 = new JLabel("Telephone:");
        telephonef = new JTextField(10);

        label6 = new JLabel("Salaire:");
        salairef = new JTextField(10);

        label7 = new JLabel("Role:");
        roles = new JComboBox<>(Role.values());

        label8 = new JLabel("Poste:");
        postes = new JComboBox<>(Poste.values());

        pane2.add(label1);
        pane2.add(idf);
        pane2.add(label2);
        pane2.add(nomf);
        pane2.add(label3);
        pane2.add(prenomf);
        pane2.add(label4);
        pane2.add(emailf);
        pane2.add(label5);
        pane2.add(telephonef);
        pane2.add(label6);
        pane2.add(salairef);
        pane2.add(label7);
        pane2.add(roles);
        pane2.add(label8);
        pane2.add(postes);

        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        afficherButton = new JButton("Afficher");

        pane3.add(ajouterButton);
        pane3.add(modifierButton);
        pane3.add(supprimerButton);
        pane3.add(afficherButton);

        String[] columnNames = {"ID", "Nom", "Prenom", "Email", "Téléphone", "Salaire", "Rôle", "Poste"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        pane1.add(pane2, BorderLayout.NORTH);
        pane1.add(scrollPane, BorderLayout.CENTER);
        pane1.add(pane3, BorderLayout.SOUTH);

        add(pane1);
        setVisible(true);
    }

    public void afficherMessageSucces(String message) {
        JOptionPane.showMessageDialog(null, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    public void afficherMessageErreur(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public int getId() {
        try {
            return Integer.parseInt(idf.getText());
        } catch (NumberFormatException e) {
            afficherMessageErreur("L'ID doit être un nombre valide.");
            return -1;
        }
    }

    public String getNom() {
        return nomf.getText();
    }

    public String getPrenom() {
        return prenomf.getText();
    }

    public String getEmail() {
        return emailf.getText();
    }

    public String getTelephone() {
        return telephonef.getText();
    }

    public double getSalaire() {
        try {
            return Double.parseDouble(salairef.getText());
        } catch (NumberFormatException e) {
            afficherMessageErreur("Le salaire doit être un nombre valide.");
            return -1;
        }
    }

    public Role getRole() {
        return (Role) roles.getSelectedItem();
    }
    public Poste getPoste() {
        return (Poste) postes.getSelectedItem();
    }
    public static void main(String[] args) {
        new EmployeView();
    }
    public JButton getAjouterButton() {
        return ajouterButton;
    }
    public DefaultTableModel getModel() {
        return tableModel;
    }
    public JButton getModifierButton() {
        return modifierButton;
    }
    public JButton getSupprimerButton() {
        return supprimerButton;
    }
    public JButton getAfficherButton() {
        return afficherButton;
    }
    public JTable getTable() {
        return table;
    }
}
