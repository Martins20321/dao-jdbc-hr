package application;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.dao.impl.EmployeeDaoJDBC;
import model.entities.Employee;
import model.entities.OrganizationUnit;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        EmployeeDao empDao = DaoFactory.createEmployeeDao();
        OrganizationUnit org = new OrganizationUnit(2, null, null, null);

        /*
        System.out.println("=== TEST 1: Employee Insert");
        OrganizationUnit org = new OrganizationUnit(2, null, null, null);
        Employee newEmp = new Employee(null, "Ronaldo Bastos", "ronaldo@email.com", "Desenvolvedor", new Date(), 5.300, org);
        empDao.insert(newEmp);
        System.out.println("Insert completed, new Id: " + newEmp.getId());
         */

        System.out.println();
        System.out.println("=== TEST 2: Employee FindByID");
        Employee emp = empDao.findById(2);
        System.out.println(emp);

        System.out.println();
        System.out.println("=== TEST 3: Employee FindByOrganization");
        List<Employee> list = empDao.findByOrganization(org);
        for(Employee e : list){
            System.out.println(e);
        }

        System.out.println();
        System.out.println("=== TEST 4: Employee Update");
        emp = empDao.findById(2);
        emp.setEmail("pedro@gmail.com");
        emp.setJob("Engenheiro");
        emp.setBaseSalary(7200.00);
        empDao.Update(emp);
        System.out.println("Update Completed");

        /*
        System.out.println();
        System.out.println("=== TEST 5: Employee Delete");
        empDao.DeleteByID(4);
        System.out.println("Delete Completed");
        */

    }
}
