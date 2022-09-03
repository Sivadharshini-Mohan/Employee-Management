import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ProjectService {
    ProjectMapper projectMapper = new ProjectMapper();
    ProjectDao projectDao = new ProjectDao();

    public String addProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromProjectDto(projectDto);
        projectDao.insertProject(project);
        return "Project detail create sucessfully";    
    }
    
    public String updateProject(int id, ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromProjectDto(projectDto);
        projectDao.updateProjectById(id, project);
        return "Project detail updtaed sucessfully";    
    }

    public List<ProjectDto> getprojects() throws CustomException {
        List<Project> projects =  projectDao.retriveProject();
        List<ProjectDto> projectDtoList =  new ArrayList<ProjectDto>();
        for(Project project : projects) {
            ProjectDto projectDto = projectMapper.toProjectDto(project);
            projectDtoList.add(projectDto);
        }
        return projectDtoList; 
    }

     public String deleteProject(int id) throws CustomException {
        projectDao.deleteProjectById(id);
        return "Project deleted sucessfully";
    }
  
    public String assingProjectToEmployees(int id, int employee, LocalDate startDate, LocalDate relieveDate) throws CustomException {
        projectDao.assingemployees(id, employee, startDate, relieveDate);
        return "Assigned employees for project" +id ;
    }
    
    public List<EmployeeProjectDto> getEmployeeProjects() throws CustomException {
        List<EmployeeProject> employeeProjects =  projectDao.retriveEmployeeProject();
        List<EmployeeProjectDto> employeeProjectDtoList =  new ArrayList<EmployeeProjectDto>();
        for(EmployeeProject employeeProject : employeeProjects) {
            EmployeeProjectDto employeeProjectDto = projectMapper.toEmployeeProjectDto(employeeProject);
            employeeProjectDtoList.add(employeeProjectDto);
        }
        return employeeProjectDtoList; 
    }

    public String deleteEmployeeProject(int projectId, int employeeId) throws CustomException {
        projectDao.deleteEmployeeProjectByIds(projectId, employeeId);
        return "Employee sucessfully removed from project ";
    }
    
}