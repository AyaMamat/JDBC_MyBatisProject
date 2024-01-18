package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.ISupplierDAO;
import com.laba.solvd.jdbc.model.Supplier;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SupplierMapper implements ISupplierDAO {
    private static final Logger logger = LogManager.getLogger(SupplierMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(Supplier supplier) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.insert("saveEntity", supplier);
            session.commit();
            logger.info("Supplier created in SQLSession");
        }
    }

    @Override
    public Supplier getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read supplier in SqlSession");
            return session.selectOne("getEntityByID", id);
        }
    }

    @Override
    public void updateEntity(Supplier supplier) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.update("updateEntity", supplier);
            session.commit();
            logger.info("Supplier updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("Supplier deleted in SqlSession");
        }
    }

    @Override
    public List<Supplier> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all suppliers in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Supplier> getSupplierByName(String name) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get suppliers by name in SqlSession");
            return session.selectList("getSupplierByName", name);
        }
    }
}