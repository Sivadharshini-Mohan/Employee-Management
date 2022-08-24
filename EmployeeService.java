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
     
    EmployeeDao employeeDao = new EmployeeDao();
    RoleDao roleDao = new RoleDao();
    Mapper mapper = new Mapper();
    /**  
     * <p>  
     * Get the Employee input from controller and transfer to employee Database acess object  
     * </p>
     *
     * @param employeeDto
     * 
     */
    public boolean addEmployee(EmployeeDto employeeDto) {	
        Employee employee = mapper.employeeDtoToEmployee(employeeDto);
        employeeDao.insertEmployee(employee);
        return true;
    }
    
    public void employeeRole(String employeeRole) {
        roleDao.role(employeeRole);
    }
}