package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee create(Employee employee);
    List<Employee> getAll();
}
