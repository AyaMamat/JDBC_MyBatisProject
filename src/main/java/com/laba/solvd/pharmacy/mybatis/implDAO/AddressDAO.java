package com.laba.solvd.pharmacy.mybatis.implDAO;

import com.laba.solvd.pharmacy.interfaces.IAddressDAO;
import com.laba.solvd.pharmacy.model.Address;
import com.laba.solvd.pharmacy.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddressDAO implements IAddressDAO<Address> {

    private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static IAddressDAO myBatisDAO;

    public AddressDAO() {
        sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void saveEntity(Address address) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IAddressDAO.class);
            myBatisDAO.saveEntity(address);
            session.commit();
            LOGGER.info("Address created in SQLSession");
        }
    }

    @Override
    public Address getEntityByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IAddressDAO.class);
            myBatisDAO.getEntityByID(id);
            LOGGER.info("Read address in SqlSession");
            return (Address) myBatisDAO.getEntityByID(id);
        }
    }

    @Override
    public void updateEntity(Address address) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IAddressDAO.class);
            myBatisDAO.updateEntity(address);
            session.commit();
            LOGGER.info("Address updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IAddressDAO.class);
            myBatisDAO.removeEntityById(id);
            session.commit();
            LOGGER.info("Address deleted in SqlSession");
        }
    }

    @Override
    public List<Address> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IAddressDAO.class);
            LOGGER.info("Get all addresses in SqlSession");
            return myBatisDAO.getAll();
        }
    }

    @Override
    public List<Address> getAddressByCity(String city) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            myBatisDAO = session.getMapper(IAddressDAO.class);
            LOGGER.info("Get addresses by city in SqlSession");
            return myBatisDAO.getAddressByCity(city);
        }
    }
}
