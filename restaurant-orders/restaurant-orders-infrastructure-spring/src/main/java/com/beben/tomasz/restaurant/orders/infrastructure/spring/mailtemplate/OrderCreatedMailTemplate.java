package com.beben.tomasz.restaurant.orders.infrastructure.spring.mailtemplate;

import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;

public class OrderCreatedMailTemplate {

    public static String getOrderCreatedMailTemplate(Order newOrder) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>")
                .append("<h2>Zamówienie zostało utworzone</h2>")
                .append("<p>Dane: </p>");

        stringBuilder.append("<p> Imie i nazwisko: ")
                .append(newOrder.getClientData().getName())
                .append(" ")
                .append(newOrder.getClientData().getSurname())
                .append("</p>");

        stringBuilder.append("<p>Email: ")
                .append(newOrder.getClientData().getEmail())
                .append("</p>");

        stringBuilder.append("<p> Nr. telefonu: ")
                .append(newOrder.getClientData().getPhoneNumber())
                .append("</p>");

        stringBuilder.append("<p>Zawartość zamówienia</p>");

        for (OrderItem orderItemEntity : newOrder.getOrderItemEntities()) {
            stringBuilder.append("<b>")
                    .append(orderItemEntity.getName())
                    .append(" x ")
                    .append(orderItemEntity.getQuantity())
                    .append(" ")
                    .append(orderItemEntity.totalPrice())
                    .append("</b>");
        }

        stringBuilder.append("<p>Suma: ")
                .append(newOrder.getTotalAmount())
                .append("</p>");

        stringBuilder.append("<p>Podgląd zamówienia: ");
        stringBuilder.append("<a href=\"http://localhost:4200/order/details/")
                .append(newOrder.getOrderId())
                .append("\">")
                .append("link</a></p>");

        stringBuilder.append("</body></html>");

        return stringBuilder.toString();
    }
}
