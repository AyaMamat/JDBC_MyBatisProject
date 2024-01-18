package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IPersonDAO;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rewrite.LoggerNameLevelRewritePolicy;

import java.util.List;

public class PersonMapper implements IPersonDAO {
    private static final Logger LOGGER = LogManager.getLogger(PersonMapper.class.getName());
    private static final MyBatisSQLFactory MY_BATIS = MyBatisSQLFactory.getInstance();

    @Override
    public void saveEntity(Person person) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.insert("saveEntity", person);
            session.commit();
            LOGGER.info("Person created in SQLSession");
        }
    }

    @Override
    public Person getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Read person in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Person person) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.update("updateEntity", person);
            session.commit();
            LOGGER.info("Person updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Person deleted in SqlSession");
        }
    }

    @Override
    public List<Person> getAll() {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get all persons in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Person> getPersonsByBirthDate(String birthDate) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get persons by birth date in SqlSession");
            return session.selectList("getPersonsByBirthDate", birthDate);
        }
    }
}