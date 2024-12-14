package Login;

import java.awt.Color;
import java.awt.Container;
import javax.swing.*;
import View.EmployeView;

public class Login extends JFrame {
    private JTextField txtNom, txtPrenom;
    private JButton btnLogin, btnReset;
    private JLabel label1, label2;

    public Login() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Container c = getContentPane();
        c.setBackground(Color.WHITE);

        label1 = new JLabel("Nom :");
        label2 = new JLabel("Prénom :");
        txtNom = new JTextField();
        txtPrenom = new JTextField();
        btnLogin = new JButton("Login");
        btnReset = new JButton("Reset");
        btnLogin.setBackground(Color.BLUE);
        btnLogin.setForeground(Color.WHITE);

        btnReset.setBackground(Color.BLUE);
        btnReset.setForeground(Color.WHITE);

        add(label1);
        add(label2);
        add(txtNom);
        add(txtPrenom);
        add(btnLogin);
        add(btnReset);

        label1.setBounds(50, 50, 100, 20);
        txtNom.setBounds(150, 50, 150, 25);

        label2.setBounds(50, 100, 100, 20);
        txtPrenom.setBounds(150, 100, 150, 25);

        btnLogin.setBounds(50, 150, 100, 30);
        btnReset.setBounds(200, 150, 100, 30);

        btnLogin.addActionListener(e -> {
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();

            if (nom.equals("admin") && prenom.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Connexion réussie !");
                
                SwingUtilities.invokeLater(() -> {
                    EmployeView employeView = new EmployeView();
                    employeView.setVisible(true);
                });

                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Nom ou Prénom incorrect !");
            }
        });

        btnReset.addActionListener(e -> {
            txtNom.setText("");
            txtPrenom.setText("");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
