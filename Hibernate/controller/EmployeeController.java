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
    private static SessionFactory factory;
    private Role role = new Role();
    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
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
                    createEmployee(Constants.TRAINER,role.getId());
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 2 :
                try {
                    createEmployee(Constants.TRAINEE, role.getId());
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;
           
            case 3 : 
                try {
                    createEmployee(Constants.MANAGER, role.getId());
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 4 :
                try {
                    logger.info("\n Select role: \n press 1 for trainer \n press 2 for trainee");
                    displayEmployees(roleOption());
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 5 :
                
                    logger.info(" Enter employee id which employee detail you want to show: ");
                    int employeeId = scanner.nextInt();
                    //displayEmployee(employeeId);
                
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
    public void createEmployee(String employeeRole, int role) throws CustomException {
        try {	
            Employee employee = new Employee();
            logger.info("Enter the employee name : ");
            employee.setName(nameValidation());  
            logger.info("Enter the employee Email id ");
            employee.setEmail(emailValidation()); 
            logger.info("Enter the employee dob ");
            employee.setDateOfBirth(dateValidation()); 
            logger.info(" Enter employee gender: \n press 1 to Male \n press 2 to Female \n press 3 to Others");
            employee.setGender(genderOption()); 
            logger.info("Enter employee mobileNumber");
            employee.setMobileNumber(mobileNumberValidation());
            logger.info("Enter the employee date of joining ");
            employee.setDateOfJoining(dateValidation()); 
            logger.info("Enter the employee batch ");
            int batch = scanner.nextInt();
            employee.setBatch(batch); 
            employee.setEmployeeRole(employeeRole);
            logger.info(employeeService.addEmployeeByRole(employee, employeeRole));
        } catch (CustomException exception) {
            throw new CustomException(exception.getMessage());
        }     
    } 

    public void displayEmployees(String employeeRole) throws CustomException {
        List<Employee> employees = employeeService.getEmployeesByRole(employeeRole);
            for(Employee employee : employees) {
                logger.info(employee);
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
        Employee employee = new Employee();
        logger.info("Enter new employee name : ");
        employee.setName(nameValidation());  
        logger.info("Enter new employee Email id ");
        employee.setEmail(emailValidation()); 
        logger.info("Enter new employee dob, Required Format is d/M/yyyy : ");
        employee.setDateOfBirth(dateValidation()); 
        logger.info("Enter new employee gender : \n press 1 to Male \n press 2 to Female \n press 3 to Others");
        String gender = scanner.next();
        employee.setGender(gender); 
        logger.info("Enter new employee mobileNumber");
        employee.setMobileNumber(mobileNumberValidation());
        logger.info("Enter new employee date of joining, Required Format is d/M/yyyy : ");
        employee.setDateOfJoining(dateValidation()); 
        logger.info("Enter new employee batch ");
        int batch = scanner.nextInt();
        employee.setBatch(batch); 
        logger.info(employeeService.updateEmployee(employee, employeeId));
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

    public String roleOption() {
        String role = null;
        boolean isValid = true;
        do {
            String employeeRole = scanner.next();

            switch(employeeRole) {
                case "1":
                    role = Roles.Trainer.role;
                    break;

                case "2":
                    role = Roles.Trainee.role;
                    break;

                case "3":
                    role = Roles.Manager.role;
                    break;

                default:
                    return "Invalid Option";
                           
            }
        } while(!isValid);
        return role;
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