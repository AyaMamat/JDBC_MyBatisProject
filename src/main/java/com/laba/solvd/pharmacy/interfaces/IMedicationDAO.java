package com.laba.solvd.pharmacy.interfaces;

import java.util.List;

public interface IMedicationDAO<Medication> extends IBaseDAO<Medication> {

    List<Medication> getMedicationByName(String name);
}
