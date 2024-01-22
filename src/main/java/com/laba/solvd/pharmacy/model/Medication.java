package com.laba.solvd.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Medication {

    @JsonProperty("medicationId")
    @XmlElement
    private int medicationId;

    @JsonProperty("name")
    @XmlElement
    private String name;

    @JsonProperty("description")
    @XmlElement
    private String description;

    @JsonProperty("price")
    @XmlElement
    private double price;

    @JsonProperty("supplier")
    @XmlElement
    private Supplier supplier;

    public Medication() {
    }

    public Medication(int medicationId, String name, String description, double price, Supplier supplier) {
        this.medicationId = medicationId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.supplier = supplier;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return medicationId == that.medicationId && Double.compare(price, that.price) == 0 && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(supplier, that.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationId, name, description, price, supplier);
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medicationId=" + medicationId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", supplier=" + supplier +
                '}';
    }
}
