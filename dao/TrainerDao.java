package com.i2i.project.dao;

import com.i2i.project.model.Trainer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrainerDao {
    private static List<Trainer> trainers = new ArrayList<Trainer>();
    
   /**
    * <p>
    * Insert the trainer into the list
    * </p>
    * @param trainer
    * 
    */  
    public void insertTrainer(Trainer trainer) {	
        trainers.add(trainer);
    }

   /**
     * <p>
     * Retrive the trainer from the list
     * </p>
     * @return trainers 
     * 
     */
    public List<Trainer> retriveTrainer() {  
        return trainers;
    }

   /**
     * <p>
     * update the exsiting trainer in the list
     * </p>
     * @return trainers 
     * 
     */
    public void updateByIndex(int index, Trainer trainer) {
        trainers.set(index,trainer);
    }

  /**
     * <p>
     * delete the exsiting trainer in the list
     * </p>
     * @return trainers 
     * 
     */
     public void deleteTrainerByObject(Trainer trainer) {
        trainers.remove(trainer);
    }
    
}
