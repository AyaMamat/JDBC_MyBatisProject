package com.laba.solvd.pharmacy.interfaces;

import com.laba.solvd.pharmacy.model.Customer;

import java.util.List;

public interface IPrescriptionDAO<Prescription> extends IBaseDAO<Prescription> {

    List<Prescription> getPrescriptionByCustomerId(Customer customerId);
}
