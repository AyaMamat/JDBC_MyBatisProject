package com.laba.solvd.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class PrescribedMedication {

    @JsonProperty("prescriptionId")
    @XmlElement
    private int prescriptionId;

    @JsonProperty("medicationId")
    @XmlElement
    private int medicationId;

    public PrescribedMedication() {

    }

    public PrescribedMedication(int prescriptionId, int medicationId) {
        this.prescriptionId = prescriptionId;
        this.medicationId = medicationId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescribedMedication that = (PrescribedMedication) o;
        return prescriptionId == that.prescriptionId && medicationId == that.medicationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, medicationId);
    }

    @Override
    public String toString() {
        return "PrescribedMedication{" +
                "prescriptionId=" + prescriptionId +
                ", medicationId=" + medicationId +
                '}';
    }
}