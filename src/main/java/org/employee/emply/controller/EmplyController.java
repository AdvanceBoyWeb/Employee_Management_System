package org.employee.emply.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.employee.emply.model.Employee;
import org.employee.emply.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin("http://localhost:5150/")
public class EmplyController {

    // Dependency Injection.
    @Autowired
    EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return employeeService.readEmployees();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.readEmployee(id);
    }

    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee) {
        return employeeService.creatEmployee(employee);
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id))
            return "Delete Successfully";
        return "Not found";
    }

    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

}
