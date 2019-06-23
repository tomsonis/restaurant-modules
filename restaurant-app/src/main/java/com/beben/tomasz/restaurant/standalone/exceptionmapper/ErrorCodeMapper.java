package com.beben.tomasz.restaurant.standalone.exceptionmapper;

import com.beben.tomasz.restaurant.orders.domain.OrderValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

class ErrorCodeMapper {

    private static Map<Class<? extends Exception>, String> EXCEPTION_MAPPERS = new HashMap<>();

    static {
        EXCEPTION_MAPPERS.put(IllegalArgumentException.class, "Zamówienie nie istnieje.");
        EXCEPTION_MAPPERS.put(OrderValidationException.class, "Bład podczas składania zamówienia");
    }


    static ErrorResult mapException(Exception exception) {
        String errorCode = EXCEPTION_MAPPERS.getOrDefault(exception.getClass(), StringUtils.EMPTY);
        if (errorCode != null) {
            return ErrorResult.error(errorCode, exception.getMessage());
        } else {
            return ErrorResult.error(exception.getClass().getSimpleName(), exception.getMessage());
        }
    }
}
