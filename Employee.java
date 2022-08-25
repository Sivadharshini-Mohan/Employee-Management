public class Employee {  
    String name;  
    String email;
    String dob;
    String gender;
    long mobileNumber;
    String doj;
    int batch;
    
    Employee(String name, String email, String dob,String gender,long mobileNumber,String doj,int batch) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.doj = doj;
        this.batch = batch;
    }
        
    public String getName() {  
        return name;  
    }

    public void setName(String name) {  
        this.name = name;  
    }

    public String getEmailId() {  
        return email;  
    }

    public void setEmailId(String email) {  
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

    public void setDoj(String doj) {
        this.doj = doj;
    }
    
    public  String getDoj() {
        return doj;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }
    
    public String toString() {
        return "Name: " + name +  "\n Email Id: " + email + "\n Date of birth:" + dob +
             "\n Gender:" + gender + "\n Mobile Number:" + mobileNumber + "\n Date od joining:" + doj + "\n Batch:" + batch ;

    }

}


    
     
  