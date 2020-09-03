package com.example.storeInspicio.Controllers;

import com.example.storeInspicio.Models.SuggestionModels.Suggestion;
import com.example.storeInspicio.Repos.SuggestionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/suggestion")
public class SuggestionController {
    private final SuggestionRepository suggestionRepo;

    public SuggestionController(SuggestionRepository suggestionRepo) {
        this.suggestionRepo = suggestionRepo;
    }


    @PostMapping()
    Suggestion newSuggestion(@RequestBody Suggestion newSuggestion) {
        return suggestionRepo.save(newSuggestion);
    }

}
