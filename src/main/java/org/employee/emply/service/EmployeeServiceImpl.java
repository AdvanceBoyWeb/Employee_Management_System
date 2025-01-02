package org.employee.emply.service;

import java.util.ArrayList;
import java.util.List;

import org.employee.emply.entity.EmployeeEntity;
import org.employee.emply.model.Employee;
import org.employee.emply.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String creatEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);

        employeeRepository.save(employeeEntity);
        return "Saved successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);

        return employee;
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : employeesList) {
            Employee emp = new Employee();

            emp.setId(employeeEntity.getId());
            emp.setName(employeeEntity.getName());
            emp.setPhone(employeeEntity.getPhone());
            emp.setEmail(employeeEntity.getEmail());
            employees.add(emp);
        }

        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {

        EmployeeEntity emp = employeeRepository.findById(id).get();
        employeeRepository.delete(emp);
        return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {

        EmployeeEntity exestingEmployee = employeeRepository.findById(id).get();

        exestingEmployee.setName(employee.getName());
        exestingEmployee.setPhone(employee.getPhone());
        exestingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(exestingEmployee);

        return "Update Successfully";
    }

}