package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IPrescriptionDAO;
import com.laba.solvd.jdbc.model.Customer;
import com.laba.solvd.jdbc.model.Prescription;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PrescriptionMapper implements IPrescriptionDAO {
    private static final Logger logger = LogManager.getLogger(PrescriptionMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(Prescription prescription) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.insert("saveEntity", prescription);
            session.commit();
            logger.info("Prescription created in SQLSession");
        }
    }

    @Override
    public Prescription getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read prescription in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Prescription prescription) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.update("updateEntity", prescription);
            session.commit();
            logger.info("Prescription updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("Prescription deleted in SqlSession");
        }
    }

    @Override
    public List<Prescription> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all prescriptions in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Prescription> getPrescriptionByCustomerId(Customer customerId) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get prescriptions by customer ID in SqlSession");
            return session.selectList("getPrescriptionByCustomerId", customerId.getCustomerId());
        }
    }
}