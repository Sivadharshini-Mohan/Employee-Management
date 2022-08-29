import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee {  
    String name;  
    String email;
    LocalDate dateOfBirth;
    String gender;
    long mobileNumber;
    LocalDate dateOfJoin;
    int batch;
    
    public Employee(String name, String email, String dateOfBirth, String gender, long mobileNumber, String dateOfJoin, int batch) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfJoin = LocalDate.parse(dateOfJoin);
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
    
    public void setDob(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public LocalDate getDob() {
        return dateOfBirth;
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

    public void setDoj(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
    
    public LocalDate getDoj() {
        return dateOfJoin;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }
    
    public String toString() {
        return "Name: " + name +  "\n Email Id: " + email + "\n Date of birth:" + dateOfBirth +
             "\n Gender:" + gender + "\n Mobile Number:" + mobileNumber + "\n Date of joining:" + dateOfJoin + "\n Batch:" + batch ;
    }
}


    
     
  