package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IPositionDAO;
import com.laba.solvd.jdbc.model.Position;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PositionMapper implements IPositionDAO {
    private static final Logger LOGGER = LogManager.getLogger(PositionMapper.class.getName());
    private static final MyBatisSQLFactory MY_BATIS = MyBatisSQLFactory.getInstance();

    @Override
    public void saveEntity(Position position) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.insert("saveEntity", position);
            session.commit();
            LOGGER.info("Position created in SQLSession");
        }
    }

    @Override
    public Position getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Read position in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Position position) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.update("updateEntity", position);
            session.commit();
            LOGGER.info("Position updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Position deleted in SqlSession");
        }
    }

    @Override
    public List<Position> getAll() {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get all positions in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Position> getPositionByTitle(String title) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get positions by title in SqlSession");
            return session.selectList("getPositionByTitle", title);
        }
    }
}