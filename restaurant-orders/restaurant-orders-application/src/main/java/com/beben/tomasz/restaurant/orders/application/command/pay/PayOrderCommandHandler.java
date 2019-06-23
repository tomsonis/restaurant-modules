package com.beben.tomasz.restaurant.orders.application.command.pay;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.orders.api.command.PayOrderCommand;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PayOrderCommandHandler implements CommandHandler<PayOrderCommand, Void> {

    private OrdersRepository ordersRepository;

    private OrderEvent orderEvent;

    @Override
    public Void handle(PayOrderCommand payOrderCommand) {
        ordersRepository.readOrderToPay(payOrderCommand.getOrderId())
                .map(restaurantOrder -> restaurantOrder.pay(
                                Option.of(payOrderCommand.getPaymentReferenceNumber())
                        )
                )
                .flatMap(ordersRepository::save)
                .onDefined(orderEvent::emmit);

        return null;
    }
}
