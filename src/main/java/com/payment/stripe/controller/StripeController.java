package com.payment.stripe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.stripe.dto.ProductRequest;
import com.payment.stripe.dto.StripeResponse;
import com.payment.stripe.service.StripeService;

@RestController()
@RequestMapping("/product/v1")
public class StripeController {

    StripeService service;

    public StripeController(StripeService service){
        this.service = service;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest){
        var response = service.checkoutProduct(productRequest);
        return  ResponseEntity.ok(response);
    }

}
