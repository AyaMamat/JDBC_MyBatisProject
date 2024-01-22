package com.laba.solvd.pharmacy.builder;

import com.laba.solvd.pharmacy.model.Doctor;
import com.laba.solvd.pharmacy.model.DoctorSpecialty;
import com.laba.solvd.pharmacy.model.Person;

import java.util.List;

public class DoctorBuilder {

    private int doctorId;
    private Person person;
    private List<DoctorSpecialty> specialties;

    public DoctorBuilder() {
    }

    public DoctorBuilder withDoctorId(int doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public DoctorBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    public DoctorBuilder withSpecialties(List<DoctorSpecialty> specialties) {
        this.specialties = specialties;
        return this;
    }

    public Doctor build() {
        return new Doctor(doctorId, person, specialties);
    }
}
