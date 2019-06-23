package com.beben.tomasz.restaurant.standalone.context;

import com.beben.tomasz.restaurant.commons.Context;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.EmptyContext;

import java.util.Objects;


public class ContextHolderImpl implements ContextHolder {

    private static final ThreadLocal<Context> contextThreadLocal = new ThreadLocal<>();

    @Override
    public Context getContext() {
        Context context = contextThreadLocal.get();
        if (Objects.isNull(context)) {
            context = EmptyContext.newInstance();
            contextThreadLocal.set(context);
        }
        return context;
    }

    @Override
    public void setContext(Context context) {
        contextThreadLocal.set(context);
    }
}
