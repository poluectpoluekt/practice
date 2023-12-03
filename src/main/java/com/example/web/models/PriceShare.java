package com.example.web.models;

import org.springframework.stereotype.Component;

@Component
public class PriceShare {
    private final Double price = 0.02;

    public Double getPrice() {
        return price;
    }
}
