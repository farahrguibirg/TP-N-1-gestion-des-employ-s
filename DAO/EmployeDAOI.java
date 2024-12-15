package DAO;

import java.util.List;

import Model.Employe;

public interface EmployeDAOI {
public void insertEmp(Employe emp);
public boolean deleteEmp(int id);
public boolean updateEmp(Employe emp);
public List<Employe> getEmployes();
}
