package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.jdbc.model.Employee;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class EmployeeMapper implements IEmployeeDAO {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeMapper.class.getName());
    private static final MyBatisSQLFactory MY_BATIS = MyBatisSQLFactory.getInstance();

    @Override
    public void saveEntity(Employee employee) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.insert("saveEntity(", employee);
            session.commit();
            LOGGER.info("Employee created in SQLSession");
        }
    }

    @Override
    public Employee getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Read employee in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Employee employee) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.update("updateEntity", employee);
            session.commit();
            LOGGER.info("Employee updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Employee deleted in SqlSession");
        }
    }

    @Override
    public List<Employee> getAll() {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get all employees in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Employee> getEmployeeByLastName(String lastName) throws SQLException {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get employees by last name in SqlSession");
            return session.selectList("getEmployeeByLastName", lastName);
        }
    }
}