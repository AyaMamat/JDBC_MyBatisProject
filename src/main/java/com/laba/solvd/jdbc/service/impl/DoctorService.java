package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.DoctorDAO;
import com.laba.solvd.jdbc.dao.implMapper.DoctorMapper;
import com.laba.solvd.jdbc.dao.interfaces.IDoctorDAO;
import com.laba.solvd.jdbc.model.Doctor;
import com.laba.solvd.jdbc.model.DoctorSpecialty;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.service.interfaces.IDoctorService;
import com.laba.solvd.jdbc.service.interfaces.IDoctorSpecialtyService;
import com.laba.solvd.jdbc.service.interfaces.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class DoctorService implements IDoctorService {
    private static final Logger logger = LogManager.getLogger(DoctorService.class.getName());
    private final IDoctorDAO doctorDAO;
    private final IPersonService personService;
    private final IDoctorSpecialtyService doctorSpecialtyService;


    public DoctorService(IPersonService personService, IDoctorSpecialtyService doctorSpecialtyService) {
        this.personService = personService;
        this.doctorSpecialtyService = doctorSpecialtyService;
        if (!connectionFactory.isMyBatis()) {
            this.doctorDAO = new DoctorDAO();
            logger.info("Using JDBC Doctor repository");
        } else {
            this.doctorDAO = new DoctorMapper();
            logger.info("Using MyBatis Doctor mapper");
        }
    }

    @Override
    public Doctor create(Doctor doctor) {
        if(doctor.getPerson()!=null){
            Person person=personService.create(doctor.getPerson());
            doctor.setPerson(person);
        }

        if (doctor.getSpecialties() != null && !doctor.getSpecialties().isEmpty()) {
            List<DoctorSpecialty> specialties = new ArrayList<>();

            for (DoctorSpecialty specialty : doctor.getSpecialties()) {
                DoctorSpecialty createdSpecialty = doctorSpecialtyService.create(specialty);
                specialties.add(createdSpecialty);
            }

            doctor.setSpecialties(specialties);
        }
        doctorDAO.saveEntity(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> getAll() {
        return doctorDAO.getAll();
    }
}