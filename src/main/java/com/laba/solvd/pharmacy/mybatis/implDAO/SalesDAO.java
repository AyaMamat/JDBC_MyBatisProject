package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.ISalesDAO;
import com.laba.solvd.pharmacy.model.Sale;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class SalesDAO implements ISalesDAO<Sale> {

    private static final Logger LOGGER = LogManager.getLogger(SalesDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static ISalesDAO myBatisDAO;

    public SalesDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Sale sale) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISalesDAO.class);
            myBatisDAO.saveEntity(sale);
            session.commit();
            LOGGER.info("Sale created in SQLSession");
        }
    }

    @Override
    public Sale getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISalesDAO.class);
            LOGGER.info("Read sale in SqlSession");
            return (Sale) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Sale sale) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISalesDAO.class);
            myBatisDAO.updateEntity(sale);
            session.commit();
            LOGGER.info("Sale updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISalesDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Sale deleted in SqlSession");
        }
    }

    @Override
    public List<Sale> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISalesDAO.class);
            LOGGER.info("Get all sales in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Sale> getSalesByDate(LocalDate date) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISalesDAO.class);
            LOGGER.info("Get sales by date in SqlSession");
            return myBatisDAO.getSalesByDate(date);
        }
    }
}