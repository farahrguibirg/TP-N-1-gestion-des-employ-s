package Main;

import Controller.EmployeController;
import view.EmployeView;
import Model.EmployeModel;

public class Main {
    public static void main(String[] args) {
        // Création des instances pour le modèle, la vue et le contrôleur
        EmployeView view = new EmployeView();
        EmployeModel service = new EmployeModel();
        EmployeController controller = new EmployeController(view, service);

        // Rendre la vue visible
        view.setVisible(true);
    }
}
