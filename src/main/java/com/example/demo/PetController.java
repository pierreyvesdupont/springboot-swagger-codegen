package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.tej.SwaggerCodgen.api.PetsApi;
import io.tej.SwaggerCodgen.model.Cat;
import io.tej.SwaggerCodgen.model.Dog;

@RestController
public class PetController implements PetsApi {
    
    @Override
    public ResponseEntity<List<Object>> listPets(Integer limit) {
        List<Object> pets = new ArrayList<>();
        pets.add(new Cat()
                         .name("Félix")
                         .tag("MainCoon"));
        pets.add(new Dog()
                        .name("Médor")
                        .tag("Labrador"));
        return ResponseEntity.ok(pets);
    }
}
