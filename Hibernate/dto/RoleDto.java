package com.i2i.annotation.dto;

import com.i2i.annotation.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class RoleDto {
    private int id;
    private String name;
    private List<Employee> employees = new ArrayList<Employee>();
    
    public RoleDto() {

    }
   
    public RoleDto(String name) { 
        this.name = name;
    }
 
    public RoleDto(int id, String name) { 
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

    public String toString() {
        return "\n Role :" + name;
    }  
}

