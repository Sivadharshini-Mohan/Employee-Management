package com.i2i.annotation.converter;

import com.i2i.annotation.dto.EmployeeProjectDto;
import com.i2i.annotation.dto.ProjectDto;
import com.i2i.annotation.model.EmployeeProject;
import com.i2i.annotation.model.Project;

/**
 * <p>
 * It helps to convert the model data to dto vice versa
 * </p>
 **/
public class ProjectMapper {   
    public Project fromProjectDto(ProjectDto projectDto) {
        Project project = new Project(projectDto.getName(), projectDto.getClientName(), 
                                      projectDto.getCompanyName(), projectDto.getStartDate().toString(), projectDto.getProjectStatus()); 

        return project;
    }

    public ProjectDto toProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto(project.getName(), project.getClientName(), 
                                               project.getCompanyName(), project.getStartDate().toString(), project.getProjectStatus()); 
        
        return projectDto;
    }

    public EmployeeProject fromEmployeeProjectDto(EmployeeProjectDto employeeProjectDto) {
         EmployeeProject employeeProject = new EmployeeProject(employeeProjectDto.getProjectName(), employeeProjectDto.getStartDate().toString(),
                                                               employeeProjectDto.getRelievedDate().toString(), 
                                                               employeeProjectDto.getActiveStatus(),
                                                               employeeProjectDto.getEmployee(), employeeProjectDto.getProject()); 

        return employeeProject;
    }
    
    public EmployeeProjectDto toEmployeeProjectDto(EmployeeProject employeeProject) {
        EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto(employeeProject.getProjectName(), employeeProject.getStartDate().toString(),
                                                                        employeeProject.getRelievedDate().toString(), 
                                                                        employeeProject.getActiveStatus(),
                                                                        employeeProject.getEmployee(), employeeProject.getProject()); 

        return employeeProjectDto;
    }
}