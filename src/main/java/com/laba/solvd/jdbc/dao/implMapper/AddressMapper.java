package com.laba.solvd.jdbc.dao.implMapper;

import com.laba.solvd.jdbc.dao.interfaces.IAddressDAO;
import com.laba.solvd.jdbc.model.Address;
import com.laba.solvd.jdbc.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddressMapper implements IAddressDAO {

    private static final Logger logger = LogManager.getLogger(AddressMapper.class.getName());
    private static final MyBatis MY_BATIS = MyBatis.getInstance();

    @Override
    public void saveEntity(Address address) {
        try(SqlSession session= MY_BATIS.getSession();){
            session.insert("saveEntity",address);
            session.commit();
            logger.info("Address created in SQLSession");
        }

    }

    @Override
    public Address getEntityByID(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Read address in SqlSession");
            return session.selectOne("getEntityById", id);
        }
    }

    @Override
    public void updateEntity(Address address) {
        try (SqlSession session = MY_BATIS.getSession();){
            session.update("updateEntity", address);
            session.commit();
            logger.info("Address updated in SqlSession");
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = MY_BATIS.getSession();) {
            session.delete("removeEntityById", id);
            session.commit();
            logger.info("Address deleted in SqlSession");
        }
    }

    @Override
    public List<Address> getAll() {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get all addresses in SqlSession");
            return session.selectList("getAll");
        }
    }

    @Override
    public List<Address> getAddressByCity(String city) {
        try (SqlSession session = MY_BATIS.getSession();) {
            logger.info("Get addresses by city in SqlSession");
            return session.selectList("getAddressesByCity", city);
        }
    }
}
