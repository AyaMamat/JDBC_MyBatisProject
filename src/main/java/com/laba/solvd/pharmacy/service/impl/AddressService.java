package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.AddressDAO;
import com.laba.solvd.pharmacy.interfaces.IAddressDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Address;

import java.util.List;


public class AddressService implements IAddressDAO<Address> {

    private static final AddressDAO addressDAO = (AddressDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("addresses");

    @Override
    public void saveEntity(Address address) {
        addressDAO.saveEntity(address);
    }

    @Override
    public Address getEntityByID(int id) {
        return addressDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Address address) {
        addressDAO.updateEntity(address);
    }

    @Override
    public void removeEntityById(int id) {
        addressDAO.removeEntityById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressDAO.getAll();
    }

    @Override
    public List<Address> getAddressByCity(String city) {
        return addressDAO.getAddressByCity(city);
    }

}
