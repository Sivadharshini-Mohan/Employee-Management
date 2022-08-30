import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;

import java.util.Scanner;

public class EmployeeManagementSystem {
    private EmployeeController employeeController = new EmployeeController();
    private ProjectManagement projectManagement = new ProjectManagement();
    private static Logger logger = Logger.getLogger(EmployeeManagementSystem.class);
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        EmployeeManagementSystem managementSystem = new EmployeeManagementSystem();
        managementSystem.managementSelection();
    }
    public void managementSelection() {
        logger.info("\n Press 1 to Employee Management operation \n Press 2 to Project Management");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1 :
                employeeController.choiceSelection();
                break;
            case 2:
                try{
                    projectManagement.projectManagerLogin();
                } catch(CustomException exception) {
                    logger.info(exception.getMessage());
                }
                break;
            default :
                System.exit(0);     
       }
   } 
}