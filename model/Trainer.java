package com.i2i.project.model;

public class Trainer extends Employee {
    private int experience;
    private double salary;

    Trainer(String name, String id, String dob, int experience, double salary) {
        super(name,id,dob);
        this.experience = experience;
        this.salary = salary;
    }

    public int getExperience() {  
        return experience;  
    }

    public void setExperience(int experience) { 
        this.experience = experience;  
    }  

    public double getSalary() {  
        return salary;  
    }
  
    public void setSalary(int salary) {  
        this.salary = salary;  
    }

    public String toString() {
        return(" Name: " + name+ "\n ID: " +id +"\n Experience: " +experience+ "\n Salary:" + salary);
    }
}