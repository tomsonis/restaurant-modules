package com.beben.tomasz.restaurant.products.application.model;

import com.beben.tomasz.restaurant.products.domain.UnitEnum;
import com.beben.tomasz.restaurant.products.domain.Volume;

public class TestVolume implements Volume {

    public static final String TEST_ID = "TEST_ID";
    private static final int TEST_CAPACITY = 250;

    @Override
    public String getId() {
        return TEST_ID;
    }

    @Override
    public int getCapacity() {
        return TEST_CAPACITY;
    }

    @Override
    public UnitEnum getUnitEnum() {
        return UnitEnum.G;
    }
}
