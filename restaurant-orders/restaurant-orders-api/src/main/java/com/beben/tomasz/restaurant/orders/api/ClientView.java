package com.beben.tomasz.restaurant.orders.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ClientView {

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;
}
