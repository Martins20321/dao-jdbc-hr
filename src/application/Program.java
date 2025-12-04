package application;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.dao.impl.EmployeeDaoJDBC;
import model.entities.Employee;
import model.entities.OrganizationUnit;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        EmployeeDao empDao = DaoFactory.createEmployeeDao();

        /*
        System.out.println("=== TEST 1: Employee Insert");
        OrganizationUnit org = new OrganizationUnit(2, null, null, null);
        Employee newEmp = new Employee(null, "Ronaldo Bastos", "ronaldo@email.com", "Desenvolvedor", new Date(), 5.300, org);
        empDao.insert(newEmp);
        System.out.println("Insert completed, new Id: " + newEmp.getId());
         */

        System.out.println();
        System.out.println("=== TEST 2: Employee FindByID");
        Employee emp = empDao.findById(4);
        System.out.println(emp);
    }
}
