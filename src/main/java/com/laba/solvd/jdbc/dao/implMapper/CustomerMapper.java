package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.ICustomerDAO;
import com.laba.solvd.jdbc.model.Customer;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CustomerMapper implements ICustomerDAO {

    private static final Logger logger = LogManager.getLogger(CustomerMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(Customer customer) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.insert("saveEntity", customer);
            session.commit();
            logger.info("Customer created in SQLSession");
        }
    }

    @Override
    public Customer getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read customer in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Customer customer) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.update("updateEntity", customer);
            session.commit();
            logger.info("Customer updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("Customer deleted in SqlSession");
        }
    }

    @Override
    public List<Customer> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all customers in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) throws SQLException {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get customers by last name in SqlSession");
            return session.selectList("getCustomersByLastName", lastName);
        }
    }
}
