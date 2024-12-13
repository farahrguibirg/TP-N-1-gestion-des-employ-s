package Main;
import Model.*;
import View.*;
import DAO.*;
import Controller.*;
public class Main {
	public static void main(String[] args) {
		EmployeDAOImpl dao = new EmployeDAOImpl();
		EmployeModel model = new EmployeModel(dao);
		EmployeView view = new EmployeView();
		EmployeController controller = new EmployeController(view,model);
	}
}
