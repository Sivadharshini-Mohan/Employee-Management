package com.i2i.project.controller;

import com.i2i.project.services.TrainerService;
import com.i2i.project.services.TraineeService;
import com.i2i.project.services.LeadService;
import com.i2i.project.DAO.TrainerDao;
import com.i2i.project.DAO.TraineeDao;
import com.i2i.project.model.Lead;
import com.i2i.project.model.Trainer;
import com.i2i.project.model.Trainee;



import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

/**
 * <p>
 * Check the login credential
 * Give some option to lead to map
 * </p>
 */ 
public class LeadSignIn {
    Scanner scanner = new Scanner(System.in);
    TrainerService trainerService = new TrainerService();
    TraineeService traineeService = new TraineeService();
    TraineeDao traineeDao = new TraineeDao();
    LeadService leadService = new LeadService();
    Map<String, List<Lead>> assignedMap = new HashMap<String, List<Lead>>();
     
    /**
     * <p>
     * Display all trainer name and email id
     * </p>
     */
    public void displayTrainers() {
        List<Trainer> trainers = trainerService.getTrainer();

        for(Trainer trainer : trainers) {
            System.out.println(trainer.getName() + " - " + trainer.getId());   
        }

        System.out.println("Count of trainer: " + trainers.size());    
    }

    /**
     * <p>
     * Display all trainer name and email id
     * </p>
     */
     public void displayTrainees() {
         List<Trainee> trainees = traineeService.getTrainee();

         for(Trainee trainee : trainees) {
             System.out.println(trainee.getName() + " - " + trainee.getId());
         }

         System.out.println("Count of trainee: " + trainees.size());    
     }

    /**
    * <p>
    * check the login crendential
    * @param email check the lead email id 
    * @param paasword check the lead password
    * </p>
    */
    public void splittingTrainerToTrainees(String email, String password) {
        boolean isContinue = true;
        while(isContinue) {
            List<Trainee> trainees = traineeService.getTrainee();
            List<Trainer> trainers = trainerService.getTrainer();
            if (email.equals("lead@gmail.com") && password.equals("lead")) {
                System.out.println("\n1. Manual Mapping. \n2. Automatic Mapping. \n3. Exit. \n4. View all teams.");
                int choice = scanner.nextInt();
                         
                switch(choice) {     
                    case 1:
                        manualMap();
                        break;

                    case 2 :
                        autoMap();
                        break;
                    
                    case 3 :
                        isContinue = false;
                        break; 

                    case 4 : 
                        viewAll();
                        break;
                
                    default :
                        System.out.println("Thank you");                                              
                }                   
            }    
        }
    }

    public void manualMap() {
        System.out.println("All trainers and trainees");
        displayTrainers();
        displayTrainees();
        System.out.println("Enter number of trainer count you want to assign:");
        int trainerSize = scanner.nextInt();
        System.out.println("Enter the number of trainee count you want to assign a trainer:");
        int traineeSize = scanner.nextInt();
        String trainerName;
        for(int trainerLoop = 0; trainerLoop < trainerSize; trainerLoop++) {
            System.out.println("\nEnter the " + (trainerLoop+1) + " trainer email-Id");
            String trainerId = scanner.next();
            boolean isNewList = true;

            for(int traineeLoop = 0; traineeLoop < traineeSize; traineeLoop++) {
                System.out.println("Enter the name of the trainee");
                String traineeName = scanner.next();                                     
                Lead lead = new Lead(traineeName);                            
                leadService.addTrainees(lead, isNewList);
                isNewList = false;
                break;
            }
            List list = leadService.getAssignedTrainees();
            assignedMap.put(trainerId, list);
            break; 
        }

    }

    public void autoMap() {
        List list = leadService.getAssignedTrainees();
        assignedMap.put("sivadharshini@gmail.com",list);
        assignedMap.put("john@gmail.com",list);
        System.out.println(assignedMap);

        for (String trainerEmail: assignedMap.keySet()) {
            System.out.println("Tranier Email id -" + trainerEmail);
        }
        for (List<Lead> traineesName : assignedMap.values()) {
            System.out.println("Trainees -" + traineesName);
        } 
    } 

    public void viewAll() {
        for (Map.Entry<String, List<Lead>> entry : assignedMap.entrySet()) {
            System.out.println("................................................................................");
            System.out.println("Trainer \t\t\t Trainee");
            System.out.println(entry.getKey() + " = " + entry.getValue());
            System.out.println("................................................................................");
        } 
    }
}