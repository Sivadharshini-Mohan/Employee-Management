import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class RoleDao extends BaseDao {
    BaseDao baseDao = new BaseDao();
    public void role(String employeeRole) {
        try {
        Connection connection = baseDao.mysqlConnection();
        String query = "select * from employee where name = employeeRole";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}