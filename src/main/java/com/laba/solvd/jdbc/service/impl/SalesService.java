package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.SaleDAO;
import com.laba.solvd.jdbc.dao.implMapper.SalesMapper;
import com.laba.solvd.jdbc.dao.interfaces.ISalesDAO;
import com.laba.solvd.jdbc.model.Employee;
import com.laba.solvd.jdbc.model.Prescription;
import com.laba.solvd.jdbc.model.Sale;
import com.laba.solvd.jdbc.service.interfaces.IEmployeeService;
import com.laba.solvd.jdbc.service.interfaces.IPrescriptionService;
import com.laba.solvd.jdbc.service.interfaces.ISalesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class SalesService implements ISalesService {
    private static final Logger logger = LogManager.getLogger(SalesService.class.getName());
    private final ISalesDAO salesDAO;
    private final IEmployeeService employeeService;
    private final IPrescriptionService prescriptionService;

    public SalesService(IEmployeeService employeeService, IPrescriptionService prescriptionService) {
        this.employeeService = employeeService;
        this.prescriptionService = prescriptionService;
        if (!connectionFactory.isMyBatis()) {
            this.salesDAO = new SaleDAO();
            logger.info("Using JDBC Sales repository");
        } else {
            this.salesDAO = new SalesMapper();
            logger.info("Using MyBatis Sales mapper");
        }
    }

    @Override
    public Sale create(Sale sale) {

        if (sale.getEmployee() != null) {
            Employee employee = employeeService.create(sale.getEmployee());
            sale.setEmployee(employee);
        }

        if (sale.getPrescription() != null) {
            Prescription prescription = prescriptionService.create(sale.getPrescription());
            sale.setPrescription(prescription);
        }

        salesDAO.saveEntity(sale);
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        return salesDAO.getAll();
    }
}
