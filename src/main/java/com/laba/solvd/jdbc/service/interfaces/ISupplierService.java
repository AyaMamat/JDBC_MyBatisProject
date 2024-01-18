package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Supplier;

import java.util.List;

public interface ISupplierService {

    Supplier create(Supplier supplier);
    List<Supplier> getAll();
    
}
