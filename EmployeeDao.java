import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

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
            String query = " insert into  employee (name, email_id, dob, gender, mobile_number, date_of_joining, batch) values (?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmailId());
            preparedStatement.setString(3, employee.getDob());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setLong(5, employee.getMobileNumber());
            preparedStatement.setString(6, employee.getDoj());
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
    
    public List<Employee> retriveTrainer() throws CustomException {
            List<Employee> employees = new ArrayList<Employee>(); 
        try {
            String query = "select employee.*, employee_role.role_id from employee_role inner join employee on "
                + "employee.id = employee_role.employee_id;" ;
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
}

