package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IDoctorDAO;
import com.laba.solvd.pharmacy.model.Doctor;
import com.laba.solvd.pharmacy.model.DoctorSpecialty;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DoctorDAO implements IDoctorDAO<Doctor> {

    private static final Logger LOGGER = LogManager.getLogger(DoctorDAO.class.getName());
    private SqlSessionFactory sqlSessionFactory;
    private IDoctorDAO myBatisDAO;

    public DoctorDAO() {
        sqlSessionFactory= MyBatisConfig.getSqlSessionFactory();
    }


    @Override
    public void saveEntity(Doctor doctor) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorDAO.class);
            myBatisDAO.saveEntity(doctor);
            session.commit();
            LOGGER.info("Doctor created in SQLSession");
        }
    }

    @Override
    public Doctor getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorDAO.class);
            LOGGER.info("Read doctor in SqlSession");
            return (Doctor) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Doctor doctor) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorDAO.class);
            myBatisDAO.updateEntity(doctor);
            session.update("updateEntity", doctor);
            session.commit();
            LOGGER.info("Doctor updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorDAO.class);
            myBatisDAO.removeEntityById(id);
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Doctor deleted in SqlSession");
        }
    }

    @Override
    public List<Doctor> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorDAO.class);
            LOGGER.info("Get all doctors in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<DoctorSpecialty> getDoctorSpecialtyByDoctorId(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorDAO.class);
            LOGGER.info("Get doctor specialties by doctor ID in SqlSession");
            return myBatisDAO.getDoctorSpecialtyByDoctorId(id);
        }
    }
}
