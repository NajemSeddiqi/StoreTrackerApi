package com.example.storeInspicio.Controllers;


import com.example.storeInspicio.CustomExceptions.StoreNotFoundException;
import com.example.storeInspicio.ModelAssemblers.StoreModelAssembler;
import com.example.storeInspicio.Models.StoreModels.Properties;
import com.example.storeInspicio.Models.StoreModels.Store;
import com.example.storeInspicio.Repos.StoreRepository;
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
public class StoreController {

    private final StoreRepository repository;
    private final StoreModelAssembler assembler;

    public StoreController(StoreRepository repository, StoreModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    //Aggregate root
    @GetMapping("/stores")
    public CollectionModel<EntityModel<Store>> all() throws IOException {
        List<EntityModel<Store>> stores = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(stores,
                linkTo(methodOn(StoreController.class).all()).withSelfRel());
    }

    @PostMapping("/stores")
    ResponseEntity<?> newStore(@RequestBody Store newStore) {
        EntityModel<Store> entityModel = assembler.toModel(repository.save(newStore));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //Single item
    @GetMapping("/stores/{id}")
    public EntityModel<Store> one(@PathVariable String id) {
        Store store = repository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException(id));

        return assembler.toModel(store);
    }

    @PutMapping("/stores/{id}")
    ResponseEntity<?> updateStore(@RequestBody Store store, @PathVariable String id) {
        Store updatedStore = repository.findById(id)
                .map(s -> {
                    Properties props = store.getProperties();
                    s.setProperties(new Properties(props.getStore(),
                            props.getPicUrl(),
                            props.getAddress(),
                            props.getOpeningHours(),
                            props.getWebsite(),
                            props.getCity(),
                            props.getProvince()));
                    return repository.save(s);
                })
                .orElseGet(() -> {
                    store.setId(id);
                    return repository.save(store);
                });

        EntityModel<Store> entityModel = assembler.toModel(repository.save(updatedStore));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/stores/{id}")
    ResponseEntity<?> deleteStore(@PathVariable String id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

