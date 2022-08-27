import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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
        logger.info("\n press 1 to create trainer detail \n press 2 to create trainee detail \n press 3 to display all employees"
            + " \n press 4 to display employee \n press 5 to update employee detail \n press 6 to delete employee detail" );
        int userChoice = scanner.nextInt();
          
        switch(userChoice){
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
                    logger.info("\n Select role: \n press 1 for trainer \n press 2 for trainee");
                    int employeeRole = scanner.nextInt();
                    displayEmployees(employeeRole);
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;

            case 4 :
                try {
                    logger.info(" Enter employee id which employee detail you want to show: ");
                    int employeeId = scanner.nextInt();
                    displayEmployee(employeeId);
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;
            
            case 5 :
                try {
                    updateEmployee();
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                } 
                break;

           case 6 :
               try {
                    deleteEmployee();
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
            employeeDto.setDob(dateValidation()); 
            logger.info(" Enter employee gender: \n press 1 to Male \n press 2 to Female");
            employeeDto.setGender(genderOption()); 
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
    } 

    public void displayEmployees(int employeeRole) throws CustomException {
        List<EmployeeDto> employeeDtos = employeeService.getEmployees(employeeRole);
            for(EmployeeDto employeeDto : employeeDtos) {
                logger.info(employeeDto);
            }
    }

    public void displayEmployee(int employeeId) throws CustomException {
        List<EmployeeDto> employeeDtos = employeeService.getEmployee(employeeId);
            for(EmployeeDto employeeDto : employeeDtos) {
                logger.info(employeeDto);
            }
    }

    public void updateEmployee() throws CustomException {
        logger.info("Enter email id which employee data you want modify :");
        String email = emailValidation();
        EmployeeDto employeeDto = new EmployeeDto();
        logger.info("Enter new employee name : ");
        employeeDto.setName(nameValidation());  
        logger.info("Enter new employee Email id ");
        employeeDto.setEmailId(emailValidation()); 
        logger.info("Enter new employee dob, Required Format is d/M/yyyy : ");
        employeeDto.setDob(dateValidation()); 
        logger.info("Enter new employee gender : ");
        String gender = scanner.next();
        employeeDto.setGender(gender); 
        logger.info("Enter new employee mobileNumber");
        employeeDto.setMobileNumber(mobileNumberValidation());
        logger.info("Enter new employee date of joining, Required Format is d/M/yyyy : ");
        employeeDto.setDoj(dateValidation()); 
        logger.info("Enter new employee batch ");
        int batch = scanner.nextInt();
        employeeDto.setBatch(batch); 
        employeeService.updateEmployee(employeeDto, email);
    }

    public void deleteEmployee() throws CustomException {
        logger.info("Enter email id which employee data you want to delete :");
        String email = emailValidation();
        employeeService.deleteEmployee(email);
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
                    return "Invalid Option ";
                           
            }
        } while(!isValid);
        return gender;

        
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