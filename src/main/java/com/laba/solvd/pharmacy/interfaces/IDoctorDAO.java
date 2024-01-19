package com.laba.solvd.pharmacy.interfaces;

import com.laba.solvd.pharmacy.model.DoctorSpecialty;

import java.util.List;

public interface IDoctorDAO<Doctor> extends IBaseDAO<Doctor> {

    List<DoctorSpecialty> getDoctorSpecialtyByDoctorId(int id);
}
