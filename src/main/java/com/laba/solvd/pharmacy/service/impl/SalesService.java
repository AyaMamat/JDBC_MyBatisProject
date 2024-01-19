package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.SalesDAO;
import com.laba.solvd.pharmacy.interfaces.ISalesDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Sale;

import java.time.LocalDate;
import java.util.List;

public class SalesService implements ISalesDAO<Sale> {

    private static final SalesDAO saleDAO = (SalesDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("sales");

    @Override
    public void saveEntity(Sale sale) {
        saleDAO.saveEntity(sale);
    }

    @Override
    public Sale getEntityByID(int id) {
        return saleDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Sale sale) {
        saleDAO.updateEntity(sale);
    }

    @Override
    public void removeEntityById(int id) {
        saleDAO.removeEntityById(id);
    }

    @Override
    public List<Sale> getAll() {
        return saleDAO.getAll();
    }

    @Override
    public List<Sale> getSalesByDate(LocalDate date) {
        return saleDAO.getSalesByDate(date);
    }
}