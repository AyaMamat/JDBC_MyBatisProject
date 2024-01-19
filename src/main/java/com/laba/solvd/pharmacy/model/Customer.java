package com.laba.solvd.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @JsonProperty("customerId")
    @XmlElement
    private int customerId;

    @JsonProperty("person")
    @XmlElement
    private Person person;

    public Customer() {
    }


    public Customer(int customerId, Person person) {
        this.customerId = customerId;
        this.person = person;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(person, customer.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, person);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", person=" + person +
                '}';
    }
}
