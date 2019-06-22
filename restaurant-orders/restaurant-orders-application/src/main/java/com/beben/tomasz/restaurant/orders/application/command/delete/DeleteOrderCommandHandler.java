package com.beben.tomasz.restaurant.orders.application.command.delete;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.orders.api.command.DeleteOrderCommand;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteOrderCommandHandler implements CommandHandler<DeleteOrderCommand, Void> {

    private OrdersRepository ordersRepository;

    private OrderEvent orderEvent;

    @Override
    public Void handle(DeleteOrderCommand deleteOrderCommand) {
        ordersRepository.readOrderToDelete(
                OrderId.of(
                        deleteOrderCommand.getOrderId()
                ))
                .map(RestaurantOrder::delete)
                .flatMap(ordersRepository::save)
                .onDefined(orderEvent::emmit);
        return null;
    }
}
