package com.beben.tomasz.restaurant.core.domain;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class Address {

    private String street;

    private String postalCode;

    private String city;

    private String country;
}
