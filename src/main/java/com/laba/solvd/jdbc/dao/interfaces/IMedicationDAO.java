package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Medication;

import java.util.List;

public interface IMedicationDAO extends IBaseDAO<Medication> {
    List<Medication> getMedicationByName(String name);
}
