package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Prescription;

import java.util.List;

public interface IPrescriptionService {

    Prescription create(Prescription prescription);
    List<Prescription> getAll();
}
