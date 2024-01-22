package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.SupplierDAO;
import com.laba.solvd.pharmacy.interfaces.ISupplierDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Supplier;

import java.util.List;

public class SupplierService implements ISupplierDAO<Supplier> {

    private static final SupplierDAO supplierDAO = (SupplierDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("suppliers");

    @Override
    public void saveEntity(Supplier supplier) {
        supplierDAO.saveEntity(supplier);
    }

    @Override
    public Supplier getEntityByID(int id) {
        return supplierDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Supplier supplier) {
        supplierDAO.updateEntity(supplier);
    }

    @Override
    public void removeEntityById(int id) {
        supplierDAO.removeEntityById(id);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierDAO.getAll();
    }

    @Override
    public List<Supplier> getSupplierByName(String name) {
        return supplierDAO.getSupplierByName(name);
    }
}
