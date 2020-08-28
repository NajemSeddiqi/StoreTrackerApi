package com.example.storeInspicio.Models.StoreModels;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document(collection = "Stores")
public class Store {

    @Id
    private String id;

    private String type = "Feature";

    private Properties properties;

    private Geometry geometry;

    public Store(Properties properties, Geometry geometry) {
        this.properties = properties;
        this.geometry = geometry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return id.equals(store.id) && properties.equals(store.properties) && geometry.equals(store.geometry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, properties, geometry);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", storeProperties=" + properties +
                ", geometry=" + geometry +
                '}';
    }
}

