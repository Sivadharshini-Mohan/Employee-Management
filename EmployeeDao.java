import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

/**
 * <p>
 * It will communicate with Employee service and
 * it store the data to database
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 */
public class EmployeeDao extends BaseDao {
    private Connection connection = mysqlConnection();
    private PreparedStatement preparedStatement;
    
    /**
     * <p>
     * Insert the employee data in to database
     * </p> 
     * @param Employee object
     * @throws CustomException
     * @return {@link int}
     *
     */
    public int insertEmployee(Employee employee) throws CustomException {

        try {
            int employeeId = 0 ;
            String query = " insert into  employee (name, email_id, dob, gender, mobile_number, date_of_joining, batch,active_status) "
                + " values (?, ?, ?, ?, ?, ?, ?, 1 )";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement(employee);
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
    
    /**
     * <p>
     * Retrive the all employee data from database
     * </p> 
     * @param employeRole
     * @throws CustomException
     * @return {@link List} 
     *
     */
    public List<Employee> retriveEmployeeByRole(int employeeRole) throws CustomException {
            List<Employee> employees = new ArrayList<Employee>(); 
        try {
            String query = " select employee.*,employee_role.role_id from employee_role inner join employee on employee.id = employee_role.employee_id where employee.active_status = 1 "
                + " and employee_role.role_id = " + employeeRole ;
             System.out.println(employeeRole);
            preparedStatement = connection.prepareStatement(query);
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

    /**
     * <p>
     * Retrive the particular employee data from database
     * </p> 
     * @param employeId
     * @throws CustomerException
     * @return {@link List} 
     *
     **/
    public List<Employee> retriveEmployeeById(int employeeId) throws CustomException {
            List<Employee> employees = new ArrayList<Employee>(); 
        try {
            String query = "select employee.*  from employee where id = " + employeeId;
            preparedStatement = connection.prepareStatement(query);
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

    /**
     * <p>
     * Update the employee data to database
     * </p>
     * @param employee 
     * @param email 
     * @throws CustomerException
     * @return {@link boolean} return true or false 
     *
     */
    public boolean updateEmployeeById(Employee employee, int id) throws CustomException {
        try{
            String query = "update employee set name = ? , email_id = ?, dob = ?, gender = ?, mobile_number = ?, date_of_joining = ?," 
                 + "batch = ?, active_status = 1 where id = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement(employee);
            preparedStatement.setInt(8, id);
            return preparedStatement.execute();
            
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }
    
    /**
     * <p>
     * Delete the employee data 
     * </p> 
     * @param email
     * @throws CustomException
     * @return {@link void} return nothing 
     *
     **/
    public void deleteEmployeeById(int id) throws CustomException {
       try{
            String query = "update employee set active_status = 0 where id = " + id; 
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
       } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }

    public void preparedStatement(Employee employee) throws CustomException  {
        try {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmailId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(employee.getDob()));
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setLong(5, employee.getMobileNumber());
            preparedStatement.setDate(6, java.sql.Date.valueOf(employee.getDoj()));
            preparedStatement.setInt(7, employee.getBatch());
        } catch (Exception exception) {
            throw new CustomException(exception.getMessage());
        }
    }

}

