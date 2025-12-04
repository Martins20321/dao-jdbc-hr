package model.dao;

import model.entities.Employee;
import model.entities.OrganizationUnit;

import java.util.List;

public interface OrganizationUnitDao {

    public void insert(OrganizationUnit obj);
    public void Update(OrganizationUnit obj);
    public void DeleteByID(Integer id);
    public OrganizationUnit findById(Integer id);
    List<OrganizationUnit> findAll();

}
