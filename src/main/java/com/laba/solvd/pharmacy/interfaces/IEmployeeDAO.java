package com.laba.solvd.pharmacy.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO<Employee> extends IBaseDAO<Employee> {

    List<Employee> getEmployeeByLastName(String lastName);
}
