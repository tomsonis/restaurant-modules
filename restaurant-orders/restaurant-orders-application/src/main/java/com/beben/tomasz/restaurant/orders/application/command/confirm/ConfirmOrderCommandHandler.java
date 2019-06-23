package com.beben.tomasz.restaurant.orders.application.command.confirm;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.orders.api.command.ConfirmOrderCommand;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmOrderCommandHandler implements CommandHandler<ConfirmOrderCommand, Void> {

    private OrdersRepository ordersRepository;

    private OrderEvent orderEvent;

    @Override
    public Void handle(ConfirmOrderCommand confirmOrderCommand) {
        ordersRepository.readOrderToConfirm(confirmOrderCommand.getOrderId())
                .map(RestaurantOrder::confirm)
                .flatMap(ordersRepository::save)
                .onDefined(orderEvent::emmit);

        return null;
    }
}
