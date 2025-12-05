package application;

import model.dao.DaoFactory;
import model.dao.OrganizationUnitDao;
import model.entities.OrganizationUnit;

public class Program2 {
    public static void main(String[] args) {

        OrganizationUnitDao orgDao = DaoFactory.createOrganizationUnitDao();

        System.out.println("===  TEST 1: OrganizationUnit FindById");
        OrganizationUnit org = orgDao.findById(2);
        System.out.println(org);
    }
}
