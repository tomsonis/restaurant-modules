package com.beben.tomasz.restaurant.orders.infrastructure.spring.configuration;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.orders.application.command.confirm.ConfirmOrderCommandHandler;
import com.beben.tomasz.restaurant.orders.application.command.create.CreateOrderCommandHandler;
import com.beben.tomasz.restaurant.orders.application.command.delete.DeleteOrderCommandHandler;
import com.beben.tomasz.restaurant.orders.application.command.finish.FinishOrderCommandHandler;
import com.beben.tomasz.restaurant.orders.application.command.given.GivenOrderStatusCommandHandler;
import com.beben.tomasz.restaurant.orders.application.command.pay.PayOrderCommandHandler;
import com.beben.tomasz.restaurant.orders.application.command.prepare.PrepareOrderCommandHandler;
import com.beben.tomasz.restaurant.orders.domain.order.OrderFactory;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrdersCommandHandlersConfiguration {

    @Bean
    CreateOrderCommandHandler createOrderCommand(
            OrdersRepository ordersRepository,
            OrderFactory orderFactory,
            ContextHolder contextHolder,
            OrderEvent orderEvent,
            QueryExecutor queryExecutor
    ) {
        return new CreateOrderCommandHandler(
                ordersRepository,
                orderFactory,
                contextHolder,
                orderEvent,
                queryExecutor
        );
    }

    @Bean
    DeleteOrderCommandHandler deleteOrderCommandHandler(
            OrdersRepository ordersRepository,
            OrderEvent orderEvent
    ) {
        return new DeleteOrderCommandHandler(
                ordersRepository,
                orderEvent
        );
    }

    @Bean
    FinishOrderCommandHandler finishOrderCommandHandler(
            OrdersRepository ordersRepository,
            OrderEvent orderEvent
    ) {
        return new FinishOrderCommandHandler(
                ordersRepository,
                orderEvent
        );
    }

    @Bean
    PayOrderCommandHandler payOrderCommandHandler(
            OrdersRepository ordersRepository,
            OrderEvent orderEvent
    ) {
        return new PayOrderCommandHandler(
                ordersRepository,
                orderEvent
        );
    }

    @Bean
    PrepareOrderCommandHandler prepareOrderCommandHandler(
            OrdersRepository ordersRepository,
            OrderEvent orderEvent
    ) {
        return new PrepareOrderCommandHandler(
                ordersRepository,
                orderEvent
        );
    }

    @Bean
    ConfirmOrderCommandHandler confirmOrderCommandHandler(
            OrdersRepository ordersRepository,
            OrderEvent orderEvent
    ) {
        return new ConfirmOrderCommandHandler(
                ordersRepository,
                orderEvent
        );
    }

    @Bean
    GivenOrderStatusCommandHandler givenOrderCommandHandler(
            OrdersRepository ordersRepository,
            OrderEvent orderEvent
    ) {
        return new GivenOrderStatusCommandHandler(
                ordersRepository,
                orderEvent
        );
    }
}
