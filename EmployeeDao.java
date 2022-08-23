import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
impoer java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDao extends BaseDao {
    static List<Employee> employees = new LinkedList<Employee>();
    
    
    /**
     * <p>
     * Insert the employee into the list
     * </p>
     * @param employee
     * 
     */
    public void insertEmployee(Employee employee) {
        try {
            
            String query = " insert into employee(name, email_id, dob, gender, mobile_number, experience, batch,"
                +"role)" + " values (?, ?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, employee.getName());
            preparedStatement.setString (2, employee.getId());
            preparedStatement.setString (3, employee.getDob());
            preparedStatement.setString (4, employee.getGender());
            preparedStatement.setLong (5, employee.getMobileNumber());
            preparedStatement.setInt (6, employee.getExperience());
            preparedStatement.setInt (7, employee.getBatch());
            preparedStatement.setString (8, employee.getRole());
            preparedStatement.execute();
            System.out.println("Table inserted");
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }   
}
