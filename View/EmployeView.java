package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Enum.*;
import java.awt.*;

public class EmployeView extends JFrame {
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    
    public JLabel label1, label2, label3, label4, label5, label6, label7;
    public JTextField text1, text2, text3, text4, text5;
    public JComboBox<Role> roles;
    public JComboBox<Poste> postes;
    public JTable table;
    public DefaultTableModel model;
    public JScrollPane scrollPane;
    public JButton btn1, btn2, btn3, btn4;
    public JSpinner idSpinner;
    public EmployeView() {
        setTitle("Employees Management");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new GridLayout(8, 2));
        panel3 = new JPanel(new FlowLayout());

        JLabel labelId = new JLabel("ID:");
        idSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000000, 1)); 
        label1 = new JLabel("Nom:");
        text1 = new JTextField(10);
        label2 = new JLabel("Prenom:");
        text2 = new JTextField(10);
        label3 = new JLabel("Telephone:");
        text3 = new JTextField(10);
        label4 = new JLabel("Email:");
        text4 = new JTextField(10);
        label5 = new JLabel("Salaire:");
        text5 = new JTextField(10);
        label6 = new JLabel("Roles:");
        roles = new JComboBox<>(Role.values());
        label7 = new JLabel("Postes:");
        postes = new JComboBox<>(Poste.values());
        panel2.add(labelId);
        panel2.add(idSpinner);
        panel2.add(label1);
        panel2.add(text1);
        panel2.add(label2);
        panel2.add(text2);
        panel2.add(label3);
        panel2.add(text3);
        panel2.add(label4);
        panel2.add(text4);
        panel2.add(label5);
        panel2.add(text5);
        panel2.add(label6);
        panel2.add(roles);
        panel2.add(label7);
        panel2.add(postes);

        String[] columnNames = {"ID","Nom", "Prenom", "Telephone", "Email", "Salaire", "Role", "Poste"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        btn1 = new JButton("Ajouter");
        btn2 = new JButton("Modifier");
        btn3 = new JButton("Supprimer");
        btn4 = new JButton("Afficher");
        panel3.add(btn1);
        panel3.add(btn2);
        panel3.add(btn3);
        panel3.add(btn4);
        panel1.add(panel2, BorderLayout.NORTH);
        panel1.add(scrollPane, BorderLayout.CENTER);
        panel1.add(panel3, BorderLayout.SOUTH);

        add(panel1);
        setVisible(true);
    }
}
