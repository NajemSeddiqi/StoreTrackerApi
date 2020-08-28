package com.example.storeInspicio.Repos;

import com.example.storeInspicio.Models.StoreModels.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
}
