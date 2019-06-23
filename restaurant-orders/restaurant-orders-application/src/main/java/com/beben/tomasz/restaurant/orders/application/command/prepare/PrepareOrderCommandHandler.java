package com.beben.tomasz.restaurant.orders.application.command.prepare;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.orders.api.command.PrepareOrderCommand;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrepareOrderCommandHandler implements CommandHandler<PrepareOrderCommand, Void> {

    private OrdersRepository ordersRepository;

    private OrderEvent orderEvent;

    @Override
    public Void handle(PrepareOrderCommand prepareOrderCommand) {
        ordersRepository.readOrderToPrepare(prepareOrderCommand.getOrderId())
                .map(RestaurantOrder::prepare)
                .flatMap(ordersRepository::save)
                .onDefined(orderEvent::emmit);

        return null;
    }
}
