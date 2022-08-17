package com.i2i.project.services;

import com.i2i.project.dao.LeadDao;
import com.i2i.project.dao.TrainerDao;
import com.i2i.project.dao.TraineeDao;
import com.i2i.project.model.Trainer;
import com.i2i.project.model.Trainee;
import com.i2i.project.model.Lead;

import java.util.List;

public class LeadService {
    LeadDao leadDao = new LeadDao();
    TrainerDao trainerDao = new TrainerDao();
    TraineeDao traineeDao = new TraineeDao();
    
    /**
     * <p>
     * Insert the trainees name into list by calling leadDao
     * @param lead lead list has list of trainees
     * @param isNewList want to create new list or add existing list 
     * </p>
     */
    public void addTrainees (Lead lead, boolean isNewList) {
        leadDao.insertAssignedTrainees(lead,isNewList);
    }

   /**
     * <p>
     * Retrive the trainees name into list by calling leadDao
     *
     * </p>
     */
    public List getAssignedTrainees() {
        List<Lead> trainees = leadDao.retriveAssignedTrainees();
        return trainees;
    }

}