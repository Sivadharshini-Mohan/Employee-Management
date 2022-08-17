package com.i2i.project.model;

public class Employee {  
    protected String name;  
    protected String id;
    protected String dob;

    Employee(String name, String id, String dob) {
        this.name = name;
        this.id = id;
        this.dob = dob;
    }
        
    public String getName() {  
        return name;  
    }

    public void setName(String name) {  
        this.name = name;  
    }

    public String getId() {  
        return id;  
    }

    public void setId(String id) {  
        this.id = id;  
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getDob() {
        return dob;
    }
}


    
     
  