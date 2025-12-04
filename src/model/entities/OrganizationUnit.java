package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class OrganizationUnit implements Serializable {
    private int Id;
    private String Name;
    private String acronym;
    private String address;

    public OrganizationUnit(int id, String name, String acronym, String address) {
        Id = id;
        Name = name;
        this.acronym = acronym;
        this.address = address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationUnit that = (OrganizationUnit) o;
        return Id == that.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }

    @Override
    public String toString() {
        return "OrganizationUnit{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", acronym='" + acronym + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
