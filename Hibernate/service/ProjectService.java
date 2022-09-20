package com.i2i.annotation.service;

import com.i2i.annotation.common.CustomException;
import com.i2i.annotation.converter.ProjectMapper;
import com.i2i.annotation.dao.EmployeeDao;
import com.i2i.annotation.dao.ProjectDao;
import com.i2i.annotation.dto.EmployeeProjectDto;
import com.i2i.annotation.dto.ProjectDto;
import com.i2i.annotation.model.Employee;
import com.i2i.annotation.model.EmployeeProject;
import com.i2i.annotation.model.Project;
import com.i2i.annotation.model.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * It holds all bussiness logics and get values 
 * from DAO, convert the model as DTO and pass it to Controller class
 * </p>
 * @author Sivadharshini Mohan
 * @version 1.0
 **/
public class ProjectService {
    ProjectMapper projectMapper = new ProjectMapper();
    ProjectDao projectDao = new ProjectDao();

    /**  
     * <p>  
     * Get the user input from controller and transfer to project Database acess object  
     * </p>
     *
     * @param projectDto 
     * @return {@link String}
     */
    public String addProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromProjectDto(projectDto);
        projectDao.insertProject(project);

        return "Project detail create sucessfully";    
    }
    
    /**  
     * <p>  
     * Get the user input update it into database
     * </p>
     *
     * @param employeeDto 
     * @param employeeRole Trainer/Trainee
     * 
     * @return {@link String}
     */
    public String updateProject(int id, ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromProjectDto(projectDto);
        project.setId(id);
        projectDao.updateProjectById(project);

        return "Project detail updtaed sucessfully";    
    }

   /**
    * <p>
    * Get the all project detail
    * </p> 
    * @return {@link List} return Object
    */
    public List<ProjectDto> getprojects() throws CustomException {
        List<Project> projects =  projectDao.retriveProjects();
        List<ProjectDto> projectDtoList =  new ArrayList<ProjectDto>();
        for(Project project : projects) {
            ProjectDto projectDto = projectMapper.toProjectDto(project);
            projectDtoList.add(projectDto);
        }

        return projectDtoList; 
    }

   /**
    * <p>
    * Delete the project by using id
    * </p> 
    * @param project id 
    * @return {@link List} return Object
    */
     public String deleteProject(int id) throws CustomException {
        List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();
        Project project = projectDao.retrieveProjectById(id);
        project.setProjectStatus("active");
        project.setEmployeeProjects(employeeProjects);
        projectDao.deleteProject(project);

        return "Project deleted sucessfully";
    }
  
    /**  
     * <p>  
     * Get the  input from controller and transfer to employee project Database acess object  
     * </p>
     *
     * @param employeeDto 
     * @param employeeRole Trainer/Trainee
     * 
     * @return {@link String}
     */
    public String assingProjectToEmployees(EmployeeProjectDto employeeProjectDto, int id, int employeeId) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeProject employeeProject = projectMapper.fromEmployeeProjectDto(employeeProjectDto);
        Project project = projectDao.retrieveProjectById(id);
        Employee employee = employeeDao.retrieveEmployeeById(employeeId);
        employeeProject.setProject(project);
        employeeProject.setEmployee(employee);
        projectDao.assingEmployees(employeeProject);
        
        return "Assigned employees for project";
    }
    
    /**
    * <p>
    * Get the all employee project detail
    * </p> 
    * @return {@link List} return Object
    */
    public List<EmployeeProjectDto> getEmployeeProjects() throws CustomException {
        List<EmployeeProject> employeeProjects =  projectDao.retriveEmployeeProject();
        List<EmployeeProjectDto> employeeProjectDtoList =  new ArrayList<EmployeeProjectDto>();
        for(EmployeeProject employeeProject : employeeProjects) {
            EmployeeProjectDto employeeProjectDto = projectMapper.toEmployeeProjectDto(employeeProject);
            employeeProjectDtoList.add(employeeProjectDto);
        }

        return employeeProjectDtoList; 
    }

}