package com.laba.solvd.jdbc.model;


import java.util.Objects;

public class DoctorSpecialty {

    private int doctorSpecialtyId;
    private String title;


    public DoctorSpecialty() {
    }

    public DoctorSpecialty(int doctorSpecialtyId, String title) {
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.title = title;
    }

    public int getDoctorSpecialtyId() {
        return doctorSpecialtyId;
    }

    public void setDoctorSpecialtyId(Integer doctorSpecialtyId) {
        this.doctorSpecialtyId = doctorSpecialtyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorSpecialty that = (DoctorSpecialty) o;
        return doctorSpecialtyId == that.doctorSpecialtyId && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorSpecialtyId, title);
    }
}
