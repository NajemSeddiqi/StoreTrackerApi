package com.example.storeInspicio.ModelAssemblers;

import com.example.storeInspicio.Controllers.StoreController;
import com.example.storeInspicio.Models.StoreModels.Store;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class StoreModelAssembler implements RepresentationModelAssembler<Store, EntityModel<Store>> {
    @Override
    public EntityModel<Store> toModel(Store entity) {

        try {
            return EntityModel.of(entity,
                    linkTo(methodOn(StoreController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(StoreController.class).all()).withRel("stores"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
