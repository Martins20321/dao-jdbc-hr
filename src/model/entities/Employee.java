package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Employee implements Serializable {

    private int id;
    private String name;
    private String email;
    private String job;
    private Date birthDate;
    private double baseSalary;

    private OrganizationUnit organizationUnit;

    public Employee(){

    }

    public Employee(int id, String name, String email, String job, Date birthDate, double baseSalary, OrganizationUnit organizationUnit) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.job = job;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.organizationUnit = organizationUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", job='" + job + '\'' +
                ", birthDate=" + birthDate +
                ", baseSalary=" + baseSalary +
                ", organizationUnit=" + organizationUnit +
                '}';
    }
}
