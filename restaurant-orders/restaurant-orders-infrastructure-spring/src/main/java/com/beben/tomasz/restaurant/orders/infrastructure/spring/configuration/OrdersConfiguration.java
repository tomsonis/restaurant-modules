package com.beben.tomasz.restaurant.orders.infrastructure.spring.configuration;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.commons.mail.EmailService;
import com.beben.tomasz.restaurant.orders.application.converter.OrderItemConverter;
import com.beben.tomasz.restaurant.orders.application.converter.ToClientViewConverter;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderItemViewConverter;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.OrderFactory;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.event.OrderEventHandler;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.read.JpaReadOrdersRepository;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.DefaultOrderFactory;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.OrdersJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.persistence.EntityManager;

@Configuration
@Import({
        OrdersCommandHandlersConfiguration.class,
        OrdersQueryHandlersConfiguration.class,
        OrdersHttpEndpointsConfiguration.class
})
public class OrdersConfiguration {


    @Bean
    OrderFactory orderFactory(
            OrdersRepository ordersRepository
    ) {
        return new DefaultOrderFactory(
                ordersRepository
        );
    }

    @Bean
    OrdersRepository ordersRepository(
            EntityManager entityManager
    ) {
        return new OrdersJpaRepository(
                entityManager
        );
    }

    @Bean
    OrderItemConverter orderItemConverter() {
        return new OrderItemConverter();
    }

    @Bean
    OrderReadRepository orderReadRepository() {
        return new JpaReadOrdersRepository();
    }


    @Bean
    OrderEventHandler confirmedOrderEventHandler(
            SimpMessagingTemplate simpMessagingTemplate,
            ToOrderDetailsViewConverter toOrderDetailsViewConverter,
            EmailService emailService
    ) {
        return new OrderEventHandler(
                simpMessagingTemplate,
                toOrderDetailsViewConverter,
                emailService
        );
    }

    @Bean
    ToOrderDetailsViewConverter toOrderDetailsViewConverter(
            QueryExecutor queryExecutor,
            ToOrderItemViewConverter toOrderItemViewConverter,
            ToClientViewConverter toClientViewConverter
    ) {
        return new ToOrderDetailsViewConverter(
                queryExecutor,
                toOrderItemViewConverter,
                toClientViewConverter
        );
    }

    @Bean
    ToClientViewConverter toClientViewConverter() {
        return new ToClientViewConverter();
    }

    @Bean
    ToOrderItemViewConverter toOrderItemViewConverter() {
        return new ToOrderItemViewConverter();
    }
}
