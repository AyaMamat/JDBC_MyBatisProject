package com.laba.solvd.pharmacy.factory;

import com.laba.solvd.pharmacy.interfaces.IBaseDAO;
import com.laba.solvd.pharmacy.jdbc.implDAO.*;


public class JDBCDAOFactory implements IBaseDAOFactory {

    @Override
    public IBaseDAO getDAO(String tableName) {
        return switch (tableName) {
            case "addresses" -> new AddressDAO();
            case "customers" -> new CustomerDAO();
            case "doctors" -> new DoctorDAO();
            case "doctor_specialties" -> new DoctorSpecialtyDAO();
            case "employees" -> new EmployeeDAO();
            case "medications" -> new MedicationDAO();
            case "people" -> new PersonDAO();
            case "positions" -> new PositionDAO();
            case "prescriptions" -> new PrescriptionDAO();
            case "sales" -> new SaleDAO();
            case "suppliers" -> new SupplierDAO();
            default -> null;
        };
    }
}