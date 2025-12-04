package application;

import model.entities.Employee;
import model.entities.OrganizationUnit;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        OrganizationUnit org = new OrganizationUnit(1001, "Tecnologia e Desenvolvimento", "TDEV", "Rua 5 - SP");
        System.out.println(org);

        Employee emp = new Employee(1, "João Carlos", "joao@gmail.com", "Desenvolvedor", new Date(), 3500.00, org);
        System.out.println(emp);

    }
}
