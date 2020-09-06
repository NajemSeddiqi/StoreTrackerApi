package com.example.storeInspicio.Controllers;


import com.example.storeInspicio.CustomExceptions.StoreNotFoundException;
import com.example.storeInspicio.ModelAssemblers.StoreModelAssembler;
import com.example.storeInspicio.Models.StoreModels.Properties;
import com.example.storeInspicio.Models.StoreModels.Store;
import com.example.storeInspicio.Repos.StoreRepository;
import com.example.storeInspicio.Repos.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/stores")
public class StoreController {

    private final StoreRepository storeRepo;
    private final StoreModelAssembler assembler;

    public StoreController(StoreRepository storeRepo, StoreModelAssembler assembler) {
        this.storeRepo = storeRepo;
        this.assembler = assembler;
    }


    //Aggregate root
    @GetMapping()
    public CollectionModel<EntityModel<Store>> all() throws IOException {
        List<EntityModel<Store>> stores = storeRepo.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(stores,
                linkTo(methodOn(StoreController.class).all()).withSelfRel());
    }

    @PostMapping()
    ResponseEntity<?> newStore(@RequestBody Store newStore) {
        EntityModel<Store> entityModel = assembler.toModel(storeRepo.save(newStore));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //Single item
    @GetMapping("/{id}")
    public EntityModel<Store> one(@PathVariable String id) {
        Store store = storeRepo.findById(id)
                .orElseThrow(() -> new StoreNotFoundException(id));

        return assembler.toModel(store);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateStore(@RequestBody Store store, @PathVariable String id) {
        Store updatedStore = storeRepo.findById(id)
                .map(s -> {
                    Properties props = store.getProperties();
                    s.setProperties(new Properties(props.getStore(),
                            props.getPicUrl(),
                            props.getAddress(),
                            props.getOpeningHours(),
                            props.getWebsite(),
                            props.getCity(),
                            props.getProvince()));
                    return storeRepo.save(s);
                })
                .orElseGet(() -> {
                    store.setId(id);
                    return storeRepo.save(store);
                });

        EntityModel<Store> entityModel = assembler.toModel(storeRepo.save(updatedStore));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteStore(@PathVariable String id) {
        storeRepo.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

