package com.payment.stripe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ProductRequest {

    private Long amount;
    private Long  quantity;
    private String  name;
    private String currency;
}
