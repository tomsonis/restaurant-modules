package com.beben.tomasz.restaurant.commons.view;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class AddressView {

    private String street;

    private String postalCode;

    private String city;

    private String country;
}
