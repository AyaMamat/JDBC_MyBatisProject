package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IAddressDAO;
import com.laba.solvd.jdbc.model.Address;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddressMapper implements IAddressDAO {

    private static final Logger LOGGER = LogManager.getLogger(AddressMapper.class.getName());
    private static final MyBatisSQLFactory MY_BATIS = MyBatisSQLFactory.getInstance();

    @Override
    public void saveEntity(Address address) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.insert("saveEntity", address);
            session.commit();
            LOGGER.info("Address created in SQLSession");
        }

    }

    @Override
    public Address getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Read address in SqlSession");
            return session.selectOne("getEntityById", id);
        }
    }

    @Override
    public void updateEntity(Address address) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.update("updateEntity", address);
            session.commit();
            LOGGER.info("Address updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession()) {
            session.delete("removeEntityById", id);
            session.commit();
            LOGGER.info("Address deleted in SqlSession");
        }
    }

    @Override
    public List<Address> getAll() {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get all addresses in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Address> getAddressByCity(String city) {
        try (SqlSession session = MY_BATIS.getSession()) {
            LOGGER.info("Get addresses by city in SqlSession");
            return session.selectList("getAddressesByCity", city);
        }
    }
}
