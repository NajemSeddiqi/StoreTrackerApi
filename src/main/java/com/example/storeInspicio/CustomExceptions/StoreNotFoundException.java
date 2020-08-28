package com.example.storeInspicio.CustomExceptions;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(String id) {
        super("Could not find store " + id);
    }
}
