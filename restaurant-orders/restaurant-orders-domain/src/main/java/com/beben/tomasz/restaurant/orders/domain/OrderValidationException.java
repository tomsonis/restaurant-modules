package com.beben.tomasz.restaurant.orders.domain;

public class OrderValidationException extends Exception {
    public OrderValidationException() {
    }

    public OrderValidationException(String message) {
        super(message);
    }

    public OrderValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
