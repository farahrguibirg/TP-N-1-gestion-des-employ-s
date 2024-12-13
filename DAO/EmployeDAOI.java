package DAO;
import java.util.List;

import Model.Employe;
public interface EmployeDAOI {
	public void addEmploye(Employe emp);
	public void modifyEmploye(Employe emp);
	public void deleteEmploye(int id);
	public List<Employe> getAllEmploye();
	
}
