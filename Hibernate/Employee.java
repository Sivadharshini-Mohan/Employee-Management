import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    int id; 
    String name;  
    int batch;
    
    public Employee(String name, int batch ) {
        this.name = name;
        this.batch = batch;
    }
    
    public Employee() {
        // default constructor
    }
    public int getId() {  
        return id;  
    }

    public void setId(int id) {  
        this.id = id;  
    }
    public String getName() {  
        return name;  
    }

    public void setName(String name) {  
        this.name = name;  
    }

    
    public void setBatch(int batch) {
        this.batch = batch;
    }
    
    public int getBatch() {
        return batch;
    }
    
    
}