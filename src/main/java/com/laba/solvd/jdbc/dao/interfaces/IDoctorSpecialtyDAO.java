package com.laba.solvd.jdbc.dao.interfaces;


import com.laba.solvd.jdbc.model.DoctorSpecialty;

public interface IDoctorSpecialtyDAO extends IBaseDAO<DoctorSpecialty>{

    DoctorSpecialty getDoctorSpecialtyByTitle(String title);
}
