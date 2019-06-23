package com.beben.tomasz.restaurant.orders.application.command.finish;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.orders.api.command.FinishOrderCommand;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FinishOrderCommandHandler implements CommandHandler<FinishOrderCommand, Void> {

    private OrdersRepository ordersRepository;

    private OrderEvent orderEvent;

    @Override
    public Void handle(FinishOrderCommand finishOrderCommand) {
        ordersRepository.readOrderToFinish(finishOrderCommand.getOrderId())
                .map(RestaurantOrder::finish)
                .flatMap(ordersRepository::save)
                .onDefined(orderEvent::emmit);

        return null;
    }
}
