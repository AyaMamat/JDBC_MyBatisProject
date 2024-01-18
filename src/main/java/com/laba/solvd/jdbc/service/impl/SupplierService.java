package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.SupplierDAO;
import com.laba.solvd.jdbc.dao.implMapper.SupplierMapper;
import com.laba.solvd.jdbc.dao.interfaces.ISupplierDAO;
import com.laba.solvd.jdbc.model.Supplier;
import com.laba.solvd.jdbc.service.interfaces.ISupplierService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class SupplierService implements ISupplierService {
    private static final Logger logger = LogManager.getLogger(SupplierService.class.getName());
    private final ISupplierDAO supplierDAO;

    public SupplierService() {
        if (!connectionFactory.isMyBatis()) {
            this.supplierDAO = new SupplierDAO();
            logger.info("Using JDBC Supplier repository");
        } else {
            this.supplierDAO = new SupplierMapper();
            logger.info("Using MyBatis Supplier mapper");
        }
    }

    @Override
    public Supplier create(Supplier supplier) {

        supplierDAO.saveEntity(supplier);
        return supplier;
    }

    @Override
    public List<Supplier> getAll() {
        return supplierDAO.getAll();
    }

}
