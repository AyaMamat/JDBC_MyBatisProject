package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.MedicationDAO;
import com.laba.solvd.jdbc.dao.implMapper.MedicationMapper;
import com.laba.solvd.jdbc.dao.interfaces.IMedicationDAO;
import com.laba.solvd.jdbc.model.Medication;
import com.laba.solvd.jdbc.model.Supplier;
import com.laba.solvd.jdbc.service.interfaces.IMedicationService;
import com.laba.solvd.jdbc.service.interfaces.ISupplierService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class MedicationService implements IMedicationService {
    private static final Logger LOGGER = LogManager.getLogger(MedicationService.class.getName());
    private final IMedicationDAO medicationDAO;
    private final ISupplierService supplierService;

    public MedicationService(ISupplierService supplierService) {
        this.supplierService = supplierService;
        if (!connectionFactory.isMyBatis()) {
            this.medicationDAO = new MedicationDAO();
            LOGGER.info("Using JDBC Medication repository");
        } else {
            this.medicationDAO = new MedicationMapper();
            LOGGER.info("Using MyBatisSQLFactory Medication mapper");
        }
    }

    @Override
    public Medication create(Medication medication) {
        if (medication.getSupplier() != null) {
            Supplier supplier = supplierService.create(medication.getSupplier());
            medication.setSupplier(supplier);
        }
        medicationDAO.saveEntity(medication);
        return medication;
    }

    @Override
    public List<Medication> getAll() {
        return medicationDAO.getAll();
    }
}
