package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.EmployeeDao;
import model.entities.Employee;
import model.entities.OrganizationUnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE EMPLOYEE "
                            + "SET email = ?, job = ?, baseSalary = ? "
                            + "WHERE id = ?;");

            st.setString(1, obj.getEmail());
            st.setString(2, obj.getJob());
            st.setDouble(3, obj.getBaseSalary());
            st.setInt(4, obj.getId());

            st.executeUpdate();
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void DeleteByID(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "DELETE FROM EMPLOYEE "
                        + "WHERE id = ? ");

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0){
                throw new DbIntegrityException("No rows Affected, This ID does not exist");
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
    public Employee findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT EMPLOYEE.*, ORGANIZATION_UNIT.Name as orgName "
                            + "from EMPLOYEE INNER JOIN ORGANIZATION_UNIT "
                            + "ON EMPLOYEE.OrganizationUnitId = ORGANIZATION_UNIT.Id "
                            + "WHERE EMPLOYEE.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){ //Significa que encontrou a tupla
                OrganizationUnit org = intanciationOrganizationUnit(rs);
                Employee emp = instanciationEmployee(rs,org);
                return emp;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Employee instanciationEmployee(ResultSet rs, OrganizationUnit org) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getInt("id"));
        emp.setName(rs.getString("name"));
        emp.setEmail(rs.getString("email"));
        emp.setJob(rs.getString("job"));
        emp.setBirthDate(rs.getDate("birthDate"));
        emp.setBaseSalary(rs.getDouble("baseSalary"));
        emp.setOrganizationUnit(org);

        return emp;
    }

    private OrganizationUnit intanciationOrganizationUnit(ResultSet rs) throws SQLException {
        OrganizationUnit org = new OrganizationUnit();
        org.setId(rs.getInt("Id"));
        org.setName(rs.getString("Name"));

        /*org.setAcronym(rs.getString("acronym"));    //Erro de found = Quando não está pedindo na consulta
        org.setAddress("address");
        */

        return org;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public List<Employee> findByOrganization(OrganizationUnit organization) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT EMPLOYEE.*, "
                            + "ORGANIZATION_UNIT.Name as OrgName "
                            + "FROM EMPLOYEE join ORGANIZATION_UNIT on EMPLOYEE.OrganizationUnitId = ORGANIZATION_UNIT.Id "
                            + "WHERE OrganizationUnitId = ? "
                            + "order by Name; ");

            st.setInt(1, organization.getId());
            rs = st.executeQuery();

            List<Employee> list = new ArrayList<>();
            //Evitando a repetição de Organizations
            Map<Integer, OrganizationUnit> map = new HashMap<>();

            while(rs.next()){

                //Verificação se já existe um Organization
                OrganizationUnit org = map.get(rs.getInt("OrganizationUnitId"));

                if(org == null) { //Se não existir, Instanciamos um Organization
                    org = intanciationOrganizationUnit(rs);
                    map.put(rs.getInt("OrganizationUnitId"), org); //Salvando no map
                }
                Employee emp = instanciationEmployee(rs, org);
                list.add(emp);
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
