package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Doctor;

import java.util.List;

public interface IDoctorService {
    Doctor create(Doctor doctor);
    List<Doctor> getAll();
}
