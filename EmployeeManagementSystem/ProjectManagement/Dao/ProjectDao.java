import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

public class ProjectDao extends BaseDao {
    private Connection connection = mysqlConnection();
    private PreparedStatement preparedStatement;
    
    public void insertProject(Project project) throws CustomException {

        try {
            String query = " insert into  project (name, client_name, company_name, start_date, project_status, created_date) "
                + " values (?, ?, ?, ?, ?, current_timestamp)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement(project);
            preparedStatement.execute();            
        } catch(Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }

    public boolean updateProjectById(int id, Project project) throws CustomException {
        try{
            String query = "update project set name = ? , client_name = ?, company_name = ?, start_dtae = ?, "
                           + "updated_date = current_timestamp where id = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement(project);
            preparedStatement.setInt(6, id);
            return preparedStatement.execute();
            
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
    
    public List<Project> retriveProject() throws CustomException {
            List<Project> projects = new ArrayList<Project>(); 
        try {
            String query = " select Project.*,employee_Project.project_id from employee_project inner join project on "
                            + "project.id = employee_project.project_id ";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String clientName = resultSet.getString("cilent_name");
                String companyName = resultSet.getString("company_name");
                String startDate = resultSet.getString("start_date");
                String projectStatus = resultSet.getString("project_status");
                Project project = new Project(name, clientName, companyName, startDate, projectStatus);
                projects.add(project);
            }
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
        return projects;
    } 

    public void deleteProjectById(int id) throws CustomException {
       try{
            String query = "update employee set active_status = 0 where id = " + id; 
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
       } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }   

     public void preparedStatement(Project project) throws CustomException  {
        try {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getClientName());
            preparedStatement.setString(3, project.getCompanyName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(project.getStartDate()));
            preparedStatement.setString(5, project.getProjectStatus());
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
}