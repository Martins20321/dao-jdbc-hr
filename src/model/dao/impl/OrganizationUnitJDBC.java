package model.dao.impl;

import model.dao.OrganizationUnitDao;
import model.entities.OrganizationUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try{
            st = conn.prepareStatement(
                    "SELECT ORGANIZATION_UNIT.Name as orgName "
                            + "FROM ORGANIZATION_UNIT "
                            + "WHERE id = 3;"
            )
        }
    }

    @Override
    public List<OrganizationUnit> findAll() {
        return List.of();
    }
}
