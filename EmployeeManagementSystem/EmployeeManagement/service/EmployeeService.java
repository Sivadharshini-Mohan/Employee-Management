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
    private EmployeeDao employeeDao = new EmployeeDao();
    private RoleDao roleDao = new RoleDao();
    private EmployeeMapper employeeMapper = new EmployeeMapper();

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
    public String addEmployeeByRole(EmployeeDto employeeDto, String role) throws CustomException {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        int employeeId = employeeDao.insertEmployee(employee);
        int roleId = roleDao.retriveRoleIdByName(role);
        roleDao.assignEmployeeRole(employeeId, roleId);
        return "Employee detail create sucessfully";    
    }
    
    /**
     * <p>
     * Get the all employee detail
     * </p> 
     *  
     * @param employeeRole Trainer/Trainee
     * @return {@link List} return Object
     */
    public List<EmployeeDto> getEmployeesByRole(int employeeRole) throws CustomException  {
        List<Employee> employees =  employeeDao.retriveEmployeeByRole(employeeRole);
        List<EmployeeDto> employeeDtoList =  new ArrayList<EmployeeDto>();
        for(Employee employee : employees) {
            EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList; 
    }
    
    /**
     * <p>
     * Get the particular employee detail
     * </p> 
     *  
     * @param employeeId 
     * @return {@link List} return Object
     */
    public List<EmployeeDto> getEmployeeById(int employeeId) throws CustomException  {
        List<Employee> employees =  employeeDao.retriveEmployeeById(employeeId);
        List<EmployeeDto> employeeDtoList =  new ArrayList<EmployeeDto>();
        for(Employee employee : employees) {
            EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
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
    public String updateEmployee(EmployeeDto employeeDto, int id) throws CustomException {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        employeeDao.updateEmployeeById(employee, id);
        return "Employee detail updated sucessfully";
    } 
    
    /**
     * <p>
     * Delete the employee detail 
     * </p> 
     *  
     * @param email
     * @return {@link String} 
     */
    public String deleteEmployee(int id) throws CustomException {
        employeeDao.deleteEmployeeById(id);
        return "Employee deleted sucessfully";
    }
}