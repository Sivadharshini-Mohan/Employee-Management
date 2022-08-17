package com.i2i.project.model;

public class Lead {
    protected String name;
     
    public Lead(String name) {
        this.name = name;
    }

    public String getid() {
	return name;
    }
   
    public String toString() {
        return  "\nTrainer Name      : " + name;
    }
}