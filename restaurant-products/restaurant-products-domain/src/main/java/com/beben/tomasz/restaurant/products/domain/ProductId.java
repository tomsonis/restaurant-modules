package com.beben.tomasz.restaurant.products.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ProductId {

    private String id;

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
