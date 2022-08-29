public class ProjectService {
    EmployeeMapper employeeMapper = new EmployeeMapper();
    ProjectDao projectDao = new ProjectDao();

    public String addEmployeeByRole(ProjectDto projectDto) throws CustomException {
        Project project = employeeMapper.projectDtoToProject(projectDto);
        projectDao.insertProject(project);
        return "Project detail create sucessfully";    
    }
}