import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * It holds all bussiness logics and get values 
 * from DAO, convert the model as DTO and pass it to Controller class
 * </p>
 * @author Sivadharshini Mohan
 * @version 1.0
 **/
public class EmployeeService {          
    private EmployeeMapper employeeMapper = new EmployeeMapper();   
    private EmployeeDao employeeDao = new EmployeeDao();
    private EmployeeDto employeedto = new EmployeeDto();
    private RoleDao roleDao = new RoleDao();

    /**  
     * <p>  
     * Get the Employee input from controller and transfer to employee Database acess object  
     * </p>
     *
     * @param employeeDto 
     * @param employeeRole Trainer/Trainee
     * 
     * @return {@link String}
     */
    public String addEmployeeByRole(EmployeeDto employeeDto, String employeeRole) throws CustomException {
        Role role = roleDao.retrieveRoleByName(employeeRole);
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        //employee.setRoles(role);
        int employeeId = employeeDao.insertEmployee(employee);
        return "Employee detail create sucessfully";    
    }

   public List<EmployeeDto> getEmployees() throws CustomException  {
        List<EmployeeDto> employeeDtoList = null;
        for(Employee employee: employeeDao.retriveEmployees()) {          
             employeeDtoList.add(employeeMapper.employeeToEmployeeDto(employee));
        }
        return employeeDtoList; 
    }

    public EmployeeDto getEmployeeById(int id) throws CustomException {
        Employee employee = employeeDao.retrieveEmployeeById(id);
        EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
        return employeeDto;
    }

    /**
     * <p>
     * Update the employee detail
     * </p> 
     *  
     * @param employeeDto
     * @param email
     * @return {@link String} 
     */
    public String updateEmployee(EmployeeDto employeeDto, int employeeId, String role) throws CustomException {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        Employee updateEmployee = employeeDao.retrieveEmployeeById(employeeId);
        List<Role> roles = updateEmployee.getRoles();
        Role newRole = roleDao.retrieveRoleByName(role);
        roles.add(newRole);
        employee.setRoles(roles);
        employee.setId(employeeId);
        employeeDao.updateEmployeeById(employee);
        return "Employee detail updated sucessfully";
    } 
    public String deleteEmployeeById(int id) throws CustomException {
        List<Role> roles = new ArrayList<Role>();
        Employee employee = employeeDao.retrieveEmployeeById(id);
        employee.setStatus("inactive");
        employee.setRoles(roles);
        employeeDao.deleteEmployee(employee);
        return "Employee Detail deleted successfully";
    }
}