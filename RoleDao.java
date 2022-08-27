import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends BaseDao {
    private Connection connection = mysqlConnection();
    
    public int retriveRoleIdByName(String name) throws CustomException {
        try {
            int roleId = 0;
            String query = "select id from role where name = '" + name + "'" ;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery(); 
            while (rs.next()) {
                roleId = rs.getInt("id");
                System.out.println(roleId);
            } 
            return roleId;
        } catch(Exception exception) {
            throw new CustomException(exception.getMessage());
        }  
    }

    public boolean assignEmployeeRole(int employeeId, int roleId) throws CustomException {
        try {
            String query = " insert into  employee_role (employee_id, role_id) values (?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, employeeId);
            preparedStatement.setInt (2, roleId);
            return preparedStatement.execute();
        } catch(Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
}