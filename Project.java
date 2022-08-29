import java.time.LocalDate;

public class Project {
    int id;
    String name;
    String clientName;
    String companyName;
    LocalDate startDate;
    String projectStatus; 

    public Project(int id, String name, String clientName, String startDate, String projectStatus) {
        this.id = id;
        this.name = name;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startDate = startDate;
        this.projectStatus = projectStatus;
    } 

    public int getId() {
        return id;
    } 
    
    public void setId() {
        this.id = id;
    }

    public String getName() {  
        return name;  
    }

    public void setName(String name) {  
        this.name = name;  
    }

    public String getCompanyName() {  
        return companyName;  
    }

    public void setCompanyName(String companyName) {  
        this.companyName = companyName;  
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
    
    public String getProjectStatus() {
        return projectStatus;
    }
    
    public String toString() {
        return "Id: " + id +" \n Project Name: " + name +  "\n Company Name: " + companyName + "\n Starting Date:" + startDate +
             "\n Project Status:" + projectStatus ;
    }   
}