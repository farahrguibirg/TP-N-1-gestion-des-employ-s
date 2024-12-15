package Model;

import java.util.List;

import DAO.*;

public class EmployeModel {
private EmployeDAOImpl dao;
public EmployeModel(EmployeDAOImpl dao) {
	this.dao=dao;}
public boolean insertEmp(int id,String nom,String prenom,String email,String telephone ,Double salaire,Role role,Poste poste) {
	 if (!email.contains("@") || !email.contains(".")) {
         System.err.println("Email must be valid and contain '@' and '.' characters.");
         return false;
     }
     if (telephone.length() != 10) {
         System.err.println("The phone number must contain exactly 10 digits.");
         return false;
     }
     Employe emp = new Employe(id,nom, prenom, email, telephone, salaire, role, poste);
     dao.insertEmp(emp);
     return true;
 }
public boolean updateEmp(int id,String nom,String prenom,String email,String telephone ,Double salaire,Role role,Poste poste) {
	if (!email.contains("@") || !email.contains(".")) {
        System.err.println("Email must be valid and contain '@' and '.' characters.");
        return false;
    }
    if (telephone.length() != 10) {
        System.err.println("The phone number must contain exactly 10 digits.");
        return false;
    }
    Employe emp = new Employe(id,nom, prenom, email, telephone, salaire, role, poste);
    dao.updateEmp(emp);
    return true;
}
public boolean deleteEmp(int id){
	dao.deleteEmp(id);
	return true;
    
}
public Object[][] getAll() {
    List<Employe> list = dao.getEmployes();
    Object[][] employegs = new Object[list.size()][8]; 

    for (int i = 0; i < list.size(); i++) {
    	Employe emp = list.get(i);
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
