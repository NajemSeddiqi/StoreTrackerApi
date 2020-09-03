package com.example.storeInspicio.Models.SuggestionModels;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document(collection = "Suggestions")
public class Suggestion {

    @Id
    private String id;
    private String store;
    private String address;

    public Suggestion(String id, String store, String address) {
        this.id = id;
        this.store = store;
        this.address = address;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion that = (Suggestion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(store, that.store) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, address);
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "id='" + id + '\'' +
                ", store='" + store + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
