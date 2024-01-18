package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Address;

import java.util.List;

public interface IAddressDAO extends IBaseDAO<Address>{

    List<Address> getAddressByCity(String city) ;
}
