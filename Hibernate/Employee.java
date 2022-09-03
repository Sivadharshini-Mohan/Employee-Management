import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee { 
    int employeeId; 
    String name;  
    String email;
    LocalDate dob;
    String gender;
    long mobileNumber;
    LocalDate doj;
    int batch;
    
    public Employee(String name, String email, String dob, String gender, long mobileNumber, String doj, int batch ) {
        this.name = name;
        this.email = email;
        this.dob = LocalDate.parse(dob);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.doj = LocalDate.parse(doj);
        this.batch = batch;
    }
    
    public Employee(int employeeId, String name, String email, String dob, String gender, long mobileNumber, String doj, int batch ) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.dob = LocalDate.parse(dob);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.doj = LocalDate.parse(doj);
        this.batch = batch;
    }
    
    public Employee() {
    }
        
    public int getEmployeeId() {
        return employeeId;
    } 
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {  
        return name;  
    }

    public void setName(String name) {  
        this.name = name;  
    }

    public String getEmail() {  
        return email;  
    }

    public void setEmail(String email) {  
        this.email = email;  
    }
    
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public LocalDate getDob() {
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

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }
    
    public LocalDate getDoj() {
        return doj;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }
    
    public String toString() {
        return  "Employee Id : " + employeeId + "\n Name: " + name +  "\n Email Id: " + email + "\n Date of birth:" + dob +
             "\n Gender:" + gender + "\n Mobile Number:" + mobileNumber + "\n Date of joining:" + doj + "\n Batch:" + batch ;
    }
}


    
     
  