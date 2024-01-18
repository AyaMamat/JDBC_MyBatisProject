package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.PrescriptionDAO;
import com.laba.solvd.jdbc.dao.implMapper.PrescriptionMapper;
import com.laba.solvd.jdbc.dao.interfaces.IPrescriptionDAO;
import com.laba.solvd.jdbc.model.Customer;
import com.laba.solvd.jdbc.model.Doctor;
import com.laba.solvd.jdbc.model.Prescription;
import com.laba.solvd.jdbc.service.interfaces.ICustomerService;
import com.laba.solvd.jdbc.service.interfaces.IDoctorService;
import com.laba.solvd.jdbc.service.interfaces.IPrescriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class PrescriptionService implements IPrescriptionService {
    private static final Logger LOGGER = LogManager.getLogger(PrescriptionService.class.getName());
    private  IPrescriptionDAO prescriptionDAO;
    private  IDoctorService doctorService;
    private  ICustomerService customerService;

    public PrescriptionService(IDoctorService doctorService, ICustomerService customerService) {
        this.doctorService = doctorService;
        this.customerService = customerService;
        if (!connectionFactory.isMyBatis()) {
            this.prescriptionDAO = new PrescriptionDAO();
            LOGGER.info("Using JDBC Prescription repository");
        } else {
            this.prescriptionDAO = new PrescriptionMapper();
            LOGGER.info("Using MyBatisSQLFactory Prescription mapper");
        }
    }

    @Override
    public Prescription create(Prescription prescription) {
        if (prescription.getDoctor() != null) {
            Doctor doctor = doctorService.create(prescription.getDoctor());
            prescription.setDoctor(doctor);
        }

        if (prescription.getCustomer() != null) {
            Customer customer = customerService.create(prescription.getCustomer());
            prescription.setCustomer(customer);
        }

        prescriptionDAO.saveEntity(prescription);
        return prescription;
    }

    @Override
    public List<Prescription> getAll() {
        return prescriptionDAO.getAll();
    }
}
