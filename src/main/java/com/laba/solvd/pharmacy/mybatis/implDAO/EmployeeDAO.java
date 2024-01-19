package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IEmployeeDAO;
import com.laba.solvd.pharmacy.model.Employee;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeDAO implements IEmployeeDAO<Employee> {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static IEmployeeDAO myBatisDAO;

    public EmployeeDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }


    @Override
    public void saveEntity(Employee employee) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IEmployeeDAO.class);
            myBatisDAO.saveEntity(employee);
            session.insert("saveEntity(", employee);
            session.commit();
            LOGGER.info("Employee created in SQLSession");
        }
    }

    @Override
    public Employee getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IEmployeeDAO.class);
            LOGGER.info("Read employee in SqlSession");
            return (Employee) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Employee employee) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IEmployeeDAO.class);
            myBatisDAO.updateEntity(employee);
            session.commit();
            LOGGER.info("Employee updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IEmployeeDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Employee deleted in SqlSession");
        }
    }

    @Override
    public List<Employee> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IEmployeeDAO.class);
            LOGGER.info("Get all employees in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Employee> getEmployeeByLastName(String lastName) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IEmployeeDAO.class);
            LOGGER.info("Get employees by last name in SqlSession");
            return myBatisDAO.getEmployeeByLastName(lastName);
        }
    }
}