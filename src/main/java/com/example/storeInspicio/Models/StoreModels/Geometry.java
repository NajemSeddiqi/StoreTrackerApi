package com.example.storeInspicio.Models.StoreModels;

import java.util.List;
import java.util.Objects;

public class Geometry {
    private String type;
    private List coordinates;

    public Geometry(String type, List coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(List coordinates) {
        this.coordinates = coordinates;
    }

    public List getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geometry geometry = (Geometry) o;
        return Objects.equals(type, geometry.type) &&
                Objects.equals(coordinates, geometry.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, coordinates);
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}

