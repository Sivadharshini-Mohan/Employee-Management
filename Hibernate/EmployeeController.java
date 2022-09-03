import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

/**
 * <p>
 * EmployeeController class  will communicate with user and get the options to 
 * Add, Display, Update and Delete (CRUD) 
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 **/
public class EmployeeController {
    private static Logger logger = Logger.getLogger(EmployeeController.class);
    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
    private ProjectService projectService = new ProjectService();
    private static SessionFactory factory;
    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            hibernateException.printStackTrace();
        }

        EmployeeController controller = new EmployeeController();
        BasicConfigurator.configure(); 
        controller.choiceSelection();
    }
    
    /**
     * <p>
     * It shows the option to Add, Update, Delete,  
     * Display employee
     * </p>
     * @return {@link void} return nothing 
     */
    public void choiceSelection() {
        logger.info("\n press 1 to create trainer detail \n press 2 to create trainee detail \n press 3 to create project manager detail "
            + " \n press 4 to display all employees"
            + " \n press 5 to display employee \n press 6 to update employee detail \n press 7 to delete employee detail"
            + " \n press 8 to project manager portal");
            
        int userChoice = scanner.nextInt();
          
        switch(userChoice) {

            case 1 :
                try {
                    createEmployee(Constants.TRAINER);
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 2 :
                try {
                    createEmployee(Constants.TRAINEE);
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;
           
            case 3 : 
                try {
                    createEmployee(Constants.MANAGER);
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 4 :
                try {
                    logger.info("\n Select role: \n press 1 for trainer \n press 2 for trainee");
                    int employeeRole = scanner.nextInt();
                    displayEmployees(employeeRole);
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 5 :
                try {
                    logger.info(" Enter employee id which employee detail you want to show: ");
                    int employeeId = scanner.nextInt();
                    displayEmployee(employeeId);
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;
            
            case 6 :
                try {
                    updateEmployee();
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                } 
                break;

           case 7 :
               try {
                    deleteEmployee();
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                } 
                break; 
            
            case 8 :
                try {
                    projectManagerLogin();
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }                 
                break;         
            
            default :
                System. exit(0);
        }
    }  

    /**
     * <p>
     * Create a employee detail  
     * </p>
     * @param employeeRole Trainer/Trainee
     * @throws CustomException 
     * @return {@link void} return nothing
     */     
    public void createEmployee(String employeeRole) throws CustomException {
        try {	
            EmployeeDto employeeDto = new EmployeeDto();
            logger.info("Enter the employee name : ");
            employeeDto.setName(nameValidation());  
            logger.info("Enter the employee Email id ");
            employeeDto.setEmailId(emailValidation()); 
            logger.info("Enter the employee dob ");
            employeeDto.setDob(dateValidation()); 
            logger.info(" Enter employee gender: \n press 1 to Male \n press 2 to Female \n press 3 to Others");
            employeeDto.setGender(genderOption()); 
            logger.info("Enter employee mobileNumber");
            employeeDto.setMobileNumber(mobileNumberValidation());
            logger.info("Enter the employee date of joining ");
            employeeDto.setDoj(dateValidation()); 
            logger.info("Enter the employee batch ");
            int batch = scanner.nextInt();
            employeeDto.setBatch(batch); 
            logger.info(employeeService.addEmployeeByRole(employeeDto, employeeRole));
        } catch (CustomException exception) {
            throw new CustomException(exception.getMessage());
        }     
    } 

    /**
     * <p>
     * Display all employee details
     * </p>
     * @param employeeRole Trainer/Trainee
     * @throws CustomException  
     * @return {@link void} return nothing 
     *
     */     
    public void displayEmployees(int employeeRole) throws CustomException {
        List<EmployeeDto> employeeDtos = employeeService.getEmployeesByRole(employeeRole);
            for(EmployeeDto employeeDto : employeeDtos) {
                logger.info(employeeDto);
            }
    }

    /**
     * <p>
     * Display particular employee detail
     * </p>
     * @param employeeId id of employee
     * @throws CustomException  
     * @return {@link void} return nothing 
     *
     */     
    public void displayEmployee(int employeeId) throws CustomException {
        List<EmployeeDto> employeeDtos = employeeService.getEmployeeById(employeeId);
            for(EmployeeDto employeeDto : employeeDtos) {
                logger.info(employeeDto);
            }
    }

    /**
     * <p>
     * Update employee detail  
     * </p>
     * @throws CustomException 
     * 
     * @return {@link void} return nothing 
     *
     */     
    public void updateEmployee() throws CustomException {
        logger.info("Enter employee id which employee detail you want to modify :");
        int employeeId = scanner.nextInt();
        EmployeeDto employeeDto = new EmployeeDto();
        logger.info("Enter new employee name : ");
        employeeDto.setName(nameValidation());  
        logger.info("Enter new employee Email id ");
        employeeDto.setEmailId(emailValidation()); 
        logger.info("Enter new employee dob, Required Format is d/M/yyyy : ");
        employeeDto.setDob(dateValidation()); 
        logger.info("Enter new employee gender : \n press 1 to Male \n press 2 to Female \n press 3 to Others");
        String gender = scanner.next();
        employeeDto.setGender(gender); 
        logger.info("Enter new employee mobileNumber");
        employeeDto.setMobileNumber(mobileNumberValidation());
        logger.info("Enter new employee date of joining, Required Format is d/M/yyyy : ");
        employeeDto.setDoj(dateValidation()); 
        logger.info("Enter new employee batch ");
        int batch = scanner.nextInt();
        employeeDto.setBatch(batch); 
        logger.info(employeeService.updateEmployee(employeeDto, employeeId));
    }

    /**
     * <p>
     * Delete employee detail  
     * </p>
     * @throws CustomException 
     * @return {@link void} return nothing 
     *
     */     
    public void deleteEmployee() throws CustomException {
        logger.info("Enter employee id which employee data you want to delete :");
        int employeeId = scanner.nextInt();
        logger.info(employeeService.deleteEmployee(employeeId));
    }

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
                + " \n press 4 to delete project \n press 5 to Assign project to employees "
                + " \n press 6 to display employee's projects " );
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
                 case 5: 
                     assignProject();
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

    public void createProject() {
        try {
            ProjectDto projectDto = new ProjectDto();
            logger.info("Enter project name: ");
            projectDto.setName(nameValidation());
            logger.info("Enter project client name: ");
            projectDto.setClientName(nameValidation());
            logger.info("Enter project company name: ");
            projectDto.setCompanyName(scanner.next());
            logger.info("Enter project start date: ");
            projectDto.setStartDate(dateValidation());
            logger.info("Enter project Status: ");
            projectDto.setProjectStatus(nameValidation());
            projectService.addProject(projectDto);  
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }    
    }

    public void updateProject() {
        try {
            ProjectDto projectDto = new ProjectDto();
            logger.info("Enter project id which project detail you want to modify :");
            int projectId = scanner.nextInt();
            logger.info("Enter project name: ");
            projectDto.setName(nameValidation());
            logger.info("Enter project client name: ");
            projectDto.setClientName(nameValidation());
            logger.info("Enter project company name: ");
            projectDto.setCompanyName(scanner.next());
            logger.info("Enter project start date: ");
            projectDto.setStartDate(dateValidation());
            logger.info("Enter project Status: ");
            projectDto.setProjectStatus(nameValidation());
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

    public void assignProject() {
        try {
            logger.info("Enter the project id : ");
            int projectId = scanner.nextInt();
            logger.info("How many member want to add in project" + projectId );
            int employeeCount = scanner.nextInt();
            logger.info("Enter the project start date:");
            LocalDate startDate = dateValidation();
            logger.info("Enter the project relieved date");
            LocalDate relieveDate = dateValidation();
            int count = 0;
            do {
                logger.info("Please enter " + projectId + "employee id : " );
                int projectEmployees = scanner.nextInt();
                projectService.assingProjectToEmployees(projectId, projectEmployees, startDate, relieveDate);
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
    /**
     * <p>
     * This method is used to validate employee name. 
     * </p>  
     * @return {@link string} return valid name
     */
    public String nameValidation() {
        String name = null;
         boolean isValid = false;         
         do {
            String employeeName = scanner.next();
            isValid = ValidationUtil.isValid(employeeName, ValidationUtil.NAME_REGEX);

            if (isValid) {
                name = employeeName; 
                break;
            } else {
                logger.error("Please enter valid input!!!");
            } 
        } while(!isValid);
        return name;
    }
    
    /**
     * <p>
     * This method is used to validate employee email id
     * </p>  
     * @return {@link string} return valid email id
     */
    public String emailValidation() {
         String emailId = null;
         boolean isValid = false;         
         do {
            String employeeId = scanner.next();
            isValid = ValidationUtil.isValid(employeeId, ValidationUtil.ID_REGEX);

            if (isValid) {
                emailId = employeeId; 
                break;
            } else {
                logger.error("Please enter valid input!!!");
            } 
        } while(!isValid);
        return emailId;
    }

     /**
     * <p>
     * This method is used to validate the date. 
     * </p>  
     * @return {@link LocalDate} return valid date
     */
    public LocalDate dateValidation() {
         DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);
         LocalDate date = null;
         boolean isValid = false;         
         do {
            String tempDate = scanner.next();
            isValid = ValidationUtil.isValid(tempDate, ValidationUtil.DATE_REGEX);
            try {
		date = LocalDate.parse(tempDate, format);                
		isValid = true;
                return date;
	    } catch (DateTimeParseException e) {
		System.out.println("Invalid Date Format");
	    }
        } while(!isValid);
        return null;
    }
    
     /**
     * <p>
     * This method is used to select the gender option by using enum
     * </p>  
     * @return {@link string} return gender 
     */
    public String genderOption() {
        String gender = null;
        boolean isValid = true;
        do {
            String employeeGender = scanner.next();

            switch(employeeGender) {
                case "1":
                    gender = Gender.Male.gender;
                    break;

                case "2":
                    gender = Gender.Female.gender;
                    break;

                case "3":
                    gender = Gender.Others.gender;
                    break;

                default:
                    return "Invalid Option";
                           
            }
        } while(!isValid);
        return gender;
    }  

    /**
     * <p>
     * This method is used to validate employee mobile number
     * </p>  
     * @return {@link long} return valid mobile number
     */
    public long mobileNumberValidation() {
        String employeeMobileNumber;
        boolean isValid = false;
        do {
            employeeMobileNumber = scanner.next();
            isValid = ValidationUtil.isValid(employeeMobileNumber, ValidationUtil.MOBILE_NUMBER_REGEX);

            if (isValid) {
                break;
            } else {
                logger.error("Please enter valid input!!!");
            } 
        } while(!isValid);
        long mobileNo = Long.valueOf(employeeMobileNumber);
        return mobileNo; 
    }
}