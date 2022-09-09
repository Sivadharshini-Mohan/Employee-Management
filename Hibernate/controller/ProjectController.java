import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProjectController {
    private ProjectDto projectDto = new ProjectDto();
    private ProjectService projectService = new ProjectService();
    private EmployeeController employeeController = new EmployeeController();
    private EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto();
    private Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(ProjectController.class);

    public void projectManangerPortal(String userId ,String password) {
        if (userId.equals("manager") && password.equals("ideai2i")) {
            logger.info("\n press 1 to create new project \n press 2 to update status \n press 3 to display all projects"
                + " \n press 4 to delete project"
                + " \n press 4 to delete project \n press 5 to Assign project to employees "
                + " \n press 6 to display employee's projects " );
            int userChoice = scanner.nextInt();
        
            switch(userChoice) {
                case 1:
                    try{
                        createProject();
                    } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                    }  
                    break;

                 case 2:
                     logger.info("Enter project name : ");
                     updateProject();
                     break;

                 case 3:
                     displayProjects();
                     break;
   
                 case 4:
                     
                     break;

                 case 5: 
                     try {
                         assignProject();
                     } catch(CustomException exception) {
                        logger.info(exception.getMessage());
                     }  
                     
                     break;
                 
                 case 6:
                     displayEmployeeProjects();
                     break;

                 case 7:
                     deleteEmployeeFromProject();
                     break;
                  
             }
        } else {
            logger.info("Invalid userId and password");
        }  
    }

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

    public void updateProject() {
        try {
            logger.info("Enter project id which project detail you want to modify :");
            int projectId = scanner.nextInt();
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
            projectService.updateProject(projectId, projectDto);  
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }  
    }

    public void displayProjects() {
        try{
            List<ProjectDto> projectDtos = projectService.getprojects();
            for(ProjectDto projectDto : projectDtos) {
                logger.info(projectDto);
            }
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }     
    }

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
            int count = 0;
            do {
                logger.info("Please enter " + projectId + " employee id : " );
                int projectEmployees = scanner.nextInt();
                projectService.assingProjectToEmployees(employeeProjectDto, projectId);
                count++;
           } while(count <= employeeCount);
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }    
    }

    public void displayEmployeeProjects() {
        try{
            List<EmployeeProjectDto> employeeProjectDto = projectService.getEmployeeProjects();
            for(EmployeeProjectDto employeeProject : employeeProjectDto) {
                logger.info(employeeProject);
            }
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }  
    }

    public void deleteEmployeeFromProject() {
        try{
            logger.info("Enter project id :");
            int projectId = scanner.nextInt();
            logger.info("Enter employee id which employee you want to remove from " + projectId + " : ");
            int employeeId = scanner.nextInt();
            logger.info(projectService.deleteEmployeeProject(projectId, employeeId));
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }  

    }
}