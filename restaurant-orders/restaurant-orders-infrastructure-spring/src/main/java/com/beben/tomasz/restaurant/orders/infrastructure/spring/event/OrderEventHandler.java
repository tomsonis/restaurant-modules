package com.beben.tomasz.restaurant.orders.infrastructure.spring.event;

import com.beben.tomasz.restaurant.commons.mail.EmailService;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.mailtemplate.OrderCreatedMailTemplate;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@AllArgsConstructor
public class OrderEventHandler implements OrderEvent {

    private static final String ORDER_STATUS_CHANGE_URL = "/order-status-change";
    private static final String NEW_ORDER_URL = "/new-order";

    private SimpMessagingTemplate simpMessagingTemplate;

    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    private EmailService emailService;

    @Override
    public void emmit(Order newOrder) {
        sendNotification(newOrder, NEW_ORDER_URL);

        String orderCreatedMailTemplate = OrderCreatedMailTemplate.getOrderCreatedMailTemplate(newOrder);
        String subject = "Zamówienie #" + newOrder.getId() + " zostało utworzone. ";

        emailService.send(newOrder.getClientData().getEmail(), subject, orderCreatedMailTemplate);
    }

    @Override
    public void emmit(RestaurantOrder confirmOrder) {
        sendNotification(confirmOrder, ORDER_STATUS_CHANGE_URL);
    }

    private void sendNotification(Order order, String s) {
        OrderDetailsView detailsView = toOrderDetailsViewConverter.convert(order);
        simpMessagingTemplate.convertAndSend(s, detailsView);
    }
}
