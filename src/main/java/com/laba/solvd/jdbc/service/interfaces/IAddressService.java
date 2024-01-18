package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Address;

import java.util.List;

public interface IAddressService {

    Address create(Address address);
    List<Address> getAll();
}
