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
            int employeeId = 0 ;
            String query = " insert into  project (name, client_name, company_name, start_date, project_status, created_date) "
                + " values (?, ?, ?, ?, ?, current_timestamp)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement(project);
            preparedStatement.execute();            
        } catch(Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
}