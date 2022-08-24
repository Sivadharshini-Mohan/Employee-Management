import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class EmployeeDao extends BaseDao {
    BaseDao baseDao = new BaseDao(); 
    /**
     * <p>
     * Insert the employee into the list
     * </p>
     * @param employee
     * 
     */
    public Employee insertEmployee(Employee employee) {
        try {
            Connection connection = baseDao.mysqlConnection();
            String query = " insert into employee(name, email_id, dob, gender, mobile_number, experience, batch)" + " values (?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, employee.getName());
            preparedStatement.setString (2, employee.getId());
            preparedStatement.setString (3, employee.getDob());
            preparedStatement.setString (4, employee.getGender());
            preparedStatement.setLong (5, employee.getMobileNumber());
            preparedStatement.setInt (6, employee.getExperience());
            preparedStatement.setInt (7, employee.getBatch());
            //preparedStatement.setString (8,null);
            preparedStatement.execute();
            System.out.println("Table inserted");
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return employee;
    }   
}

