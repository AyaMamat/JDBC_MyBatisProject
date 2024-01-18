package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.ISalesDAO;
import com.laba.solvd.jdbc.model.Sale;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class SalesMapper implements ISalesDAO {
    private static final Logger logger = LogManager.getLogger(SalesMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(Sale sale) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.insert("saveEntity", sale);
            session.commit();
            logger.info("Sale created in SQLSession");
        }
    }

    @Override
    public Sale getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read sale in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Sale sale) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.update("updateEntity", sale);
            session.commit();
            logger.info("Sale updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("Sale deleted in SqlSession");
        }
    }

    @Override
    public List<Sale> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all sales in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Sale> getSalesByDate(Date date) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get sales by date in SqlSession");
            return session.selectList("getSalesByDate", new java.sql.Date(date.getTime()));
        }
    }
}