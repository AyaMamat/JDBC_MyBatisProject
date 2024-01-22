package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.MedicationDAO;
import com.laba.solvd.pharmacy.interfaces.IMedicationDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Medication;

import java.util.List;

public class MedicationService implements IMedicationDAO<Medication> {

    private static final MedicationDAO medicationDAO = (MedicationDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("medications");

    @Override
    public void saveEntity(Medication medication) {
        medicationDAO.saveEntity(medication);
    }

    @Override
    public Medication getEntityByID(int id) {
        return medicationDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Medication medication) {
        medicationDAO.updateEntity(medication);
    }

    @Override
    public void removeEntityById(int id) {
        medicationDAO.removeEntityById(id);

    }

    @Override
    public List<Medication> getAll() {
        return medicationDAO.getAll();
    }

    @Override
    public List<Medication> getMedicationByName(String name) {
        return medicationDAO.getMedicationByName(name);
    }
}

