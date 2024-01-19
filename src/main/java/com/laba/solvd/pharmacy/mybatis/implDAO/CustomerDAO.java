package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.ICustomerDAO;
import com.laba.solvd.pharmacy.model.Customer;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CustomerDAO implements ICustomerDAO<Customer> {
    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static ICustomerDAO myBatisDAO;

    public CustomerDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Customer customer) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ICustomerDAO.class);
            myBatisDAO.saveEntity(customer);
            session.commit();
            LOGGER.info("Customer created in SQLSession");
        }
    }

    @Override
    public Customer getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ICustomerDAO.class);
            LOGGER.info("Read customer in SqlSession");
            return (Customer) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Customer customer) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ICustomerDAO.class);
            myBatisDAO.updateEntity(customer);
            session.commit();
            LOGGER.info("Customer updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ICustomerDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Customer deleted in SqlSession");
        }
    }

    @Override
    public List<Customer> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ICustomerDAO.class);
            LOGGER.info("Get all customers in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ICustomerDAO.class);
            LOGGER.info("Get customers by last name in SqlSession");
            return myBatisDAO.getCustomersByLastName(lastName);
        }
    }
}
