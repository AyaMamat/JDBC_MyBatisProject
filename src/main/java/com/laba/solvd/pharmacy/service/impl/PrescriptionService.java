package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.PrescriptionDAO;
import com.laba.solvd.pharmacy.interfaces.IPrescriptionDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Customer;
import com.laba.solvd.pharmacy.model.Prescription;

import java.util.List;

public class PrescriptionService implements IPrescriptionDAO<Prescription> {

    private static final PrescriptionDAO prescriptionDAO = (PrescriptionDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("prescriptions");

    @Override
    public void saveEntity(Prescription prescription) {
        prescriptionDAO.saveEntity(prescription);
    }

    @Override
    public Prescription getEntityByID(int id) {
        return prescriptionDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Prescription prescription) {
        prescriptionDAO.updateEntity(prescription);
    }

    @Override
    public void removeEntityById(int id) {
        prescriptionDAO.removeEntityById(id);
    }

    @Override
    public List<Prescription> getAll() {
        return prescriptionDAO.getAll();
    }

    @Override
    public List<Prescription> getPrescriptionByCustomerId(Customer customerId) {
        return prescriptionDAO.getPrescriptionByCustomerId(customerId);
    }
}