package com.example.storeInspicio.Repos;

import com.example.storeInspicio.Models.SuggestionModels.Suggestion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SuggestionRepository extends MongoRepository<Suggestion, String> {
}
