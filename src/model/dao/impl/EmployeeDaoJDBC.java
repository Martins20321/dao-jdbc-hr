package model.dao.impl;

import model.dao.EmployeeDao;
import model.entities.Employee;

import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDao {
    @Override
    public void insert(Employee obj) {

    }

    @Override
    public void Update(Employee obj) {

    }

    @Override
    public void DeleteByID(Integer id) {

    }

    @Override
    public Employee findById(Integer id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }
}
