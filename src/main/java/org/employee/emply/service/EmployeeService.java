package org.employee.emply.service;

import java.util.List;

import org.employee.emply.model.Employee;

public interface EmployeeService {
    String creatEmployee(Employee employee);

    List<Employee> readEmployees();

    boolean deleteEmployee(Long id);

    String updateEmployee(Long id, Employee employee);

    Employee readEmployee(Long id);
}
