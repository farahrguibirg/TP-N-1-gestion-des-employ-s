package Model;

import java.util.List;

import DAO.EmployeDAOImpl;

public class EmployeModel {
    private EmployeDAOImpl dao;

    public EmployeModel(EmployeDAOImpl dao) {
        this.dao = dao;
    }

    public boolean addEmploye(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (!email.contains("@") || !email.contains(".")) {
            System.err.println("Email must be valid and contain '@' and '.' characters.");
            return false;
        }
        if (telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }
        Employe emp = new Employe(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.addEmploye(emp);
        return true;
    }

    public boolean modifyEmploye(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (!email.contains("@") || !email.contains(".")) {
            System.err.println("Email must be valid and contain '@' and '.' characters.");
            return false;
        }
        if (telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }
        Employe emp = new Employe(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.modifyEmploye(emp);
        return true;
    }

    public boolean deleteEmployee(int id){
    	dao.deleteEmploye(id);
    	return true;
    }
    public Object[][] getAllEmployees() {
        List<Employe> employes = dao.getAllEmploye();
        Object[][] employegs = new Object[employes.size()][8]; 

        for (int i = 0; i < employes.size(); i++) {
        	Employe emp = employes.get(i);
        	employegs[i][0] = emp.getId();
        	employegs[i][1] = emp.getNom();
        	employegs[i][2] = emp.getPrenom();
        	employegs[i][3] = emp.getEmail();
        	employegs[i][4] = emp.getTelephone();
        	employegs[i][5] = emp.getSalaire();
        	employegs[i][6] = emp.getRole();
        	employegs[i][7] = emp.getPoste();
        }
        return employegs;
    }
    
}
