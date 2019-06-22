package com.beben.tomasz.restaurant.core.domain;

public interface Restaurant {

    String getId();

    String getName();

    String getDescription();

    Address getAddress();

    String getPhotoUrl();
}
