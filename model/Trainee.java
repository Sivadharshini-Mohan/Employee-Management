package com.i2i.project.model;

public class Trainee extends Employee {
    private int batch;
    private String status;

    Trainee(String name, String id, String dob, int batch, String status) {
        super(name, id, dob);
        this.batch = batch;
        this.status = status;
    }

    public int getBatch() {  
        return batch;  
    }

    public void setBatch( int batch) {  
        this.batch = batch;  
    }  

    public String getStatus() {  
        return status;  
    }  

    public void setStatus(String status) {  
        this.status = status;  
    } 

    public String toString() {
        return(" Name: " + name+ "\n ID: " +id +"\n Batch: " +batch+ "\n Status:" + status);
        
    }
}