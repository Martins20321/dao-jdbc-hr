package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.OrganizationUnitDao;
import model.entities.OrganizationUnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationUnitJDBC implements OrganizationUnitDao {

    private Connection conn;

    public OrganizationUnitJDBC (Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(OrganizationUnit obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO ORGANIZATION_UNIT (Name, acronym, address) VALUES "
                    + "(?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getAcronym());
            st.setString(3, obj.getAddress());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){ //Significa que inseriu uma linha ao banco
                ResultSet rs = st.getGeneratedKeys();//Mostrar a chave gerada
                if(rs.next()){//Após inserir, o ResultSet ler a linha
                    int id = rs.getInt(1);//Primeira coluna corresponde ao Id
                    obj.setId(id);//Adiciona o id ao objeto
                }
                DB.closeResultSet(rs);
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void Update(OrganizationUnit obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE ORGANIZATION_UNIT "
                    + "SET Name = ?, acronym = ?, address = ? "
                    + "WHERE Id = ?;");

            st.setString(1, obj.getName());
            st.setString(2, obj.getAcronym());
            st.setString(3, obj.getAddress());
            st.setInt(4, obj.getId());

            st.executeUpdate();
        }
        catch (SQLException e){
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
                    "DELETE FROM ORGANIZATION_UNIT "
                        + "WHERE id = ?");

            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            //Teste de verificação
            if(rowsAffected == 0){ //Significa que nenhuma linha foi deletada
                throw new DbIntegrityException("Unexpected error, this ID does not exist.");
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
    public OrganizationUnit findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * "
                            + "FROM ORGANIZATION_UNIT "
                            + "WHERE id = ?;");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                OrganizationUnit org = instanciationOrganizationUnit(rs);
                return org;
            }
            else{ //Se não tiver, retorna nulo
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private OrganizationUnit instanciationOrganizationUnit(ResultSet rs) throws SQLException {
        OrganizationUnit org = new OrganizationUnit();
        org.setId(rs.getInt("Id"));
        org.setName(rs.getString("Name"));
        org.setAcronym(rs.getString("acronym"));
        org.setAddress(rs.getString("address"));
        return org;
    }

    @Override
    public List<OrganizationUnit> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM ORGANIZATION_UNIT");

            rs = st.executeQuery();

            List<OrganizationUnit> list = new ArrayList<>();

            while(rs.next()){
                OrganizationUnit org = instanciationOrganizationUnit(rs);
                list.add(org);
            }
            return list;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
