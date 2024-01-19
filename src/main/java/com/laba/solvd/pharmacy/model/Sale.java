package com.laba.solvd.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laba.solvd.pharmacy.parsers.parsers.DateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sale {

    @JsonProperty("saleId")
    @XmlElement
    private int saleId;

    @JsonProperty("totalPrice")
    @XmlElement
    private double totalPrice;

    @JsonProperty("saleDate")
    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate saleDate;

    @JsonProperty("employee")
    @XmlElement
    private Employee employee;

    @JsonProperty("prescription")
    @XmlElement
    private Prescription prescription;

    public Sale() {
    }

    public Sale(int saleId, double totalPrice, LocalDate saleDate, Employee employee, Prescription prescription) {
        this.saleId = saleId;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
        this.employee = employee;
        this.prescription = prescription;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return saleId == sale.saleId && Double.compare(totalPrice, sale.totalPrice) == 0 && Objects.equals(saleDate, sale.saleDate) && Objects.equals(employee, sale.employee) && Objects.equals(prescription, sale.prescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, totalPrice, saleDate, employee, prescription);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", totalPrice=" + totalPrice +
                ", saleDate=" + saleDate +
                ", employee=" + employee +
                ", prescription=" + prescription +
                '}';
    }
}
