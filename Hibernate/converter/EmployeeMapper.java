package com.i2i.annotation.converter;

import com.i2i.annotation.dto.EmployeeDto;
import com.i2i.annotation.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * It helps to convert the model data to dto vice versa
 * </p>
 **/
public class EmployeeMapper {
    
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmailId(), 
                                         employeeDto.getDateOfBirth().toString(), employeeDto.getGender(), employeeDto.getMobileNumber(), 
                                         employeeDto.getDateOfJoining().toString(), employeeDto.getBatch(), employeeDto.getStatus());

        return employee; 
     }

    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getName(), employee.getEmail(), 
                                                  employee.getDateOfBirth().toString(), employee.getGender(), employee.getMobileNumber(), 
                                                  employee.getDateOfJoining().toString(), employee.getBatch(), employee.getStatus());

        return employeeDto;
    }
}

