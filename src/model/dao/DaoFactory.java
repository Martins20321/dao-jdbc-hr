package model.dao;

import db.DB;
import model.dao.impl.EmployeeDaoJDBC;

public class DaoFactory {

    public static EmployeeDao createEmployeeDao(){
        return new EmployeeDaoJDBC(DB.getConnection()); //Pegando a conexão com o banco
    }
}
