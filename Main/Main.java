package Main;

import Controller.EmployeController;
import view.EmployeView;
import Model.EmployeModel;

public class Main {
    public static void main(String[] args) {
        // Cr�ation des instances pour le mod�le, la vue et le contr�leur
        EmployeView view = new EmployeView();
        EmployeModel service = new EmployeModel();
        EmployeController controller = new EmployeController(view, service);

        // Rendre la vue visible
        view.setVisible(true);
    }
}
