package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.*;

public class EmployeController {
    private EmployeView view;
    private EmployeModel model;

    public EmployeController(EmployeView view, EmployeModel model) {
        this.view = view;
        this.model = model;

        this.view.btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(view.textId.getText().trim());
                    String nom = view.text1.getText().trim();
                    String prenom = view.text2.getText().trim();
                    String telephone = view.text3.getText().trim();
                    String email = view.text4.getText().trim();
                    double salaire = Double.parseDouble(view.text5.getText().trim());
                    Role role = (Role) view.roles.getSelectedItem();
                    Poste poste = (Poste) view.postes.getSelectedItem();

                    model.addEmployee(id, nom, prenom, email, telephone, salaire, role, poste);
                    Object[] row = {id, nom, prenom, telephone, email, salaire, role, poste};
                    view.model.addRow(row);
                    System.out.println("Employé ajouté : " + id + nom + prenom + telephone + email + salaire + role + poste);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Veuillez entrer un ID et un salaire valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.view.btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        int id = Integer.parseInt(view.textId.getText().trim());
                        String nom = view.text1.getText().trim();
                        String prenom = view.text2.getText().trim();
                        String telephone = view.text3.getText().trim();
                        String email = view.text4.getText().trim();
                        double salaire = Double.parseDouble(view.text5.getText().trim());
                        Role role = (Role) view.roles.getSelectedItem();
                        Poste poste = (Poste) view.postes.getSelectedItem();

                        model.modifyEmployee(id, nom, prenom, email, telephone, salaire, role, poste);
                        view.model.setValueAt(id, selectedRow, 0);
                        view.model.setValueAt(nom, selectedRow, 1);
                        view.model.setValueAt(prenom, selectedRow, 2);
                        view.model.setValueAt(telephone, selectedRow, 3);
                        view.model.setValueAt(email, selectedRow, 4);
                        view.model.setValueAt(salaire, selectedRow, 5);
                        view.model.setValueAt(role, selectedRow, 6);
                        view.model.setValueAt(poste, selectedRow, 7);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(view, "Veuillez entrer un ID et un salaire valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Veuillez sélectionner une ligne à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.view.btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) view.model.getValueAt(selectedRow, 0);
                    model.deleteEmployee(id);
                    view.model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(view, "Veuillez sélectionner une ligne à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.view.btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] allEmployees = model.getAllEmployees();
                view.model.setRowCount(0);
                for (Object[] employee : allEmployees) {
                    view.model.addRow(employee);
                }
            }
        });
    }
}
