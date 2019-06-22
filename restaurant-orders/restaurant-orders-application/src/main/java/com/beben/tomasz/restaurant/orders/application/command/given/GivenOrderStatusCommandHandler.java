package com.beben.tomasz.restaurant.orders.application.command.given;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.orders.api.command.GivenOrderStatusCommand;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GivenOrderStatusCommandHandler implements CommandHandler<GivenOrderStatusCommand, Void> {

    private OrdersRepository ordersRepository;

    private OrderEvent orderEvent;

    @Override
    public Void handle(GivenOrderStatusCommand finishOrderCommand) {
        ordersRepository.readOrderToGive(
                OrderId.of(
                        finishOrderCommand.getOrderId()
                ))
                .map(RestaurantOrder::marksAsGiven)
                .flatMap(ordersRepository::save)
                .onDefined(orderEvent::emmit);

        return null;
    }
}
