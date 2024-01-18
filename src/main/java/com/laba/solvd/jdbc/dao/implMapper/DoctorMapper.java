package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IDoctorDAO;
import com.laba.solvd.jdbc.model.Doctor;
import com.laba.solvd.jdbc.model.DoctorSpecialty;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DoctorMapper implements IDoctorDAO {

    private static final Logger LOGGER = LogManager.getLogger(DoctorMapper.class.getName());
    private static final MyBatisSQLFactory MY_BATIS = MyBatisSQLFactory.getInstance();

    @Override
    public void saveEntity(Doctor doctor) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.insert("saveEntity", doctor);
            session.commit();
            LOGGER.info("Doctor created in SQLSession");
        }
    }

    @Override
    public Doctor getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Read doctor in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Doctor doctor) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.update("updateEntity", doctor);
            session.commit();
            LOGGER.info("Doctor updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Doctor deleted in SqlSession");
        }
    }

    @Override
    public List<Doctor> getAll() {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get all doctors in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<DoctorSpecialty> getDoctorSpecialtyByDoctorId(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get doctor specialties by doctor ID in SqlSession");
            return session.selectList("getDoctorSpecialtyByDoctorId", id);
        }
    }
}
