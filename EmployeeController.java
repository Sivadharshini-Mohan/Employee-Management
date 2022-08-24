import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
import java.util.Scanner;

/**
 * <p>
 * Implementation of get input from employee
 * </p>
 */
public class EmployeeController {
    private static Logger logger = Logger.getLogger(EmployeeController.class);
    private Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeService();
    EmployeeController employeeController;
    boolean is_check = true;

    public static void main(String[] args) {
        EmployeeController controller = new EmployeeController();
        BasicConfigurator.configure(); 
        controller.selectEmployeeRole();
    }

    public void selectEmployeeRole() {
        logger.info("press 1 to create trainer detail \npress 2 to create trainee detail" );
        int userRole = scanner.nextInt();
          
        switch(userRole){
            case 1 :
                createEmployee();
                employeeService.employeeRole("Trainer");
                break;
            case 2 :
                createEmployee();
                employeeService.employeeRole("Trainee");
                break;
            default :
                System. exit(0);
        }
    }  
    /**
     * <p>
     * Create a employee detail 
     * </p>
     */     
    public void createEmployee() {	
        logger.info("Enter the employee name : ");
        String name = nameValidation();    
        logger.info("Enter the employee Email id ");
        String email = emailValidation();
        logger.info("Enter the employee dob ");
        String dob = dobValidation();
        int age = ValidationUtil.calculateAge(dob);
        logger.info("Your age :" + age);
        logger.info("Enter employee gender");
        String gender = scanner.next();
        logger.info("Enter employee mobileNumber");
        long mobileNumber = scanner.nextLong();
        logger.info("Enter the employee experience ");
        int experience = scanner.nextInt();
        logger.info("Enter the employee batch ");
        int batch = scanner.nextInt();
        EmployeeDto employeeDto = new EmployeeDto(name, email, dob, gender, mobileNumber, experience, batch);
        employeeService.addEmployee(employeeDto);  
        logger.info("Employee data created sucessfully");       
        boolean isCondition = true;
        

    } 

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

    public String dobValidation() {
        String dob = null;
        boolean isValid = false;
        do {
            String employeeDob = scanner.next();
            isValid = ValidationUtil.isValid(employeeDob, ValidationUtil.DATE_REGEX);

            if (isValid) {
                dob = employeeDob; 
                break;
            } else {
                logger.error("Please enter valid input!!!");
            } 
        } while(!isValid);
        return dob;
    }
}