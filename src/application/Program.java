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

        //Test

        System.out.println("=== TEST 1: Employee Insert");
        Employee newEmp = empDao.insert(null, "Pedro Roberto", "pedro@email.com", "Engenheiro", new Date(), 11.200,OrganizationUnit);
    }
}
