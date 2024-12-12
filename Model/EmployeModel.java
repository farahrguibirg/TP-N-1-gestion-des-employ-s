package Model;

import DAO.EmployeDAOimpl;
import DAO.EmployeDAOI;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeModel {
    private final EmployeDAOI employeDAO;

    public EmployeModel() {
        this.employeDAO = new EmployeDAOimpl();
    }

	public void ajouterEmploye(Employe employe) {
        employeDAO.ajouterEmploye(employe);
    }

    public List<Employe> getAllEmployes() {
        return employeDAO.getAllEmployes();
    }

    public void updateEmploye(Employe employe) {
        employeDAO.updateEmploye(employe);
    }

    public void deleteEmploye(int id) {
        employeDAO.deleteEmploye(id);
    }

    public List<Employe> rechercherEmployes(String searchTerm) {
        List<Employe> employes = employeDAO.getAllEmployes(); 
        return employes.stream()
            .filter(emp -> emp.getNom().contains(searchTerm) || emp.getPrenom().contains(searchTerm))
            .collect(Collectors.toList());
    }
}
