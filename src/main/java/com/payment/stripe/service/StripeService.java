package com.payment.stripe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.payment.stripe.dto.ProductRequest;
import com.payment.stripe.dto.StripeResponse;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private  String stripeKey;
    public StripeResponse checkoutProduct(ProductRequest productRequest){
        Stripe.apiKey = stripeKey;

        SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
        .setName(productRequest.getName())
        .build();

        var priceData =  SessionCreateParams.LineItem.PriceData.builder()
        .setCurrency(productRequest.getCurrency() == null ? "USD" : productRequest.getCurrency())
        .setUnitAmount(productRequest.getAmount())
        .setProductData(productData)
        .build();

        var lineItem = SessionCreateParams.LineItem.builder()
        .setQuantity(productRequest.getQuantity())
        .setPriceData(priceData)
        .build();

        var params = SessionCreateParams.builder()
        .setMode(SessionCreateParams.Mode.PAYMENT)
        .setSuccessUrl("http://localhost:8080/success")
        .setCancelUrl("http://localhost:8080/cancel")
        .addLineItem(lineItem)
        .build();

        Session session = null;
        try {
            session = Session.create(params);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return StripeResponse.builder()
        .status("Success")
        .message("Payment successful")
        .sessionId(session.getId())
        .sessionUrl(session.getUrl())
        .build();
    }

}
