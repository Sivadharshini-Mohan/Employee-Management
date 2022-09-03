
public class EmployeeMapper {   
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
         Employee employee = new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getEmailId(), 
            employeeDto.getDob().toString(), employeeDto.getGender(), employeeDto.getMobileNumber(), 
            employeeDto.getDoj().toString(), employeeDto.getBatch());
        return employee;
    }

    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getEmployeeId(), employee.getName(), employee.getEmail(), 
            employee.getDob().toString(), employee.getGender(), employee.getMobileNumber(), 
            employee.getDoj().toString(), employee.getBatch());
        return employeeDto;
    }
}