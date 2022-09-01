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

    public EmployeeProject fromEmployeeProjectDto(EmployeeProjectDto employeeProjectDto) {
         EmployeeProject employeeProject = new EmployeeProject(employeeProjectDto.getEmployeeId(), employeeProjectDto.getProjectId(), 
                                                               employeeProjectDto.getProjectName(), employeeProjectDto.getStartDate().toString(),
                                                               employeeProjectDto.getRelievedDate().toString(), 
                                                               employeeProjectDto.getActiveStatus()); 

        return employeeProject;
    }
    
    public EmployeeProjectDto toEmployeeProjectDto(EmployeeProject employeeProject) {
         EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto(employeeProject.getEmployeeId(), employeeProject.getProjectId(),
                                                                        employeeProject.getProjectName(), employeeProject.getStartDate().toString(),
                                                                        employeeProject.getRelievedDate().toString(), 
                                                                        employeeProject.getActiveStatus()); 

        return employeeProjectDto;
    }
}