package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO extends IBaseDAO<Employee> {

    List<Employee> getEmployeeByLastName(String lastName) throws SQLException;
}
