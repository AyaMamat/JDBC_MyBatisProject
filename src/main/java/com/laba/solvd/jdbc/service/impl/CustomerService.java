package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.CustomerDAO;
import com.laba.solvd.jdbc.dao.implMapper.CustomerMapper;
import com.laba.solvd.jdbc.dao.interfaces.ICustomerDAO;
import com.laba.solvd.jdbc.model.Customer;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.service.interfaces.ICustomerService;
import com.laba.solvd.jdbc.service.interfaces.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class CustomerService implements ICustomerService {

    private static final Logger logger = LogManager.getLogger(CustomerService.class.getName());
    private final ICustomerDAO customerDAO;
    private final IPersonService personService;

    public CustomerService(IPersonService personService) {
        this.personService = personService;
        if (!connectionFactory.isMyBatis()) {
            this.customerDAO = new CustomerDAO();
            logger.info("Using JDBC Customer repository");
        } else {
            this.customerDAO = new CustomerMapper();
            logger.info("Using MyBatis Customer mapper");
        }
    }

    @Override
    public Customer create(Customer customer) {
        if(customer.getPerson()!=null){
            Person person=personService.create(customer.getPerson());
            customer.setPerson(person);
        }
        customerDAO.saveEntity(customer);
        return customer;

    }

    @Override
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }
}
