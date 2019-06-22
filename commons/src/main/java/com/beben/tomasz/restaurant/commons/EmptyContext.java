package com.beben.tomasz.restaurant.commons;


import io.vavr.control.Option;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "newInstance")
public class EmptyContext implements Context {

    @Override
    public Option<String> getUserId() {
        return Option.none();
    }

    @Override
    public void setUserId(String userId) {

    }
}
