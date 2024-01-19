package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IDoctorSpecialtyDAO;
import com.laba.solvd.pharmacy.model.DoctorSpecialty;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DoctorSpecialtyDAO implements IDoctorSpecialtyDAO<DoctorSpecialty> {

    private static final Logger LOGGER = LogManager.getLogger(DoctorSpecialtyDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static IDoctorSpecialtyDAO myBatisDAO;

    public DoctorSpecialtyDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(DoctorSpecialty doctorSpecialty) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorSpecialtyDAO.class);
            myBatisDAO.saveEntity(doctorSpecialty);
            session.commit();
            LOGGER.info("DoctorSpecialty created in SQLSession");
        }

    }

    @Override
    public DoctorSpecialty getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorSpecialtyDAO.class);
            myBatisDAO.getEntityByID(id);
            LOGGER.info("Read doctorSpecialty in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(DoctorSpecialty doctorSpecialty) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorSpecialtyDAO.class);
            myBatisDAO.updateEntity(doctorSpecialty);
            session.commit();
            LOGGER.info("DoctorSpecialty updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorSpecialtyDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("DoctorSpecialty deleted in SqlSession");
        }
    }

    @Override
    public List<DoctorSpecialty> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorSpecialtyDAO.class);
            LOGGER.info("Get all doctorSpecialties in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public DoctorSpecialty getDoctorSpecialtyByTitle(String title) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IDoctorSpecialtyDAO.class);
            LOGGER.info("Get doctorSpecialty by title in SqlSession");
            return (DoctorSpecialty) myBatisDAO.getDoctorSpecialtyByTitle(title);
        }
    }
}
