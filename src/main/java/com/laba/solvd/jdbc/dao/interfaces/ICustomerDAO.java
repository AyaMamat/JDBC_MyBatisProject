package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO extends IBaseDAO<Customer>{

    List<Customer> getCustomersByLastName(String lastName) throws SQLException;
}
