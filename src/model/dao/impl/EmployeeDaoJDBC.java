package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.EmployeeDao;
import model.entities.Employee;

import java.sql.*;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDao {

    //Estabelecendo uma conexão obrigatória com o banco.
    private Connection conn;

    //Injeção de dependência
    public EmployeeDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Employee obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO EMPLOYEE (name , email, job, birthDate, baseSalary, OrganizationUnitId) "
                        + "VALUES "
                        + " (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

                    st.setString(1, obj.getName());
                    st.setString(2, obj.getName());
                    st.setString(3, obj.getEmail());
                    st.setDate(4, new java.sql.Date(obj.getBirthDate().getTime()));
                    st.setDouble(5, obj.getBaseSalary());
                    st.setInt(6, obj.getOrganizationUnit().getId());

                    int rowsAffected = st.executeUpdate();

                    //A variável id recebe a primeira coluna, correspondente ao ID;
                    if(rowsAffected > 0){ //Ou seja, linhas foram inseridas
                        ResultSet rs = st.getGeneratedKeys(); //Para Mostrar a chave gerada
                            if(rs.next()){ //Se existir
                                int id = rs.getInt(1); //Posição 1 = Primeira Coluna
                                obj.setId(id); //Atribuindo o ID gerado
                            }
                        DB.closeResultSet(rs);
                    }
                    else{
                        throw new DbIntegrityException("Unexpected error, lines were not inserted.");
                    }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
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
