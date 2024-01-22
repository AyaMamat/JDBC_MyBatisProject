package com.laba.solvd.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier {

    @JsonProperty("supplierId")
    @XmlElement
    private int supplierId;

    @JsonProperty("companyName")
    @XmlElement
    private String companyName;

    public Supplier() {
    }

    public Supplier(int supplierId, String companyName) {
        this.supplierId = supplierId;
        this.companyName = companyName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return supplierId == supplier.supplierId && Objects.equals(companyName, supplier.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, companyName);
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", companyName='" + companyName + '\'' + '}';
    }
}
