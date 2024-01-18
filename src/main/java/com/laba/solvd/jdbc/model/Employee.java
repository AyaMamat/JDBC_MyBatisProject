package com.laba.solvd.jdbc.model;

import java.util.Objects;

public class Employee {
    private int employeeId;
    private Person person;
    private Position position;

    public Employee() {
    }

    public Employee(int employeeId, Person person, Position position) {
        this.employeeId = employeeId;
        this.person = person;
        this.position = position;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && Objects.equals(person, employee.person) && Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, person, position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", person=" + person +
                ", position=" + position +
                '}';
    }
}
