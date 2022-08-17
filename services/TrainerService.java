package com.i2i.project.services;

import com.i2i.project.dao.TrainerDao;
import com.i2i.project.model.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Implementation of get the data from Controller did business logic and  
 * transfer to Data access object
 * </p>
 */
public class TrainerService {    
    private TrainerDao trainerDao = new TrainerDao();
    
    /**  
     * <p>  
     * Get the trainer input from controller and transfer to trainer Database acess object  
     * </p>
     *
     * @param trainerName
     * @param trainerId
     * @param trainerExperience
     * @param trainerSalary
     * @param traineeDob
     */
    public void addTrainer(String trainerName, String trainerId, String trainerDob, int trainerExperience, double trainerSalary) {
	Trainer trainer = new Trainer(trainerName, trainerId, trainerDob, trainerExperience, trainerSalary);
        trainerDao.insertTrainer(trainer);
    }
    
   /**  
    * <p>  
    * Get the list from Database acess object
    * </p>
    *
    * @return trainers list to controller
    * 
    */
    public List<Trainer> getTrainer() {
        List<Trainer> trainers = trainerDao.retriveTrainer();
        return trainers;
    } 
    
    /**  
    * <p>  
    * Get the name from controller and retrive it from database access object 
    * </p>
    *
    * @return trainee object 
    * 
    */
    public Trainer getTrainerById(String id) {
        for (Trainer trainer : trainerDao.retriveTrainer()){
            if (id.equals(trainer.getId()))  {
                return trainer;
            }
        } 
        return null;
    }

    /**  
    * <p>  
    * Find the index of the given name
    * </p>
    *
    * @return value
    * 
    */
    public int findTrainerIndexByName(String name) {
        int index = 0;
        for (Trainer trainer : trainerDao.retriveTrainer()) {
            if (name.equals(trainer.getName())) {
                return index;
            }
            index++; 
        }
        return -1;
    }

    /**  
     * <p>  
     * Modify the existing data from trainer and trainee list
     * </p>
     *
     * @param name
     * @param id
     * @param experience
     * @param salary
     * @param index
     * @param dob
     */
    public void updateTrainer(String name, String id, String dob, int experience, double salary, int index) {
        Trainer trainer = new Trainer(name, id, dob, experience, salary);
        trainerDao.updateByIndex(index, trainer);
    }


    /**  
    * <p>  
    * Delete the existing data from trainee list
    * </p>
    *
    * @param name
    * @param index
    * 
    */
    public void deleteTrainerAndIndex(int index) {
        trainerDao.deleteTrainerByIndex(index);
    }
}