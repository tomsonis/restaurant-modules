package com.beben.tomasz.restaurant.orders.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.orders.api.ClientView;
import com.beben.tomasz.restaurant.orders.domain.order.ClientData;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToClientViewConverter implements Converter<ClientData, ClientView> {

    @Override
    public ClientView convert(ClientData clientData) {
        return ClientView.of(
                clientData.getName(),
                clientData.getSurname(),
                clientData.getEmail(),
                clientData.getPhoneNumber()
        );
    }
}
