package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.CustomerDAO;
import com.laba.solvd.pharmacy.interfaces.ICustomerDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements ICustomerDAO<Customer> {

    private static final CustomerDAO customerDAO = (CustomerDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("customers");

    @Override
    public void saveEntity(Customer customer) {
        customerDAO.saveEntity(customer);
    }

    @Override
    public Customer getEntityByID(int id) {
        return customerDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Customer customer) {
        customerDAO.updateEntity(customer);
    }

    @Override
    public void removeEntityById(int id) {
        customerDAO.removeEntityById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) throws SQLException {
        return customerDAO.getCustomersByLastName(lastName);
    }
}