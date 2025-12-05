package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.OrganizationUnitDao;
import model.entities.OrganizationUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrganizationUnitJDBC implements OrganizationUnitDao {

    private Connection conn;

    public OrganizationUnitJDBC (Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(OrganizationUnit obj) {

    }

    @Override
    public void Update(OrganizationUnit obj) {

    }

    @Override
    public void DeleteByID(Integer id) {

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
        return List.of();
    }
}
