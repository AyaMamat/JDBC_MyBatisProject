package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.DoctorSpecialty;

import java.util.List;

public interface IDoctorSpecialtyService {

    DoctorSpecialty create(DoctorSpecialty doctorSpecialty);
    List<DoctorSpecialty> getAll();
}
