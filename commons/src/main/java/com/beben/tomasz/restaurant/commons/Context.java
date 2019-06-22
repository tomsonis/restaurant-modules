package com.beben.tomasz.restaurant.commons;

import io.vavr.control.Option;

public interface Context {

    Option<String> getUserId();

    void setUserId(String userId);
}
