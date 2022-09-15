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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table; 
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "employee_project")
public class EmployeeProject {

    @Id 
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name = "project_name")
    String projectName;
 
    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "relieved_date")
    LocalDate relievedDate;

    @Column(name = "status")
    String activeStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;
    
    public EmployeeProject(String projectName, String startDate, String relievedDate, String activeStatus, Employee employee, Project project) {        
        this.projectName = projectName;
        this.startDate = LocalDate.parse(startDate); 
        this.relievedDate = LocalDate.parse(relievedDate);
        this.activeStatus = activeStatus;
        this.employee = employee;
        this.project = project;
    }

    public EmployeeProject() {
    }

    public String getProjectName() {
        return projectName;
    } 
    
    public void setProjectName() {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    } 
    
    public void setStartDate() {
        this.startDate = startDate;
    }

    public LocalDate getRelievedDate() {
        return relievedDate;
    } 
    
    public void setRelievedDate() {
        this.relievedDate = relievedDate;
    }

    public String getActiveStatus() {
        return activeStatus;
    } 
    
    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    
    public Project getProject() {
        return project;
    } 
    
    public Employee getEmployee() {
        return employee;
    } 

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String toString() {
        return  "\n Project Name : " + projectName 
                + "\n Start Date : " + startDate + "\n Relieved Date:" + relievedDate 
                + "\n Active Status:" + activeStatus ;
    }
}