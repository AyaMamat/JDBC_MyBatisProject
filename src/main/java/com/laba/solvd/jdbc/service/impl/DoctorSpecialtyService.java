package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.DoctorSpecialtyDAO;
import com.laba.solvd.jdbc.dao.implMapper.DoctorSpecialtyMapper;
import com.laba.solvd.jdbc.dao.interfaces.IDoctorSpecialtyDAO;
import com.laba.solvd.jdbc.model.DoctorSpecialty;
import com.laba.solvd.jdbc.service.interfaces.IDoctorSpecialtyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class DoctorSpecialtyService implements IDoctorSpecialtyService {
    private static final Logger LOGGER = LogManager.getLogger(DoctorSpecialtyService.class.getName());
    private final IDoctorSpecialtyDAO doctorSpecialtyDAO;

    public DoctorSpecialtyService() {
        if (!connectionFactory.isMyBatis()) {
            this.doctorSpecialtyDAO = new DoctorSpecialtyDAO();
            LOGGER.info("Using JDBC DoctorSpecialty repository");
        } else {
            this.doctorSpecialtyDAO = new DoctorSpecialtyMapper();
            LOGGER.info("Using MyBatisSQLFactory DoctorSpecialty mapper");
        }
    }

    @Override
    public DoctorSpecialty create(DoctorSpecialty doctorSpecialty) {
        doctorSpecialtyDAO.saveEntity(doctorSpecialty);
        return doctorSpecialty;
    }

    @Override
    public List<DoctorSpecialty> getAll() {
        return doctorSpecialtyDAO.getAll();
    }
}
