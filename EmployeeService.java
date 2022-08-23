import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Implementation of get the data from Controller did business logic and  
 * transfer to Data access object
 * </p>
 */
public class EmployeeService {    
    private EmployeeDao employeeDao = new EmployeeDao();
    private Mapper mapper = new Mapper();
    /**  
     * <p>  
     * Get the Employee input from controller and transfer to employee Database acess object  
     * </p>
     *
     * @param employeeDto
     * 
     */
    public void addEmployee(EmployeeDto employeeDto) {		
        Employee employee = mapper.employeeDtoToEmployee(employeeDto);
        employeeDao.insertEmployee(employee);
    }
}