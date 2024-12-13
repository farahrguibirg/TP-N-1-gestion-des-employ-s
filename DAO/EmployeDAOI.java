package DAO;
import java.util.List;

import Model.Employe;
public interface EmployeDAOI {
	public void addEmployee(Employe emp);
	public void modifyEmployee(Employe emp);
	public void deleteEmployee(int id);
	public List<Employe> getAllEmployees();
	
}
