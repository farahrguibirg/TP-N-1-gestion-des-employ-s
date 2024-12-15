package Main;

import Model.*;
import View.*;
import DAO.*;
import Controller.EmployeController;

public class Main {
    public static void main(String[] args) {
        EmployeView view = new EmployeView();
        EmployeDAOImpl dao = new EmployeDAOImpl();
        EmployeModel model = new EmployeModel(dao);
        new EmployeController(model, view);
        view.setVisible(true);
    }
}
