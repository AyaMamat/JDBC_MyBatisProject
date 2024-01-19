package com.laba.solvd.pharmacy.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO<Customer> extends IBaseDAO<Customer> {

    List<Customer> getCustomersByLastName(String lastName) throws SQLException;
}
