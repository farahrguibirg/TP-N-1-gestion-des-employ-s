package DAO;

import java.util.List;

import Model.Employe;
public interface EmployeDAOI {
public void insertEmp(Employe emp);
public void deleteEmp(int id);
public void updateEmp(Employe emp);
public List<Employe> getEmployes();
}
