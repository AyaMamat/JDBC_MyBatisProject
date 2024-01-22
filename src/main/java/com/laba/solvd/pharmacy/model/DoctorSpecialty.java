package com.laba.solvd.pharmacy.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class DoctorSpecialty {

    @JsonProperty("doctorSpecialtyId")
    @XmlElement(defaultValue = "0")
    private int doctorSpecialtyId;

    @JsonProperty("title")
    @XmlElement(defaultValue = "")
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
