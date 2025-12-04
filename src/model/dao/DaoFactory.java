package model.dao;

import model.dao.impl.EmployeeDaoJDBC;

public class DaoFactory {

    public static EmployeeDao createEmployeeDao(){
        return new EmployeeDaoJDBC();
    }
}
