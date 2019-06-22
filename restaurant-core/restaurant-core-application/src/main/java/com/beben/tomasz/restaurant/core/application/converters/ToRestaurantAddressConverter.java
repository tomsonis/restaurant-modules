package com.beben.tomasz.restaurant.core.application.converters;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.commons.view.AddressView;
import com.beben.tomasz.restaurant.core.domain.Address;

public class ToRestaurantAddressConverter implements Converter<AddressView, Address> {

    @Override
    public Address convert(AddressView addressView) {
        return Address.of(
                addressView.getStreet(),
                addressView.getPostalCode(),
                addressView.getCity(),
                addressView.getCountry()
        );
    }
}
