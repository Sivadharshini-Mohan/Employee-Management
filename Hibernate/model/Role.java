package com.i2i.annotation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;  
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table; 
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
 
/**
 * <p>
 * Attributes of role class which related to database
 * </p>
 */
@Entity
@Table(name = "role")
public class Role {

    @Id 
    @GeneratedValue 
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = Employee.class, cascade = {CascadeType.ALL})
    private List<Employee> employees = new ArrayList<Employee>();
     
    public Role() {

    }
   
    public Role(String name) { 
        this.name = name;
    }
 
    public Role(int id, String name) { 
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployee() {
        return employees;
    }
     
    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Role> getDefaultRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("Trainer"));
        roles.add(new Role("Trainee"));
        roles.add(new Role("Manager"));
        return roles;

    }
}