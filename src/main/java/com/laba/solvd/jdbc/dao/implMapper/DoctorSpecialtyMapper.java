package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IDoctorSpecialtyDAO;
import com.laba.solvd.jdbc.model.DoctorSpecialty;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DoctorSpecialtyMapper implements IDoctorSpecialtyDAO {

    private static final Logger logger = LogManager.getLogger(DoctorSpecialtyMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(DoctorSpecialty doctorSpecialty) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.insert("saveEntity", doctorSpecialty);
            session.commit();
            logger.info("DoctorSpecialty created in SQLSession");
        }

    }

    @Override
    public DoctorSpecialty getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read doctorSpecialty in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(DoctorSpecialty doctorSpecialty) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.update("updateEntity", doctorSpecialty);
            session.commit();
            logger.info("DoctorSpecialty updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("DoctorSpecialty deleted in SqlSession");
        }
    }

    @Override
    public List<DoctorSpecialty> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all doctorSpecialties in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public DoctorSpecialty getDoctorSpecialtyByTitle(String title) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get doctorSpecialty by title in SqlSession");
            return session.selectOne("getDoctorSpecialtyByTitle", title);
        }
    }
}
