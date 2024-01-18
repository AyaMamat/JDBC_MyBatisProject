package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Medication;

import java.util.List;

public interface IMedicationService {

    Medication create(Medication medication);
    List<Medication> getAll();
}
