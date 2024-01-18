package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IMedicationDAO;
import com.laba.solvd.jdbc.model.Medication;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MedicationMapper implements IMedicationDAO {
    private static final Logger LOGGER = LogManager.getLogger(MedicationMapper.class.getName());
    private static final MyBatisSQLFactory MY_BATIS = MyBatisSQLFactory.getInstance();

    @Override
    public void saveEntity(Medication medication) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.insert("saveEntity", medication);
            session.commit();
            LOGGER.info("Medication created in SQLSession");
        }
    }

    @Override
    public Medication getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Read medication in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Medication medication) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.update("updateEntity", medication);
            session.commit();
            LOGGER.info("Medication updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Medication deleted in SqlSession");
        }
    }

    @Override
    public List<Medication> getAll() {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get all medications in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Medication> getMedicationByName(String name) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get medications by name in SqlSession");
            return session.selectList("getMedicationByName", name);
        }
    }
}
