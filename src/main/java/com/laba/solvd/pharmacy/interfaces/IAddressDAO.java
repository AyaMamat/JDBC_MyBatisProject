package com.laba.solvd.pharmacy.interfaces;

import java.util.List;

public interface IAddressDAO<Address> extends IBaseDAO<Address> {

    List<Address> getAddressByCity(String city);
}
