package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IPrescriptionDAO;
import com.laba.solvd.pharmacy.model.Customer;
import com.laba.solvd.pharmacy.model.Prescription;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PrescriptionDAO implements IPrescriptionDAO<Prescription> {

    private static final Logger LOGGER = LogManager.getLogger(PrescriptionDAO.class.getName());
    private SqlSessionFactory sqlSessionFactory;
    private IPrescriptionDAO myBatisDAO;

    public PrescriptionDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Prescription prescription) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPrescriptionDAO.class);
            session.commit();
            LOGGER.info("Prescription created in SQLSession");
        }
    }

    @Override
    public Prescription getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPrescriptionDAO.class);

            LOGGER.info("Read prescription in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Prescription prescription) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPrescriptionDAO.class);
            myBatisDAO.updateEntity(prescription);
            session.commit();
            LOGGER.info("Prescription updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPrescriptionDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Prescription deleted in SqlSession");
        }
    }

    @Override
    public List<Prescription> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPrescriptionDAO.class);
            LOGGER.info("Get all prescriptions in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Prescription> getPrescriptionByCustomerId(Customer customerId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPrescriptionDAO.class);
            LOGGER.info("Get prescriptions by customer ID in SqlSession");
            return myBatisDAO.getPrescriptionByCustomerId(customerId);
        }
    }
}