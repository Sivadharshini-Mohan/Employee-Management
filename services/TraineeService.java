package com.i2i.project.services;

import com.i2i.project.dao.TraineeDao;
import com.i2i.project.model.Trainee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Implementation of get the data from Controller did business logic and  
 * transfer to Data access object
 * </p>
 */
public class TraineeService {    
    private TraineeDao traineeDao = new TraineeDao();

    /**  
     * <p>  
     * Get the trainee input from controller and transfer to trainee Database acess object  
     * </p>
     *
     * @param traineeName
     * @param traineeId
     * @param traineeBatch 
     * @param traineeStatus
     * @param traineeDob
     * 
     */
    public void addTrainee(String traineeName, String traineeId, String traineeDob, int traineeBatch, String traineeStatus) {		
        Trainee trainee = new Trainee(traineeName, traineeId, traineeDob, traineeBatch, traineeStatus);
        traineeDao.insertTrainee(trainee);
    }

    /**  
    * <p>  
    * Get the list from Database acess object
    * </p>
    *
    * @return trainees list to controller
    * 
    */
    public List<Trainee> getTrainee() {
        List<Trainee> trainees = traineeDao.retriveTrainee();
        return trainees;
    }

   /**  
    * <p>  
    * Get the name from controller and retrive it from database access object 
    * </p>
    *
    * @return trainer object 
    * 
    */
    public Trainee getTraineeById(String id) {
        for (Trainee trainee : traineeDao.retriveTrainee()) {
            if (id.equals(trainee.getId())) {
                return trainee;
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
    public int findTraineeIndexByName(String name) {
        int index = 0;
        for (Trainee trainee : traineeDao.retriveTrainee()) {
            if (name.equals(trainee.getName())) {
                return index;
            }   
            index++;
        }
        return -1;
    }



    /**  
     * <p>  
     * Modify the existing data from trainee list
     * </p>
     * 
     * @param name
     * @param id
     * @param batch
     * @param status
     * @param index
     * 
     */
    public void updateTrainee(String name, String id, String dob, int batch, String status, int index) {
        Trainee trainee = new Trainee(name, id, dob, batch, status);
        traineeDao.updateByIndex(index, trainee);
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
    public void deleteTraineeByEmail(String email) {
        for(Trainee trainee : traineeDao.retriveTrainee()) {
            if (email.equals(trainee.getId())) {
               traineeDao.deleteTraineeByObject(trainee);
               break;
            }
        }
    }

}