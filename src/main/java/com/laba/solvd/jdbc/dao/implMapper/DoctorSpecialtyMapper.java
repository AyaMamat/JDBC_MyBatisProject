package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IDoctorSpecialtyDAO;
import com.laba.solvd.jdbc.model.DoctorSpecialty;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DoctorSpecialtyMapper implements IDoctorSpecialtyDAO {

    private static final Logger LOGGER = LogManager.getLogger(DoctorSpecialtyMapper.class.getName());
    private static final MyBatisSQLFactory MY_BATIS = MyBatisSQLFactory.getInstance();

    @Override
    public void saveEntity(DoctorSpecialty doctorSpecialty) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.insert("saveEntity", doctorSpecialty);
            session.commit();
            LOGGER.info("DoctorSpecialty created in SQLSession");
        }

    }

    @Override
    public DoctorSpecialty getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Read doctorSpecialty in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(DoctorSpecialty doctorSpecialty) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.update("updateEntity", doctorSpecialty);
            session.commit();
            LOGGER.info("DoctorSpecialty updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("DoctorSpecialty deleted in SqlSession");
        }
    }

    @Override
    public List<DoctorSpecialty> getAll() {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get all doctorSpecialties in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public DoctorSpecialty getDoctorSpecialtyByTitle(String title) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get doctorSpecialty by title in SqlSession");
            return session.selectOne("getDoctorSpecialtyByTitle", title);
        }
    }
}
