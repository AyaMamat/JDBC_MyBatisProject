package com.laba.solvd.jdbc.model;

import java.util.Date;
import java.util.Objects;

public class Prescription {

    private int prescriptionId;
    private Date prescriptionDate;
    private int quantity;
    private String dosage;
    private Doctor doctor;
    private Customer customer;

    public Prescription() {
    }

    public Prescription(int prescriptionId, Date prescriptionDate, int quantity, String dosage, Doctor doctor, Customer customer) {
        this.prescriptionId = prescriptionId;
        this.prescriptionDate = prescriptionDate;
        this.quantity = quantity;
        this.dosage = dosage;
        this.doctor = doctor;
        this.customer = customer;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return prescriptionId == that.prescriptionId && quantity == that.quantity && Objects.equals(prescriptionDate, that.prescriptionDate) && Objects.equals(dosage, that.dosage) && Objects.equals(doctor, that.doctor) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, prescriptionDate, quantity, dosage, doctor, customer);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", prescriptionDate=" + prescriptionDate +
                ", quantity=" + quantity +
                ", dosage='" + dosage + '\'' +
                ", doctor=" + doctor +
                ", customer=" + customer +
                '}';
    }
}
