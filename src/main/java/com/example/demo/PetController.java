package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.tej.SwaggerCodgen.model.SubscriptionRequest;
import io.tej.SwaggerCodgen.model.SubscriptionResponse;
import io.tej.SwaggerCodgen.api.PetsApi;
import io.tej.SwaggerCodgen.model.Pet;

@RestController
public class PetController implements PetsApi {
    

    @Override
    public ResponseEntity<String> createPets(Pet pet) {
        System.out.println("createPets:["+pet+"]");
        return ResponseEntity.ok(pet.toString());

    }

}
