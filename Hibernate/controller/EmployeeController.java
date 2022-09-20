package com.i2i.annotation.controller;

import com.i2i.annotation.common.Constants;
import com.i2i.annotation.common.CustomException;
import com.i2i.annotation.common.Gender;
import com.i2i.annotation.common.ValidationUtil;
import com.i2i.annotation.dto.EmployeeDto;
import com.i2i.annotation.model.Role;
import com.i2i.annotation.service.EmployeeService;
import com.i2i.annotation.dao.RoleDao;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * <p>
 * EmployeeController controlls the employee,
 * Get the input from user sent it into service class
 * Create, Read, Update and Delete (CRUD) 
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 **/
public class EmployeeController {
    private static RoleDao roleDao = new RoleDao();
    private static Logger logger = Logger.getLogger(EmployeeController.class);
    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
    private static SessionFactory factory;
    private Role role = new Role();

    public static void main(String[] args) {
        EmployeeController controller = new EmployeeController();
        BasicConfigurator.configure(); 
        controller.choiceSelection();
        roleDao.setDefaultRole();
    }
    
    /**
     * <p>
     * It shows the option to Add, Update, Delete,  
     * Display employee
     * </p>
     * @return {@link void} return nothing 
     */
    public void choiceSelection() {
        int userChoice = 0;
        logger.info("\n press 1 to create trainer detail \n press 2 to create trainee detail \n press 3 to create project manager detail "
            + " \n press 4 to display all employees"
            + " \n press 5 to display employee \n press 6 to update employee detail \n press 7 to delete the employee detail "
            + " \n press 8 to project manager portal");
        
        if(scanner.hasNextInt()) {
            userChoice= scanner.nextInt();
        } else {
            logger.error("Invalid choice please, try again later");
           
        }

        switch (userChoice) {

            case 1 :
                createEmployee(Constants.TRAINER);
                break;

            case 2 :
                createEmployee(Constants.TRAINEE);
                break;
           
            case 3 : 
                createEmployee(Constants.MANAGER);
                break;

            case 4 :
                displayEmployees();
                break;

            case 5 :
                logger.info(" Enter employee id which employee detail you want to show: ");
                int employeeId = scanner.nextInt();
                displayEmployeeById(employeeId); 
                break;
            
            case 6 :
                updateEmployee();
                break;

            case 7 :
                deleteEmployee();
                break;

            case 8 :
                projectManagerLogin();            
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
     * @return {@link void} return nothing
     */
    public void createEmployee(String employeeRole) {
        try {	
            EmployeeDto employeeDto = new EmployeeDto();
            logger.info("Enter the employee name : ");
            employeeDto.setName(nameValidation());  
            logger.info("Enter the employee Email id ");
            employeeDto.setEmailId(emailValidation()); 
            logger.info("Enter the employee dob ");
            employeeDto.setDateOfBirth(dateValidation()); 
            logger.info(" Enter employee gender: \n press 1 to Male \n press 2 to Female \n press 3 to Others");
            employeeDto.setGender(genderOption()); 
            logger.info("Enter employee mobileNumber");
            employeeDto.setMobileNumber(mobileNumberValidation());
            logger.info("Enter the employee date of joining");
            employeeDto.setDateOfJoining(dateValidation()); 
            logger.info("Enter the employee batch ");
            int batch = scanner.nextInt();
            employeeDto.setBatch(batch); 
            employeeDto.setStatus(Constants.ACTIVE);
            logger.info(employeeService.addEmployeeByRole(employeeDto, employeeRole));
        } catch(CustomException exception) {
                    logger.info(exception.getMessage());
        }
    } 

    /**
     * <p>
     * Display all employee details
     * </p>  
     * @return {@link void} return nothing 
     *
     */
    public void displayEmployees() {
        try {
            List<EmployeeDto> employees = employeeService.getEmployees();
            for(EmployeeDto employee: employees) {
                logger.info(employee);
            }
        }  catch(CustomException exception) {
             logger.info(exception.getMessage());
        }
    }

    /**
     * <p>
     * Display employee details by user given id
     * </p>  
     * @param id 
     * @return {@link void} return nothing 
     */
    public void displayEmployeeById(int id) {
        try {
            EmployeeDto employeeDto = employeeService.getEmployeeById(id);
            logger.info(employeeService.getEmployeeById(id));
            logger.info(employeeDto);
        }  catch(CustomException exception) {
            logger.info(exception.getMessage());
        }
    }
     
    /**
     * <p>
     * Update employee detail  
     * </p>
     * @return {@link void} return nothing 
     */  
    public void updateEmployee() {
        try {
            logger.info("Enter employee id which employee detail you want to modify :");
            int employeeId = scanner.nextInt();
            EmployeeDto employeeDto = new EmployeeDto();
            logger.info("Enter new employee name : ");
            employeeDto.setName(nameValidation());  
            logger.info("Enter new employee Email id ");
            employeeDto.setEmailId(emailValidation()); 
            logger.info("Enter new employee dob, Required Format is d/M/yyyy : ");
            employeeDto.setDateOfBirth(dateValidation()); 
            logger.info("Enter new employee gender : \n press 1 to Male \n press 2 to Female \n press 3 to Others");
            employeeDto.setGender(genderOption()); 
            logger.info("Enter new employee mobileNumber");
            employeeDto.setMobileNumber(mobileNumberValidation());
            logger.info("Enter new employee date of joining, Required format is d/M/yyyy : ");
            employeeDto.setDateOfJoining(dateValidation()); 
            logger.info("Enter new employee batch ");
            int batch = scanner.nextInt();
            employeeDto.setBatch(batch);
            logger.info("Enter new role");
            String role = scanner.next(); 
            logger.info(employeeService.updateEmployee(employeeDto, employeeId, role));
        } catch(CustomException exception) {
             logger.info(exception.getMessage());
        }
    }
    
   /**
    * <p>
    * Delete employee detail  
    * </p>
    * @return {@link void} return nothing 
    *
    */   
    public void deleteEmployee() {
        try{
            logger.info("Enter the employee id which employee you want to delete :");
            int employeeId = scanner.nextInt();
            logger.info(employeeService.deleteEmployeeById(employeeId));
        } catch(CustomException exception) {
            logger.info(exception.getMessage());
        }
    }

   /**
    * <p>
    * Manager login portal  
    * </p>
    * @return {@link void} return nothing 
    *
    */   
    public void projectManagerLogin() {
        logger.info("Enter user id :");
        String userId = scanner.next();
        logger.info("Enter you password");
        String password = scanner.next();
        ProjectController projectController = new ProjectController();
        projectController.projectManangerPortal(userId, password);
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
		logger.error("Invalid Date Format");
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

                case "1" :
                    gender = Gender.Male.gender;
                    break;

                case "2" :
                    gender = Gender.Female.gender;
                    break;

                case "3" :
                    gender = Gender.Others.gender;
                    break;

                default :
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