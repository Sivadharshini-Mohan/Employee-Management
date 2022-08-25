
public class EmployeeMapper {

 public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmailId(), 
            employeeDto.getDob(), employeeDto.getGender(), employeeDto.getMobileNumber(), 
            employeeDto.getDoj(), employeeDto.getBatch());
        return employee;
    }

    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getName(), employee.getEmailId(), 
            employee.getDob(), employee.getGender(), employee.getMobileNumber(), 
            employee.getDoj(), employee.getBatch());
        return employeeDto;
    }
}