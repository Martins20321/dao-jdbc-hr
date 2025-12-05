package application;

import model.dao.DaoFactory;
import model.dao.OrganizationUnitDao;
import model.entities.OrganizationUnit;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {

        OrganizationUnitDao orgDao = DaoFactory.createOrganizationUnitDao();

        System.out.println("===  TEST 1: OrganizationUnit FindById");
        OrganizationUnit org = orgDao.findById(2);
        System.out.println(org);

        System.out.println();
        System.out.println("===  TEST 2: OrganizationUnit FindAll");
        List<OrganizationUnit> list = orgDao.findAll();
        for(OrganizationUnit o : list){
            System.out.println(o);
        }

        /*
        System.out.println();
        System.out.println("===  TEST 3: OrganizationUnit Insert");
        OrganizationUnit newOrg = new OrganizationUnit(null,"Usinas e Energias", "USE", "Rua 9 sul - Brasília");
        orgDao.insert(newOrg);
        System.out.println("Insert Completed, Your new Id: " + newOrg);
        */


        /*
        System.out.println();
        System.out.println("===  TEST 3.1: OrganizationUnit Insert");
        OrganizationUnit newOrg = new OrganizationUnit(null,"Versão Teste de Update", "VTU", "Rua 89 sul - Rio Grande do Norte");
        orgDao.insert(newOrg);
        System.out.println("Insert Completed, Your new Id: " + newOrg);
        */

        /*
        System.out.println();
        System.out.println("===  TEST 3.2: OrganizationUnit Insert");
        OrganizationUnit newOrg = new OrganizationUnit(null,"Versão Teste de Delete", "VTD", "Rua 89 sul - Rio Grande do Norte");
        orgDao.insert(newOrg);
        System.out.println("Insert Completed, Your new Id: " + newOrg);
        */

        System.out.println();
        System.out.println("===  TEST 4: OrganizationUnit Update");
        org = orgDao.findById(4);
        org.setName("Ministério Publico");
        org.setAcronym("MP");
        org.setAddress("Rua 22 Norte - Brasília,DF");
        orgDao.Update(org);
        System.out.println("Update Completed");

        System.out.println();
        System.out.println("===  TEST 5: OrganizationUnit Delete");
        orgDao.DeleteByID(6);
        System.out.println("Delete Completed");
    }
}
