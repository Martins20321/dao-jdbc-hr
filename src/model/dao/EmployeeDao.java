package model.dao;

import model.entities.Employee;
import model.entities.OrganizationUnit;

import java.util.List;

public interface EmployeeDao {

    public void insert(Employee obj);
    public void Update(Employee obj);
    public void DeleteByID(Integer id);
    public Employee findById(Integer id);
    List<Employee> findAll();
    List<Employee> findByOrganization(OrganizationUnit organization);
}
