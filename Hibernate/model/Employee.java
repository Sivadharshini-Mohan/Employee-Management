import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.CascadeType;
import javax.persistence.Table; 
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
 
@Entity
@Table(name = "employee")
public class Employee {
    @Id 
    @GeneratedValue
    @Column(name="id")
    private int id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "email_id")
    private String email;


    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "date_of_joning")
    private LocalDate dateOfJoining;

    @Column(name = "batch")
    private int batch;

    @Column(name = "role")
    private String role;

    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.ALL})
    @JoinTable(
        name = "employee_role", 
        joinColumns = { @JoinColumn(name = "employee_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id")}
    )
    private List<Role> roles = new ArrayList<Role>();
     
    public Employee() {
    }
    
    public Employee(String name, String email, String dateOfBirth, String gender, long mobileNumber, String dateOfJoining, int batch, String role ) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfJoining = LocalDate.parse(dateOfJoining);
        this.batch = batch;
        this.role = role;
    }
    
    public Employee(int id, String name, String email, String dateOfBirth, String gender, long mobileNumber, String dateOfJoining, int batch,String role ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfJoining = LocalDate.parse(dateOfJoining);
        this.batch = batch;
        this.role = role;
    }

    public int getid() {
        return id;
    } 
    
    public void setid(int id) {
        this.id = id;
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
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public LocalDate getDateOfBirth() {
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

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }
    
    public List<Role> getRole() {
        return roles;
    }
     
    public void setRole(List<Role> roles) {
        this.roles = roles;
    }
    
    public void setEmployeeRole(String role) {
        this.role = role;
    }
    
    public String getEmployeeRole() {
        return role;
    }
    
}