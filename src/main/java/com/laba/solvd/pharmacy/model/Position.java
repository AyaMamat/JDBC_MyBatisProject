package com.laba.solvd.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position {

    @JsonProperty("positionId")
    @XmlElement
    private int positionId;

    @JsonProperty("positionTitle")
    @XmlElement
    private String positionTitle;

    public Position() {
    }

    public Position(int positionId, String positionTitle) {
        this.positionId = positionId;
        this.positionTitle = positionTitle;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionId == position.positionId && Objects.equals(positionTitle, position.positionTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, positionTitle);
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionTitle='" + positionTitle + '\'' +
                '}';
    }
}
