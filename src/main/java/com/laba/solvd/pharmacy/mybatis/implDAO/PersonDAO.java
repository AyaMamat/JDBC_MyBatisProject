package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IPersonDAO;
import com.laba.solvd.pharmacy.model.Person;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class PersonDAO implements IPersonDAO<Person> {

    private static final Logger LOGGER = LogManager.getLogger(PersonDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static IPersonDAO myBatisDAO;

    public PersonDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Person person) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPersonDAO.class);
            myBatisDAO.saveEntity(person);
            session.commit();
            LOGGER.info("Person created in SQLSession");
        }
    }

    @Override
    public Person getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPersonDAO.class);
            LOGGER.info("Read person in SqlSession");
            return (Person) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Person person) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO=session.getMapper(IPersonDAO.class);
            myBatisDAO.updateEntity(person);
            session.commit();
            LOGGER.info("Person updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO=session.getMapper(IPersonDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Person deleted in SqlSession");
        }
    }

    @Override
    public List<Person> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO=session.getMapper(IPersonDAO.class);
            LOGGER.info("Get all persons in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Person> getPersonsByBirthDate(LocalDate birthDate) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO=session.getMapper(IPersonDAO.class);
            LOGGER.info("Get persons by birth date in SqlSession");
            return myBatisDAO.getPersonsByBirthDate(birthDate);
        }
    }
}