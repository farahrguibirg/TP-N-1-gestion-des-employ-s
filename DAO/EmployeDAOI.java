package DAO;

import Model.Employe;
import java.util.List;

public interface EmployeDAOI {
    void ajouterEmploye(Employe emp);
    List<Employe> getAllEmployes();
    void updateEmploye(Employe emp);
    void deleteEmploye(int id);
    Employe getEmployeById(int id);
}
