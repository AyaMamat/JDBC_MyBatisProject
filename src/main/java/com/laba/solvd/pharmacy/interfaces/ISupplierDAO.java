package com.laba.solvd.pharmacy.interfaces;

import java.util.List;

public interface ISupplierDAO<Supplier> extends IBaseDAO<Supplier> {

    List<Supplier> getSupplierByName(String name);
}
