package com.beben.tomasz.restaurant.core.domain;

public class RestaurantNotExistException extends Exception {
    public RestaurantNotExistException() {
    }

    public RestaurantNotExistException(String message) {
        super(message);
    }

    public RestaurantNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
