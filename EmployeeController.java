import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

/**
 * <p>
 * Implementation of get input from employee
 * </p>
 */
public class EmployeeController {
    private static Logger logger = Logger.getLogger(EmployeeController.class);
    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
    EmployeeController employeeController = new EmployeeController();
    boolean is_check = true;
    boolean isValid = true;	
	
    public static void main(String[] args) {
        EmployeeController controller = new EmployeeController();
        BasicConfigurator.configure(); 
        controller.selectEmployeeRole();
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
        logger.info("Enter the employee role ");
        String role = scanner.next();
        EmployeeDto employeeDto = new EmployeeDto(name, email, dob, gender, mobileNumber, experience, batch, role);
        employeeService.addEmployee(employeeDto);  
        logger.info("Employee data created sucessfully");       
        boolean isCondition = true;
    } 

    public String nameValidation() {
        String name = null;
        do {
            String employeeName = scanner.next();
            boolean isCorrect = ValidationUtil.isValid(employeeName, ValidationUtil.NAME_REGEX);

            if (isCorrect) {
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
         do {
            String employeeId = scanner.next();
            boolean isCorrect = ValidationUtil.isValid(employeeId, ValidationUtil.ID_REGEX);

            if (isCorrect) {
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
        do {
            String employeeDob = scanner.next();
            boolean isCorrect = ValidationUtil.isValid(employeeDob, ValidationUtil.DATE_REGEX);

            if (isCorrect) {
                dob = employeeDob; 
                break;
            } else {
                logger.error("Please enter valid input!!!");
            } 
        } while(!isValid);
        return dob;
    }
   
    public void selectEmployeeRole() {
        logger.info("press 1 to create employee detail \npress 2 to Login as lead" );
        int userRole = scanner.nextInt();
          
        switch(userRole){
            case 1 :
                createEmployee();
                break;
            default :
                System. exit(0);
        }
    }  
}