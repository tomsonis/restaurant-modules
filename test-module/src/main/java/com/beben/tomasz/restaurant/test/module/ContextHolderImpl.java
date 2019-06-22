package com.beben.tomasz.restaurant.test.module;

import com.beben.tomasz.restaurant.commons.Context;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.UserContext;
import io.vavr.control.Option;

public class ContextHolderImpl implements ContextHolder {

    public static final String TEST_USER_ID = "TEST_USER_ID";

    @Override
    public Context getContext() {
        return UserContext.of(Option.of(TEST_USER_ID));
    }

    @Override
    public void setContext(Context context) {
    }
}
