package com.beben.tomasz.restaurant.user.api.view;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder")
public class UserDetailsView {

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private String restaurantReference;

    private Collection<RoleTypeView> roleTypes;
}
