package com.laba.solvd.jdbc.model;

import java.util.Objects;

public class PrescribedMedication {

    private int prescriptionId;
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