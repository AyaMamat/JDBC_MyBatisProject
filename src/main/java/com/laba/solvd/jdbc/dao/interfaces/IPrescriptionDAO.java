package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Customer;
import com.laba.solvd.jdbc.model.Prescription;

import java.util.List;

public interface IPrescriptionDAO extends IBaseDAO<Prescription>{

    List<Prescription> getPrescriptionByCustomerId(Customer customerId);
}
