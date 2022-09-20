package com.i2i.annotation.model;

import java.sql.Timestamp; 
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

@Entity
@Table(name = "employee")
public class Employee {
    @Id 
    @GeneratedValue
    @Column(name="id")
    private int id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "email_id")
    private String email;


    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "date_of_joning")
    private LocalDate dateOfJoining;

    @Column(name = "batch")
    private int batch;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp updatedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employee_role", 
        joinColumns = { @JoinColumn(name = "employee_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id")}
    )
    private List<Role> roles = new ArrayList<Role>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();
     
    public Employee() {

    }
    
    public Employee(String name, String email, String dateOfBirth, String gender, long mobileNumber, String dateOfJoining, 
                    int batch, String status) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfJoining = LocalDate.parse(dateOfJoining);
        this.batch = batch;
        this.status = status;
    }
    
    public Employee(int id, String name, String email, String dateOfBirth, String gender, long mobileNumber, String dateOfJoining, 
                    int batch , String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfJoining = LocalDate.parse(dateOfJoining);
        this.batch = batch;
        this.status = status;
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

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }
    public void setStatus(String status) {
        this.batch = batch;
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

    public List<EmployeeProject> getEmployeeProjects() {
        return employeeProjects;
    }
    
    public void setEmployeeProjects(List<EmployeeProject> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    public String toString() {
        return  "Employee Id : " + id + "\n Name: " + name +  "\n Email Id: " + email + "\n Date of birth:" + dateOfBirth +
                "\n Gender:" + gender + "\n Mobile Number:" + mobileNumber + "\n Date of joining:" + dateOfJoining + "\n Batch:" + batch + "\n Roles : " + roles ;
    } 
}