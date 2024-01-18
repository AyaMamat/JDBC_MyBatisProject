package com.laba.solvd.jdbc.model;

import java.util.Objects;

public class Customer {

    private int customerId;
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
