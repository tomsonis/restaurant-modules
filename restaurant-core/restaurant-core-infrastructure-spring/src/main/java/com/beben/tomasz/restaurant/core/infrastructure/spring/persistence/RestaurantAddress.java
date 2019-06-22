package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class RestaurantAddress {

    private String street;

    private String postalCode;

    private String city;

    private String country;
}
