package com.laba.solvd.pharmacy.interfaces;


public interface IDoctorSpecialtyDAO<DoctorSpecialty> extends IBaseDAO<DoctorSpecialty> {

    DoctorSpecialty getDoctorSpecialtyByTitle(String title);
}
