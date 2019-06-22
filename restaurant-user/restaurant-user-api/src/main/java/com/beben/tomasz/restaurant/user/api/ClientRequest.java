package com.beben.tomasz.restaurant.user.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ClientRequest {

    private String name;

    private String surname;

    private String phoneNumber;
}
