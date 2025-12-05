package model.dao;

import db.DB;
import model.dao.impl.EmployeeDaoJDBC;
import model.dao.impl.OrganizationUnitJDBC;

public class DaoFactory {

    public static EmployeeDao createEmployeeDao(){
        return new EmployeeDaoJDBC(DB.getConnection()); //Pegando a conexão com o banco
    }

    public static OrganizationUnitDao createOrganizationUnitDao(){
        return new OrganizationUnitJDBC(DB.getConnection());
    }
}
