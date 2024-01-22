package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.EmployeeDAO;
import com.laba.solvd.pharmacy.interfaces.IEmployeeDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeDAO<Employee> {

    private static final EmployeeDAO employeeDAO = (EmployeeDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("employees");

    @Override
    public void saveEntity(Employee employee) {
        employeeDAO.saveEntity(employee);
    }

    @Override
    public Employee getEntityByID(int id) {
        return employeeDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Employee employee) {
        employeeDAO.updateEntity(employee);
    }

    @Override
    public void removeEntityById(int id) {
        employeeDAO.removeEntityById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public List<Employee> getEmployeeByLastName(String lastName) {
        return employeeDAO.getEmployeeByLastName(lastName);
    }
}