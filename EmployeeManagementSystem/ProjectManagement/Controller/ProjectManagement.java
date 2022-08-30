import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProjectManagement {
    private ProjectDto projectDto = new ProjectDto();
    private ProjectService projectService = new ProjectService();
    private EmployeeController employeeController = new EmployeeController();
    private Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(ProjectManagement.class);

    public void projectManagerLogin() throws CustomException {
        System.out.println("Enter user id :");
        String userId = scanner.next();
        System.out.println("Enter you password");
        String password = scanner.next();
        projectManangerPortal(userId, password);
    }

    public void projectManangerPortal(String userId ,String password) {
        if (userId.equals("manager") && password.equals("ideai2i")) {
            logger.info("\n press 1 to create new project \n press 2 to update status \n press 3 to display all projects"
                + " \n press 4 to delete project" );
            int userChoice = scanner.nextInt();
        
            switch(userChoice) {
                case 1:
                    createProject();
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
             }
        } else {
            logger.info("Invalid userId and password");
        }  
    }

    public void createProject() {
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
            projectService.addProject(projectDto);  
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
}