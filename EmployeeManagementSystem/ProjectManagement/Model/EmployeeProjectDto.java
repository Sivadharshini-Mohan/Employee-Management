import java.time.LocalDate;

public class EmployeeProjectDto {
    int employeeId;
    int projectId;
    String projectName;
    LocalDate startDate;
    LocalDate relievedDate;
    String activeStatus;
    
    public EmployeeProjectDto(int employeeId, int projectId, String projectName, String startDate, String relievedDate, String activeStatus) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = LocalDate.parse(startDate); 
        this.relievedDate = LocalDate.parse(relievedDate);
        this.activeStatus = activeStatus;
    }

    public int getEmployeeId() {
        return employeeId;
    } 
    
    public void setEmployeeId() {
        this.employeeId = employeeId;
    }
    
    public int getProjectId() {
        return projectId;
    } 
    
    public String getProjectName() {
        return projectName;
    } 
    
    public void setProjectName() {
        this.projectName = projectName;
    }

    public void setProjectId() {
        this.projectId = projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    } 
    
    public void setStartDate() {
        this.startDate = startDate;
    }

    public LocalDate getRelievedDate() {
        return relievedDate;
    } 
    
    public void setRelievedDate() {
        this.relievedDate = relievedDate;
    }

    public String getActiveStatus() {
        return activeStatus;
    } 
    
    public void setActiveStatus() {
        this.activeStatus = activeStatus;
    }

    public String toString() {
        return  "Employee Id : " + employeeId + "\n Project Id : " + projectId + "\n Project Name : " + projectName + "\n Start Date : " + startDate + "\n Relieved Date:" + relievedDate +
             "\n Active Status:" + activeStatus ;
    }
}