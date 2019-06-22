package com.beben.tomasz.restaurant.user.domain;

public interface AuthenticateUser extends ApplicationUser {

    void authenticate(String token);
}
