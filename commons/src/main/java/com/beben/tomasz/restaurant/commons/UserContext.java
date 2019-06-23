package com.beben.tomasz.restaurant.commons;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor(staticName = "of")
public class UserContext implements Context, Serializable {

    private Option<String> userId;

    @Override
    public Option<String> getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = Option.of(userId);
    }
}
