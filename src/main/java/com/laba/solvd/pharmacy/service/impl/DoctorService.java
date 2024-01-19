package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.DoctorDAO;
import com.laba.solvd.pharmacy.interfaces.IDoctorDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Doctor;
import com.laba.solvd.pharmacy.model.DoctorSpecialty;

import java.util.List;

public class DoctorService implements IDoctorDAO<Doctor> {

    private static final DoctorDAO doctorDAO = (DoctorDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("doctors");

    @Override
    public void saveEntity(Doctor doctor) {
        doctorDAO.saveEntity(doctor);
    }

    @Override
    public Doctor getEntityByID(int id) {
        return doctorDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Doctor doctor) {
        doctorDAO.updateEntity(doctor);
    }

    @Override
    public void removeEntityById(int id) {
        doctorDAO.removeEntityById(id);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorDAO.getAll();
    }

    @Override
    public List<DoctorSpecialty> getDoctorSpecialtyByDoctorId(int id) {
        return doctorDAO.getDoctorSpecialtyByDoctorId(id);
    }
}