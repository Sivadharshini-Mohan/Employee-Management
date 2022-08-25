import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
import java.util.Scanner;
import java.util.List;

/**
 * <p>
 * Implementation of get input from employee
 * </p>
 */
public class EmployeeController {
    private static Logger logger = Logger.getLogger(EmployeeController.class);
    private Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeService();

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
                try {
                    createEmployee("Trainer");
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 2 :
                try {
                    createEmployee("Trainee");
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
     */     
    public void createEmployee(String employeeRole) throws CustomException {
        try {	
            EmployeeDto employeeDto = new EmployeeDto();
            logger.info("Enter the employee name : ");
            employeeDto.setName(nameValidation());  
            logger.info("Enter the employee Email id ");
            employeeDto.setEmailId(emailValidation()); 
            logger.info("Enter the employee dob ");
            employeeDto.setName(dateValidation()); 
            logger.info("Enter employee gender");
            String gender = scanner.next();
            employeeDto.setGender(gender); 
            logger.info("Enter employee mobileNumber");
            employeeDto.setMobileNumber(mobileNumberValidation());
            logger.info("Enter the employee date of joining ");
            employeeDto.setDoj(dateValidation()); 
            logger.info("Enter the employee batch ");
            int batch = scanner.nextInt();
            employeeDto.setBatch(batch); 
            employeeService.addEmployee(employeeDto, employeeRole);
        } catch (CustomException exception) {
            throw new CustomException(exception.getMessage());
        }  
        logger.info("Employee data created sucessfully");  
        logger.info("\n Press 1 to Display the Employee detail");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 :
                List<EmployeeDto> employees = employeeService.retriveEmployee(employeeRole);
                for(EmployeeDto employee : employees) {
                    logger.info(employee);
                }
                break;
        }     
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
        System.out.println(name);
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

    public String dateValidation() {
         String date = null;
         boolean isValid = false;         
         do {
            String validateDate = scanner.next();
            isValid = ValidationUtil.isValid(validateDate, ValidationUtil.DATE_REGEX);

            if (isValid) {
                date = validateDate; 
                break;
            } else {
                logger.error("Please enter valid input!!!");
            } 
        } while(!isValid);
        System.out.println(date);
        return date;
    }
    
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