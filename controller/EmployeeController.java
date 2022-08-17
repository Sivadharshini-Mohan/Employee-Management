package com.i2i.project.controller;

import com.i2i.project.services.TrainerService;
import com.i2i.project.services.TraineeService;
import com.i2i.project.util.ValidationUtil;
import com.i2i.project.customException.CustomException;
import com.i2i.project.model.Trainer;
import com.i2i.project.model.Trainee;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * <p>
 * Implement the input output operation of trainer and trainee
 * </p>
 */
public class EmployeeController {
    
    private Scanner scanner = new Scanner(System.in);
    private TrainerService trainerService = new TrainerService();
    private TraineeService traineeService = new TraineeService();
    LeadSignIn leadSignIn = new LeadSignIn();
    boolean is_check = true;

    public static void main(String[] args) throws CustomException {
        EmployeeController controller = new EmployeeController(); 
        try {
            controller.selectEmployeeRole(); 
        } catch(InputMismatchException e) {
            throw new CustomException("Entered value not a integer, please try again!!",e);
        }
    }

    /**
     * <p>
     * Create a trainee detail 
     * </p>
     */     
    public void createTrainee() {
        boolean isValid;
        String name = null;
        String emailId = null;
        String dob = null;			
        System.out.println("Enter the trainee name ");

        do {
            String traineeName = scanner.next();
            isValid = ValidationUtil.isValid(traineeName, ValidationUtil.NAME_REGEX);

            if (isValid) {
                name = traineeName; 
            } else {
                System.out.println("Please enter valid input!!!");
            } 

        } while (!isValid);
            
        System.out.println("Enter the trainee Email id ");
        
        do {
            String traineeId = scanner.next();
            isValid = ValidationUtil.isValid(traineeId, ValidationUtil.ID_REGEX);

            if (isValid) {
                emailId = traineeId; 
            } else {
                System.out.println("Please enter valid input!!!");
            } 
        } while (!isValid);
        
        System.out.println("Enter the trainee dob ");

        do{
            String traineeDob = scanner.next();
            isValid = ValidationUtil.isValid(traineeDob, ValidationUtil.DATE_REGEX);

            if (isValid) {
                dob = traineeDob; 
            } else {
                System.out.println("Please enter valid input!!!");
            } 

        } while (!isValid);

        int age = ValidationUtil.calculateAge(dob);
        System.out.println("Your age :" + age);
        System.out.println("Enter the trainee batch ");
        int batch = scanner.nextInt();
        System.out.println("Enter the trainee Status ");
        String status = scanner.next();
        traineeService.addTrainee(name, emailId, dob, batch, status);  
        System.out.println("Trainee data created sucessfully");       
        boolean isCondition = true;

        while (isCondition) {
           
            System.out.println(" press 1 to search trainee detail \n press 2 to display trainee detail \n"
               +" press 3 to update trainee detail \n press 4 to delete trainee detail \n press 5 to create another trainee \n press 6 to go select user role ");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    searchTraineeById();
                    break; 
    
                case 2:
                   displayTrainee();
                   break;

                case 3:
                   updateTrainee();
                   break;

                case 4 :
                    try {
                        deleteTrainer();
                    } catch (CustomException e) {
                        System.out.println(e);
                    }
                case 5:
                     createTrainee();
                     break;

                case 6:
                     selectEmployeeRole();
                     break;

                default :
                    isCondition = false;
                    
             }
         }   
              
    } 

    /**
     * <p>
     * Search trainee by using trainee's name
     * </p>
     *
     */
    public void searchTraineeById() {			      
        System.out.println("Enter trainee Email Id:");
        String id = scanner.next();                    
        Trainee trainee = traineeService.getTraineeById(id);
        displayTrainee(trainee); 
    }
    
   /**
    * <p>
    * Display the trainee's give data
    * </p>
    *
    */
    public void displayTrainee() {		                
        List<Trainee> trainees = traineeService.getTrainee();
        for(Trainee trainee : trainees) {
            System.out.println(trainee);           
        }
    }

     /**
     * <p>
     * Modify the exsiting detail of trainee
     * </p>
     *
     */
    public void updateTrainee() {
        boolean isValid;
        String name = null;
        String emailId = null;
        String dob = null;			
        System.out.println("Enter trainee name which you want to modify:");
        String traineeName = scanner.next(); 
        int index = traineeService.findTraineeIndexByName(traineeName);

        if(index >= 0) {
            System.out.println("Enter new trainee name");
            
            do {
                String updateName = scanner.next();
                isValid = ValidationUtil.isValid(updateName, ValidationUtil.NAME_REGEX);

                if (isValid) {
                    name = updateName; 
                } else {
                    System.out.println("Please enter valid input!!!");
                } 
            } while (!isValid);
            System.out.println("Enter new trainee id");

            do {
                String updateId = scanner.next();
                isValid = ValidationUtil.isValid(updateId, ValidationUtil.ID_REGEX);

                if (isValid) {
                   emailId = updateId; 
               } else {
                   System.out.println("Please enter valid input!!!");
               } 
            } while (!isValid);
            
            System.out.println("Enter your Dob");
        
            do {
                String updateDob = scanner.next();
                isValid = ValidationUtil.isValid(updateDob, ValidationUtil.DATE_REGEX);
                if (isValid) {
                   dob = updateDob; 
               } else {
                   System.out.println("Please enter valid input!!!");
               } 
            } while (!isValid);
            
            int age = ValidationUtil.calculateAge(dob);
            System.out.println("Now, your age is:" + age);
            System.out.println("Enter new trainee batch");
            int batch = scanner.nextInt();
            System.out.println("Enter new trainee status");
            String status = scanner.next();
            traineeService.updateTrainee(name, emailId, dob, batch, status, index);
            System.out.println("Trainee " + name + " detail updated sucessfully");
        } else {
            System.out.println("Invaild data");
        }

    }

     /**
     * <p>
     * Create a trainer detail
     * </p>
     *
     */
    public void createTrainer() {
        boolean isValid;
        String name = null;
        String emailId = null;
        String dob = null;				
        System.out.println("Enter the trainer name ");
       
        do {
            String traineeName = scanner.next();
            isValid = ValidationUtil.isValid(traineeName, ValidationUtil.NAME_REGEX);

            if (isValid) {
                name = traineeName; 
            } else {
                 System.out.println("Please enter valid input!!!");
            } 
        } while (!isValid);
        System.out.println("Enter the trainer Email id ");
        
        do {
            String traineeId = scanner.next();
            isValid = ValidationUtil.isValid(traineeId, ValidationUtil.ID_REGEX);

            if (isValid) {
                emailId = traineeId; 
            } else {
                System.out.println("Please enter valid input!!!");
            } 
        } while (!isValid);

        System.out.println("Enter your Dob");

        do {
            String traineeDob = scanner.next();
            isValid = ValidationUtil.isValid(traineeDob,ValidationUtil.DATE_REGEX);

            if (isValid) {
                dob = traineeDob; 
            } else {
                System.out.println("Please enter valid input!!!");
            } 
        } while (!isValid);
         
        int age = ValidationUtil.calculateAge(dob);
        System.out.println("Your age is:" + age);
        System.out.println("Enter the trainerExperience ");
        int experience = scanner.nextInt();
        System.out.println("Enter the trainer salary ");
        double salary = scanner.nextDouble();
        trainerService.addTrainer(name, emailId, dob, experience, salary);
        System.out.println("Trainer data created sucessfully"); 
        boolean isCondition = true;

        while (isCondition) {
        System.out.println(" press 1 to search trainer detail \n press 2 to display trainer detail \n"
           +" press 3 to update trainer detail \n press 4 to delete trainer detail \n press 5 to create another trainer \n press 6 to go select user role");
        System.out.println("Enter your choice");
        int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchTrainerById();     
                    break;

                case 2:
                    displayTrainer();
                    break;

                case 3:
                    updateTrainer();
                    break;

                case 4 :
                    try {
                        deleteTrainer();
                    } catch (CustomException e) {
                        System.out.println(e);
                    }
                    break;
             
                case 5:
                    createTrainer();
                    break;

                case 6:
                    selectEmployeeRole();
                    break;

                default :
                    isCondition = false;
             }
             
        } 
          
    }

    /**
     * <p>
     * Search trainer by using trainer's nameCreate a trainer detail
     * </p>
     *
     */
    public void searchTrainerById() {					
        System.out.println("Enter trainer Email Id:");
        String id = scanner.next();
        Trainer trainer = trainerService.getTrainerById(id);
        displayTrainer(trainer);
     } 
     
     /**
     * <p>
     * Display the trainer's give data
     * </p>
     *
     */
     public void displayTrainer() {
         List<Trainer> trainers = trainerService.getTrainer();
         for(Trainer trainer : trainers) {
             System.out.println(trainer);
         }
      }

     /**
     * <p>
     * Modify the exsiting detail of trainer
     * </p>
     *
     */
      public void updateTrainer() {         			     
          System.out.println("Enter trainer name which you want to modify:");
          String trainerName = scanner.next();
          int index = trainerService.findTrainerIndexByName(trainerName);
          boolean isValid ;
          String name = null;
          String emailId = null;
          String dob = null; 
          if (index >= 0){
              System.out.println("Enter new trainer name");
             
              do {
                  String updateName = scanner.next();
                  isValid = ValidationUtil.isValid(updateName, ValidationUtil.NAME_REGEX);

                  if (isValid) {
                      name = updateName; 
                  } else {
                      System.out.println("Please enter valid input!!!");
                  } 
              } while (!isValid);
              
              System.out.println("Enter new trainer Email id");
       
              do {
                  String updateId = scanner.next();
                  isValid = ValidationUtil.isValid(updateId, ValidationUtil.ID_REGEX);

                  if (isValid) {
                      emailId = updateId; 
                  } else {
                      System.out.println("Please enter valid input!!!");
                  } 

              } while (!isValid);
              System.out.println("Enter new trainer dob");
         
              do {
                  String updateDob = scanner.next();
                  isValid = ValidationUtil.isValid(updateDob, ValidationUtil.DATE_REGEX);

                  if (isValid) {
                      dob = updateDob; 
                  } else {
                      System.out.println("Please enter valid input!!!");
                  } 

              } while (!isValid);
             
              int age = ValidationUtil.calculateAge(dob);
              System.out.println("Your age is:" + age);
              System.out.println("Enter new trainer experience");
              int experience = scanner.nextInt();
              System.out.println("Enter new trainer salary");
              double salary = scanner.nextDouble();
              trainerService.updateTrainer(name, emailId, dob, experience, salary, index);
              System.out.println("Trainer " + name + " detail updated sucessfully");
         }  
     }

    public void selectEmployeeRole() {
        System.out.println("press 1 to create trainer detail \npress 2 to create trainee detail \npress 3 to Login as lead" );
        int userRole = scanner.nextInt();
          
        switch(userRole){
            case 1 :
                createTrainer();
                break;
            case 2 :
                createTrainee();
                break;
            case 3 : 
                leadLogin();
                break;
            default :

                System. exit(0);
        }
    }

    public void leadLogin() {
        System.out.println("Enter Lead EmailId:");
        String email = scanner.next();
        System.out.println("Enter you password");
        String password = scanner.next();
        leadSignIn.splittingTrainerToTrainees(email,password);
     }

    public void displayTrainee(Trainee trainee) {
            
        if (trainee == null) {
            System.out.println("Record not able to find enter existing trainee name");

        } else {
            System.out.println(trainee);
        }
         
    }
     
    public void displayTrainer(Trainer trainer) {				

            if (trainer == null) {
                System.out.println("Record not able to find enter existing trainer name ");
            } else {
                 System.out.println(trainer);
            }
    }

    /**
     * <p>
     * Delete the trainee detail from list
     * </p>
     *
     */
    public void deleteTrainee() throws CustomException {						
        System.out.println("Enter trainee EmailId which you want to remove:");
        String traineeEmail = scanner.next();
        traineeService.deleteTraineeByEmail(traineeEmail);
    }

    /**
     * <p>
     * Delete the trainer detail from list
     * </p>
     *
     */
    public void deleteTrainer() throws CustomException {                                		
        System.out.println("Enter trainer EmailId which you want to remove:");
        String trainerEmail = scanner.next();
        trainerService.deleteTrainerByEmail(trainerEmail);
            System.out.println("Trainer " + trainerEmail + " deleted sucessfully");
        
    }
    
}