import java.util.List;
import java.util.ArrayList;

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

    public List<ProjectDto> getprojects() throws CustomException  {
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
 
    
}