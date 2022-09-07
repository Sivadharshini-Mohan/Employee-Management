package com.Employee-Management.EmployeeManagement.project management.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

public class ProjectDao extends BaseDao {
    private Connection connection = mysqlConnection();
    private PreparedStatement preparedStatement;
    
    public void insertProject(Project project) throws CustomException {

        try {
            String query = " insert into  project (name, client_name, company_name, start_date, project_status, created_date) "
                + " values (?, ?, ?, ?, ?, current_timestamp)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement(project);
            preparedStatement.execute();            
        } catch(Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }

    public boolean updateProjectById(int id, Project project) throws CustomException {
        try{
            String query = "update project set name = ? , client_name = ?, company_name = ?, start_dtae = ?, "
                           + "updated_date = current_timestamp where id = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement(project);
            preparedStatement.setInt(6, id);
            return preparedStatement.execute();
            
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
    
    public List<Project> retriveProject() throws CustomException {
            List<Project> projects = new ArrayList<Project>(); 
        try {
            String query = " select Project.*,employee_Project.project_id from employee_project inner join project on "
                            + "project.id = employee_project.project_id ";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String clientName = resultSet.getString("cilent_name");
                String companyName = resultSet.getString("company_name");
                String startDate = resultSet.getString("start_date");
                String projectStatus = resultSet.getString("project_status");
                Project project = new Project(name, clientName, companyName, startDate, projectStatus);
                projects.add(project);
            }
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
        return projects;
    } 

    public void deleteProjectById(int id) throws CustomException {
       try{
            String query = "update employee set active_status = 0 where id = " + id; 
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
       } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }   

     public void preparedStatement(Project project) throws CustomException  {
        try {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getClientName());
            preparedStatement.setString(3, project.getCompanyName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(project.getStartDate()));
            preparedStatement.setString(5, project.getProjectStatus());
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }

    public boolean assingemployees(int projectId, int employeeId, LocalDate startDate, LocalDate relieveDate) throws CustomException {    
        
        try {
            String query = "INSERT INTO employee_project(employee_id, project_id, started_date, relieved_date) values(?, ?, ? ,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);                        
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, projectId);
            preparedStatement.setDate(3, java.sql.Date.valueOf(startDate)); 
            preparedStatement.setDate(4, java.sql.Date.valueOf(relieveDate));
            return preparedStatement.execute();
        } catch (Exception error) {
            throw new CustomException(error.getMessage());
        }
    }

    public List<EmployeeProject> retriveEmployeeProject() throws CustomException {
            List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>(); 
        try {
            String query = " select employee_project.*,project.name from employee_project inner join project on "
                            + "project.id = employee_project.project_id ";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                int employeeId = resultSet.getInt("employee_id");
                int projectId = resultSet.getInt("project_id");
                String projectName = resultSet.getString("name");
                String startDate = resultSet.getString("started_date");
                String relievedDate = resultSet.getString("relieved_date");
                String status = resultSet.getString("active_status");
                EmployeeProject employeeProject = new EmployeeProject(employeeId, projectId, projectName, startDate, relievedDate, status);
                employeeProjects.add(employeeProject);
            }
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
        return employeeProjects;
    } 

    public void deleteEmployeeProjectByIds(int projectId, int employeeId) throws CustomException {
        try{
            String query = "update employee_project set active_status = 'inactive' where project_id = " + projectId + " and employee_id = " + employeeId ; 
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
       } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }

}