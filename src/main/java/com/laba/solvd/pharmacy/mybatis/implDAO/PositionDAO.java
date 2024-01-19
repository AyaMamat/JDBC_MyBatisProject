package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IPositionDAO;
import com.laba.solvd.pharmacy.model.Position;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PositionDAO implements IPositionDAO<Position> {
    private static final Logger LOGGER = LogManager.getLogger(PositionDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static IPositionDAO myBatisDAO;

    public PositionDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Position position) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPositionDAO.class);
            myBatisDAO.saveEntity(position);
            session.commit();
            LOGGER.info("Position created in SQLSession");
        }
    }

    @Override
    public Position getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPositionDAO.class);
            LOGGER.info("Read position in SqlSession");
            return (Position) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Position position) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPositionDAO.class);
            myBatisDAO.updateEntity(position);
            session.commit();
            LOGGER.info("Position updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPositionDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Position deleted in SqlSession");
        }
    }

    @Override
    public List<Position> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPositionDAO.class);
            LOGGER.info("Get all positions in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Position> getPositionByTitle(String title) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IPositionDAO.class);
            LOGGER.info("Get positions by title in SqlSession");
            return myBatisDAO.getPositionByTitle(title);
        }
    }
}