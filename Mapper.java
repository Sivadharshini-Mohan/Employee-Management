public class Mapper {

 public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getName(), employeeDto.getId(), 
            employeeDto.getDob(), employeeDto.getGender(), employeeDto.getMobileNumber(), 
            employeeDto.getExperience(), employeeDto.getBatch(), employeeDto.getRole());
        return employee;
    }

    public Employee employeeToEmployeeDto(Employee employee) {
        Employee employeeDto = new EmployeeDto(employee.getName(), employee.getId(), 
            employee.getDob(), employee.getGender(), employee.getMobileNumber(), 
            employee.getExperience(), employee.getBatch(), employee.getRole());
        return employee;
    }
}