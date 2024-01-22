package com.laba.solvd.pharmacy.interfaces;

import java.util.List;

public interface IDoctorSpecialtyDAO<DoctorSpecialty> extends IBaseDAO<DoctorSpecialty> {

    List<DoctorSpecialty> getDoctorSpecialtyByDoctorId(int id);
}
