package com.i2i.annotation.controller;

import com.i2i.annotation.common.CustomException;
import com.i2i.annotation.controller.EmployeeController;
import com.i2i.annotation.dto.EmployeeProjectDto;
import com.i2i.annotation.dto.ProjectDto;
import com.i2i.annotation.service.ProjectService;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

/**
 * <p>
 * Project controlls the project,
 * Get the input from user sent it into service class
 * Create, Read, Update and Delete (CRUD) 
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 **/
public class ProjectController {
    private ProjectDto projectDto = new ProjectDto();
    private ProjectService projectService = new ProjectService();
    private EmployeeController employeeController = new EmployeeController();
    private EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto();
    private Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(ProjectController.class);

    /**
     * <p>
     * It shows the option to Add, Update, Delete,  
     * Display project
     * </p>
     * @return {@link void} return nothing 
     */
    public void projectManangerPortal(String userId ,String password) {
        if (userId.equals("manager") && password.equals("ideai2i")) {
            logger.info("\n press 1 to create new project \n press 2 to update project \n press 3 to display all projects"
                + " \n press 4 to assign project to employee "
                + " \n press 5 to retrive employee projects \n press 6 to delete project ");
            int userChoice = scanner.nextInt();
        
            switch(userChoice) {

                case 1 :
                    try{
                        createProject();
                    } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                    }  
                    break;

                 case 2 :
                     try {
                         updateProject();
                     } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                     }  
                     
                     break;

                 case 3 :
                     try {
                         displayProjects();
                     } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                     }  
                     break;
   
                 case 4:
                     try {
                         assignProject();
                     } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                     }  
                     break;

                 case 5 : 
                     try{
                         displayEmployeeProjects();
                     } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                     }  
                     break;
                 
                case 6 :
                    try {
                        deleteProject();
                    } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                    } 
                default :
                    logger.info("Invalid input, Please try again later");  
              
            }
        } else {
            logger.info("Invalid userId and password");
        }  
    }

    /**
     * <p>
     * Create a project detail  
     * </p>
     * @return {@link void} return nothing
     */
    public void createProject() throws CustomException {
        try {
            logger.info("Enter project name: ");
            projectDto.setName(employeeController.nameValidation());
            logger.info("Enter project client name: ");
            projectDto.setClientName(employeeController.nameValidation());
            logger.info("Enter project company name: ");
            projectDto.setCompanyName(scanner.next());
            logger.info("Enter project start date: ");
            projectDto.setStartDate(employeeController.dateValidation());
            logger.info("Enter project Status: ");
            projectDto.setProjectStatus(employeeController.nameValidation());
            logger.info(projectService.addProject(projectDto));  
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }    
    }
 
    /**
     * <p>
     * Update project detail  
     * </p>
     * @return {@link void} return nothing 
     */  
    public void updateProject() throws CustomException {
        try {
            logger.info("Enter project id which project detail you want to modify :");
            int projectId = scanner.nextInt();
            logger.info("Enter new project name: ");
            projectDto.setName(employeeController.nameValidation());
            logger.info("Enter nw project client name: ");
            projectDto.setClientName(employeeController.nameValidation());
            logger.info("Enter nw project company name: ");
            projectDto.setCompanyName(scanner.next());
            logger.info("Enter project start date: ");
            projectDto.setStartDate(employeeController.dateValidation());
            logger.info("Enter new project Status: ");
            projectDto.setProjectStatus(employeeController.nameValidation());
            projectService.updateProject(projectId, projectDto);  
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }  
    }

    /**
     * <p>
     * Display all project details
     * </p>  
     * @return {@link void} return nothing 
     *
     */
    public void displayProjects() throws CustomException {
        try{
            List<ProjectDto> projectDtos = projectService.getprojects();
            for(ProjectDto projectDto : projectDtos) {
                logger.info(projectDto);
            }
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }     
    }

    /**
     * <p>
     * Assign the project to the employees
     * </p>  
     * @return {@link void} return nothing 
     *
     */
    public void assignProject() throws CustomException {
        try {
            logger.info("Enter the project id : ");
            int projectId = scanner.nextInt();
            logger.info("How many member want to add in project" + projectId );
            int employeeCount = scanner.nextInt();
            logger.info("Enter the project start date:");
            employeeProjectDto.setStartDate(employeeController.dateValidation());
            logger.info("Enter the project relieved date");
            employeeProjectDto.setRelievedDate(employeeController.dateValidation());
            logger.info("Enter the project status: " );
            employeeProjectDto.setActiveStatus(employeeController.nameValidation());
            int count = 1;
            do {
                logger.info("Please enter employee id : " );
                int projectEmployees = scanner.nextInt();
                projectService.assingProjectToEmployees(employeeProjectDto, projectId, projectEmployees);
                count++;
           } while(count <= employeeCount);
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }    
    }

    /**
     * <p>
     * Display all employee's project
     * </p>  
     * @return {@link void} return nothing 
     *
     */
    public void displayEmployeeProjects() throws CustomException  {
        try{
            List<EmployeeProjectDto> employeeProjectDto = projectService.getEmployeeProjects();
            for(EmployeeProjectDto employeeProject : employeeProjectDto) {
                logger.info(employeeProject);
            }
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }  
    }

    /**
     * <p>
     * Delete delete details
     * </p>  
     * @return {@link void} return nothing 
     *
     */
    public void deleteProject() throws CustomException {
        try {
            logger.info("Enter project id which you want to delete : ");
            int projectId = scanner.nextInt();
            logger.info(projectService.deleteProject(projectId));
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }  
    }
}