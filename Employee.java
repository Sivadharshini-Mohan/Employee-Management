public class Employee {  
    String name;  
    String email;
    String dob;
    String gender;
    long mobileNumber;
    int experience;
    int batch;
    String role;
    

    Employee(String name, String email, String dob,String gender,long mobileNumber,int experience,int batch,String role) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.experience = experience;
        this.batch = batch;
        this.role = role;
    }
        
    public String getName() {  
        return name;  
    }

    public void setName(String name) {  
        this.name = name;  
    }

    public String getId() {  
        return email;  
    }

    public void setId(String email) {  
        this.email = email;  
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getDob() {
        return dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getGender() {
        return gender;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    public  int getExperience() {
        return experience;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
}


    
     
  