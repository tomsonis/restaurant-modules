package com.beben.tomasz.restaurant.user.application.model;

import com.beben.tomasz.restaurant.user.domain.*;

import java.util.Collection;
import java.util.Collections;

public class TestApplicationUser implements ApplicationUser {

    public static final String TEST_USER_ID = "TEST_USER_ID";
    public static final String TEST_USERNAME = "TEST_USERNAME";
    public static final String TEST_PASSWORD = "TEST_PASSWORD";
    public static final String TEST_EMAIL = "TEST@EMAIL.COM";
    private static final String TEST_CLIENT_ID = "TEST_CLIENT_ID";
    public static final String TEST_CLIENT_NAME = "TEST_CLIENT_NAME";
    public static final String TEST_CLIENT_SURNAME = "TEST_CLIENT_SURNAME";
    public static final String TEST_CLIENT_PHONE_NUMBER = "TEST_CLIENT_PHONE_NUMBER";
    public static final String TEST_RESTAURANT_REF = "TEST_RESTAURANT_REF";

    @Override
    public UserId getUserId() {
        return UserId.of(TEST_USER_ID);
    }

    @Override
    public String getUsername() {
        return TEST_USERNAME;
    }

    @Override
    public String getPassword() {
        return TEST_PASSWORD;
    }

    @Override
    public String getEmail() {
        return TEST_EMAIL;
    }

    @Override
    public RestaurantClient getRestaurantClient() {
        return new RestaurantClient() {
            @Override
            public ClientId getClientId() {
                return ClientId.of(TEST_CLIENT_ID);
            }

            @Override
            public String getName() {
                return TEST_CLIENT_NAME;
            }

            @Override
            public String getSurname() {
                return TEST_CLIENT_SURNAME;
            }

            @Override
            public String getPhoneNumber() {
                return TEST_CLIENT_PHONE_NUMBER;
            }
        };
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public RestaurantUser getRestaurantUser() {
        return new RestaurantUser() {
            @Override
            public String getRestaurantReference() {
                return TEST_RESTAURANT_REF;
            }

            @Override
            public Collection<RestaurantRoleType> getRestaurantRoleTypes() {
                return Collections.emptyList();
            }
        };
    }
}
