package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IPersonDAO;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PersonMapper implements IPersonDAO {
    private static final Logger logger = LogManager.getLogger(PersonMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(Person person) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.insert("saveEntity", person);
            session.commit();
            logger.info("Person created in SQLSession");
        }
    }

    @Override
    public Person getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read person in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Person person) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.update("updateEntity", person);
            session.commit();
            logger.info("Person updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("Person deleted in SqlSession");
        }
    }

    @Override
    public List<Person> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all persons in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Person> getPersonsByBirthDate(String birthDate) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get persons by birth date in SqlSession");
            return session.selectList("getPersonsByBirthDate", birthDate);
        }
    }
}