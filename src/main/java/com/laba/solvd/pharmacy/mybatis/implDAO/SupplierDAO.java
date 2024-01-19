package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.ISupplierDAO;
import com.laba.solvd.pharmacy.model.Supplier;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SupplierDAO implements ISupplierDAO<Supplier> {

    private static final Logger LOGGER = LogManager.getLogger(SupplierDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static ISupplierDAO myBatisDAO;

    public SupplierDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Supplier supplier) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISupplierDAO.class);
            myBatisDAO.saveEntity(supplier);
            session.commit();
            LOGGER.info("Supplier created in SQLSession");
        }
    }

    @Override
    public Supplier getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISupplierDAO.class);
            LOGGER.info("Read supplier in SqlSession");
            return (Supplier) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Supplier supplier) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISupplierDAO.class);
            myBatisDAO.updateEntity(supplier);
            session.commit();
            LOGGER.info("Supplier updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISupplierDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Supplier deleted in SqlSession");
        }
    }

    @Override
    public List<Supplier> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISupplierDAO.class);
            LOGGER.info("Get all suppliers in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Supplier> getSupplierByName(String name) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(ISupplierDAO.class);
            LOGGER.info("Get suppliers by name in SqlSession");
            return myBatisDAO.getSupplierByName(name);
        }
    }
}