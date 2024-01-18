package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IPositionDAO;
import com.laba.solvd.jdbc.model.Position;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PositionMapper implements IPositionDAO {
    private static final Logger logger = LogManager.getLogger(PositionMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(Position position) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.insert("saveEntity", position);
            session.commit();
            logger.info("Position created in SQLSession");
        }
    }

    @Override
    public Position getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read position in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Position position) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.update("updateEntity", position);
            session.commit();
            logger.info("Position updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("Position deleted in SqlSession");
        }
    }

    @Override
    public List<Position> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all positions in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Position> getPositionByTitle(String title) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get positions by title in SqlSession");
            return session.selectList("getPositionByTitle", title);
        }
    }
}