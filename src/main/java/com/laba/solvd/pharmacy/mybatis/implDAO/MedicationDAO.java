package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IMedicationDAO;
import com.laba.solvd.pharmacy.model.Medication;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MedicationDAO implements IMedicationDAO<Medication> {

    private static final Logger LOGGER = LogManager.getLogger(MedicationDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static IMedicationDAO myBatisDAO;

    public MedicationDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Medication medication) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IMedicationDAO.class);
            myBatisDAO.saveEntity(medication);
            session.commit();
            LOGGER.info("Medication created in SQLSession");
        }
    }

    @Override
    public Medication getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IMedicationDAO.class);
            myBatisDAO.getEntityByID(id);
            LOGGER.info("Read medication in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Medication medication) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IMedicationDAO.class);
            myBatisDAO.updateEntity(medication);
            session.update("updateEntity", medication);
            session.commit();
            LOGGER.info("Medication updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IMedicationDAO.class);
            myBatisDAO.removeEntityById(id);
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Medication deleted in SqlSession");
        }
    }

    @Override
    public List<Medication> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IMedicationDAO.class);
            LOGGER.info("Get all medications in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Medication> getMedicationByName(String name) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IMedicationDAO.class);
            LOGGER.info("Get medications by name in SqlSession");
            return myBatisDAO.getMedicationByName(name);
        }
    }
}
