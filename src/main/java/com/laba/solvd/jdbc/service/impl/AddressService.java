package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.AddressDAO;
import com.laba.solvd.jdbc.dao.implMapper.AddressMapper;
import com.laba.solvd.jdbc.dao.interfaces.IAddressDAO;
import com.laba.solvd.jdbc.model.Address;
import com.laba.solvd.jdbc.service.interfaces.IAddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class AddressService implements IAddressService {
    private static final Logger logger = LogManager.getLogger(AddressService.class.getName());
    private final IAddressDAO addressDAO;

    public AddressService() {
        if (!connectionFactory.isMyBatis()) {
            this.addressDAO = new AddressDAO();
            logger.info("Using JDBC Airline repository");
        }
        else {
            this.addressDAO = new AddressMapper();
            logger.info("Using MyBatis Airline mapper");
        }
    }

    @Override
    public Address create(Address address) {
        addressDAO.saveEntity(address);
        return address;
    }

    @Override
    public List<Address> getAll() {
        return addressDAO.getAll();
    }
}
