package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Doctor;
import com.laba.solvd.jdbc.model.DoctorSpecialty;

import java.util.List;

public interface IDoctorDAO extends IBaseDAO<Doctor>{

    List<DoctorSpecialty> getDoctorSpecialtyByDoctorId(int id);
}
