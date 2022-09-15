package com.i2i.annotation.dto;

import com.i2i.annotation.model.Employee;
import com.i2i.annotation.model.Project;

import java.time.LocalDate;

public class EmployeeProjectDto {
    private String projectName;
    private LocalDate startDate;
    private LocalDate relievedDate;
    private String activeStatus;
    private Employee employee;
    private Project project;
    
    public EmployeeProjectDto(String projectName, String startDate, String relievedDate, String activeStatus, 
                               Employee employee, Project project) {
        this.projectName = projectName;
        this.startDate = LocalDate.parse(startDate); 
        this.relievedDate = LocalDate.parse(relievedDate);
        this.activeStatus = activeStatus;
        this.employee = employee;
        this.project = project;
    }
    
    public EmployeeProjectDto() {

    }

    public String getProjectName() {
        return projectName;
    } 
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    } 
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getRelievedDate() {
        return relievedDate;
    } 
    
    public void setRelievedDate(LocalDate relievedDate) {
        this.relievedDate = relievedDate;
    }

    public String getActiveStatus() {
        return activeStatus;
    } 
    
    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Employee getEmployee() {
        return employee;
    } 
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    } 
    
    public void setProject(Project project) {
        this.project = project;
    }

    public String toString() {
        return  " \n Project Name : " + projectName + "\n Start Date : " + startDate + "\n Relieved Date:" + relievedDate
                + "\n Active Status:" + activeStatus ;
    }
}