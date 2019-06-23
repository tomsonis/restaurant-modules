package com.beben.tomasz.restaurant.orders.infrastructure.spring.event;

import com.beben.tomasz.restaurant.commons.mail.EmailService;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
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
    public void emmit(Order order) {
        sendNotification(order, NEW_ORDER_URL);

        if (isNewOrder(order)) {
            sendEmail(order);
        }
    }

    private void sendNotification(Order order, String s) {
        OrderDetailsView detailsView = toOrderDetailsViewConverter.convert(order);
        simpMessagingTemplate.convertAndSend(ORDER_STATUS_CHANGE_URL, detailsView);
    }

    private boolean isNewOrder(Order order) {
        return OrderStatus.CREATED.equals(order.getOrderStatus());
    }

    private void sendEmail(Order order) {
        String orderCreatedMailTemplate = OrderCreatedMailTemplate.getOrderCreatedMailTemplate(order);
        String subject = "Zamówienie #" + order.getOrderId() + " zostało utworzone. ";

        emailService.send(order.getClientData().getEmail(), subject, orderCreatedMailTemplate);
    }
}
