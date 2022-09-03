import java.time.LocalDate;
public class ProjectDto {
    int id;
    String name;
    String clientName;
    String companyName;
    LocalDate startDate;
    String projectStatus; 
    
   public ProjectDto(String name, String clientName, String companyName, String startDate, String projectStatus) {
        this.name = name;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startDate = LocalDate.parse(startDate);
        this.projectStatus = projectStatus;
    } 
    
    public ProjectDto() {
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

    public String getClientName() {  
        return clientName;  
    }

    public void setClientName(String clientName) {  
        this.clientName = clientName;  
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
        return "\n Project Name: " + name +  "\n Company Name: " + companyName + "\n Starting Date:" + startDate +
             "\n Project Status:" + projectStatus ;
    }   
}