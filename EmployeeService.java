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
    private RoleDao roleDao = new RoleDao();
    private EmployeeMapper employeeMapper = new EmployeeMapper();

    /**  
     * <p>  
     * Get the Employee input from controller and transfer to employee Database acess object  
     * </p>
     *
     * @param employeeDto
     * 
     */
    public boolean addEmployee(EmployeeDto employeeDto, String employeeRole) throws CustomException {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        int employeeId = employeeDao.insertEmployee(employee);
        int roleId = roleDao.retriveRoleIdByName(employeeRole);
        return roleDao.assignEmployeeRole(employeeId, roleId);
            
    }
    
    public List<EmployeeDto> retriveEmployee(int employeeRole) throws CustomException  {
        List<Employee> employees =  employeeDao.retriveEmployee(employeeRole);
        List<EmployeeDto> employeeDtoList =  new ArrayList<EmployeeDto>();
        System.out.println(employees);
        for(Employee employee : employees) {
            System.out.println("wertyuiop@@@@");
            EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList; 
    }
    
    public void updateEmployee(EmployeeDto employeeDto, String email) throws CustomException {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        employeeDao.updateEmployee(employee, email);
    
    } 
    
    public void deleteEmployee(String email) throws CustomException {
        employeeDao.deleteEmployee(email);
    }
}