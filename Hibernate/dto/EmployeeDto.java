package com.i2i.annotation.dto;

import com.i2i.annotation.model.Employee;
import com.i2i.annotation.model.Project;
import com.i2i.annotation.model.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDto { 
    private int id; 
    private String name;  
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private long mobileNumber;
    private LocalDate dateOfJoin;
    private int batch;
    private String status;
    private List<Role> roles = new ArrayList<Role>();
    private List<Project> projects = new ArrayList<Project>();

    public EmployeeDto(String name, String email, String dateOfBirth, String gender,long mobileNumber, String dateOfJoin, int batch, String status) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfJoin = LocalDate.parse(dateOfJoin);
        this.batch = batch;
        this.status = status;
    }

    public EmployeeDto() {

    }

    public EmployeeDto(int id, String name, String email, String dateOfBirth, String gender, long mobileNumber, String dateOfJoin, int batch, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfJoin = LocalDate.parse(dateOfJoin);
        this.batch = batch;
        this.status = status;
    }
        
    public int getId() {
        return id;
    } 
    
    public void setId() {
        this.id = id;
    }
        
    public String getName() {  
        return name;  
    }

    public void setName(String name) {  
        this.name = name;  
    }

    public String getEmailId() {  
        return email;  
    }

    public void setEmailId(String email) {  
        this.email = email;  
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getGender() {
        return gender;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setDateOfJoining(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
    
    public LocalDate getDateOfJoining() {
        return dateOfJoin;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }

    public List<Role> getRoles() {
        return roles;
    }
     
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
   
   public List<Project> getProjects() {
        return projects;
    }
     
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

   public String toString() {
        return  "Employee Id : " + id + "\n Name: " + name +  "\n Email Id: " + email + "\n Date of birth:" + dateOfBirth +
             "\n Gender:" + gender + "\n Mobile Number:" + mobileNumber + "\n Date of joining:" + dateOfJoin + "\n Batch:" + batch + "\n Roles:" +roles;
    }
}


    
     
  