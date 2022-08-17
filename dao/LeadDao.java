package com.i2i.project.DAO;

import com.i2i.project.model.Lead;

import java.util.LinkedList;
import java.util.List;

public class LeadDao {
    List<Lead> trainees = new LinkedList<Lead>();

    /**
     * <p>
     * Insert the trainees name into list
     *
     * </p>
     */
    public void insertAssignedTrainees(Lead lead, boolean isNewList) {
        if (isNewList) {
            trainees = new LinkedList<Lead>();
            trainees.add(lead);
        } else { 
             trainees.add(lead);
        }     
    }
    
    /**
     * <p>
     * Retrive the trainees list
     *
     * </p>
     */
    public List<Lead> retriveAssignedTrainees() {
        return trainees;
    } 
}