package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer create(Customer customer);
    List<Customer> getAll();
}
