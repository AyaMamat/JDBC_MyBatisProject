package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.DoctorSpecialtyDAO;
import com.laba.solvd.pharmacy.interfaces.IDoctorSpecialtyDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.DoctorSpecialty;

import java.util.List;

public class DoctorSpecialtyService implements IDoctorSpecialtyDAO<DoctorSpecialty> {

    private static final DoctorSpecialtyDAO doctorSpecialtyDAO = (DoctorSpecialtyDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("doctor_specialty");

    @Override
    public void saveEntity(DoctorSpecialty doctorSpecialty) {
        doctorSpecialtyDAO.saveEntity(doctorSpecialty);
    }

    @Override
    public DoctorSpecialty getEntityByID(int id) {
        return doctorSpecialtyDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(DoctorSpecialty doctorSpecialty) {
        doctorSpecialtyDAO.updateEntity(doctorSpecialty);
    }

    @Override
    public void removeEntityById(int id) {
        doctorSpecialtyDAO.removeEntityById(id);
    }

    @Override
    public List<DoctorSpecialty> getAll() {
        return doctorSpecialtyDAO.getAll();
    }

    @Override
    public DoctorSpecialty getDoctorSpecialtyByTitle(String title) {
        return doctorSpecialtyDAO.getDoctorSpecialtyByTitle(title);
    }
}