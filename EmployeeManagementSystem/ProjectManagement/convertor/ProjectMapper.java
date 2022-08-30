public class ProjectMapper {   
    public Project fromProjectDto(ProjectDto projectDto) {
         Project project = new Project(projectDto.getName(), projectDto.getClientName(), 
                                       projectDto.getCompanyName(), projectDto.getStartDate().toString(), projectDto.getProjectStatus()); 

        return project;
    }

    public ProjectDto toProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto(project.getName(), project.getClientName(), 
                                               project.getCompanyName(), project.getStartDate().toString(), project.getProjectStatus()); 
        
        return projectDto;
    }
}