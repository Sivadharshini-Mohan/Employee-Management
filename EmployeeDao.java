import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

public class EmployeeDao extends BaseDao {
    private Connection connection = mysqlConnection();

    /**
     * <p>
     * Insert the employee into the list
     * </p>
     * @param employee
     * 
     */
    public int insertEmployee(Employee employee) throws CustomException {

        try {
            int employeeId = 0 ;
            String query = " insert into  employee (name, email_id, dob, gender, mobile_number, date_of_joining, batch,active_status) "
                + " values (?, ?, ?, ?, ?, ?, ?, 1 )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmailId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(employee.getDob()));
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setLong(5, employee.getMobileNumber());
            preparedStatement.setDate(6, java.sql.Date.valueOf(employee.getDoj()));
            preparedStatement.setInt(7, employee.getBatch());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("select last_insert_id() from employee");
            ResultSet resultset = preparedStatement.executeQuery();
            resultset.next();
            employeeId = Integer.valueOf(resultset.getString("last_insert_id()"));
            return employeeId;
        } catch(Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
    
    public List<Employee> retriveEmployee(int employeeRole) throws CustomException {
            List<Employee> employees = new ArrayList<Employee>(); 
        try {
            String query = "select employee.* from employee_role inner join employee on employee.id = employee_role.employee_id "
                + " where employee.active_status = 1 and employee_role.role_id =" +   employeeRole;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String emailId = rs.getString("email_id");
                String dob = rs.getString("dob");
                String gender = rs.getString("gender");
                long mobileNumber = rs.getLong("mobile_number");
                String doj = rs.getString("date_of_joining");
                int batch = rs.getInt("batch");
                Employee employee = new Employee(name, emailId, dob, gender, mobileNumber, doj, batch);
                employees.add(employee);
            }
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
        return employees;
    }    

    public boolean updateEmployee(Employee employee, String email) throws CustomException {
        try{
            String query = "update employee set name = ? , email_id = ?, dob = ?, gender = ?, mobile_number = ?, date_of_joining = ?," 
                 + "batch = ?, active_status = 1 where email_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmailId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(employee.getDob()));
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setLong(5, employee.getMobileNumber());
            preparedStatement.setDate(6, java.sql.Date.valueOf(employee.getDoj()));
            preparedStatement.setInt(7, employee.getBatch());
            preparedStatement.setString(8, email);
            return preparedStatement.execute();
            
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
    
    public void deleteEmployee(String email) throws CustomException {
       try{
            String query = "update employee set active_status = 0 where email_id = " + email; 
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
       } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }

}

