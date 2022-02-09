package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.tej.SwaggerCodgen.api.SubscriptionDataApi;
import io.tej.SwaggerCodgen.model.SubscriptionRequest;
import io.tej.SwaggerCodgen.model.SubscriptionResponse;

@RestController
public class SubscriptionController implements SubscriptionDataApi {
    
    @Override
    public ResponseEntity<SubscriptionResponse> createSubscriptionData(SubscriptionRequest subscriptionRequest) {
        System.out.println("subscriptionRequest:["+subscriptionRequest+"]");
        return ResponseEntity.ok(new SubscriptionResponse());
    }

}
