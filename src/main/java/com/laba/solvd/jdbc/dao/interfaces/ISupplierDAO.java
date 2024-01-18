package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Supplier;

import java.util.List;

public interface ISupplierDAO extends IBaseDAO<Supplier> {
    List<Supplier> getSupplierByName(String name);
}
