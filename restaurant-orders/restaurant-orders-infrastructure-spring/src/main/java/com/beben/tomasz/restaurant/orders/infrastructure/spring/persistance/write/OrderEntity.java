package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write;

import com.beben.tomasz.restaurant.orders.domain.order.ClientData;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import com.beben.tomasz.restaurant.orders.domain.order.OrderPayment;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.TableId;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity implements Order {

    @Id
    private String id;

    private String userReference;

    private String restaurantReference;

    private String tableReference;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @Fetch(FetchMode.JOIN)
    private Set<OrderItemEntity> orderItemEntities;

    @Getter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Getter
    private BigDecimal totalAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "referenceNumber", column = @Column(name = "payment_reference_number")),
            @AttributeOverride(name = "paymentType", column = @Column(name = "payment_type"))
    })
    private OrderPaymentData orderPaymentData;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "client_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "client_surname")),
            @AttributeOverride(name = "email", column = @Column(name = "client_email")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "client_phoneNumber"))
    })
    private OrderClientData orderClientData;

    @Getter
    private LocalTime arrivalTime;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    public OrderEntity(
            String id,
            String userReference,
            String restaurantReference,
            String tableReference,
            Set<OrderItemEntity> orderItemEntities,
            OrderStatus orderStatus,
            PaymentType paymentType,
            BigDecimal totalAmount,
            OrderClientData orderClientData,
            LocalTime arrivalTime
    ) {
        this.id = id;
        this.userReference = userReference;
        this.restaurantReference = restaurantReference;
        this.tableReference = tableReference;
        this.orderItemEntities = orderItemEntities;
        this.orderStatus = orderStatus;
        this.orderPaymentData = new OrderPaymentData(paymentType);
        this.totalAmount = totalAmount;
        this.orderClientData = orderClientData;
        this.arrivalTime = arrivalTime;
    }

    private OrderEntity(
            String id,
            String userReference,
            String restaurantReference,
            String tableReference,
            Set<OrderItemEntity> orderItemEntities,
            PaymentType paymentType,
            BigDecimal totalAmount,
            OrderClientData orderClientData,
            LocalTime arrivalTime
    ) {
        this.id = id;
        this.userReference = userReference;
        this.restaurantReference = restaurantReference;
        this.tableReference = tableReference;
        this.orderItemEntities = orderItemEntities;
        this.orderStatus = OrderStatus.CREATED;
        this.orderPaymentData = new OrderPaymentData(paymentType);
        this.totalAmount = totalAmount;
        this.orderClientData = orderClientData;
        this.arrivalTime = arrivalTime;
    }

    public static OrderEntity of(
            String id,
            String userReference,
            String restaurantReference,
            String tableReference,
            Set<OrderItemEntity> orderItemEntities,
            PaymentType paymentType,
            BigDecimal totalAmount,
            OrderClientData orderClientData,
            LocalTime arrivalTime
    ) {
        return new OrderEntity(
                id,
                userReference,
                restaurantReference,
                tableReference,
                orderItemEntities,
                paymentType,
                totalAmount,
                orderClientData,
                arrivalTime
        );
    }

    @Override
    public OrderId getOrderId() {
        return OrderId.of(id);
    }

    @Override
    public UserId getUserReference() {
        return UserId.of(userReference);
    }

    @Override
    public RestaurantId getRestaurantReference() {
        return RestaurantId.of(restaurantReference);
    }

    @Override
    public TableId getTableReference() {
        return TableId.of(tableReference);
    }

    @Override
    public Set<OrderItem> getOrderItemEntities() {
        return orderItemEntities.stream()
                .map(orderItemEntity -> OrderItem.of(
                        String.valueOf(orderItemEntity.getId()),
                        orderItemEntity.getName(),
                        orderItemEntity.getPrice(),
                        orderItemEntity.getQuantity()
                ))
                .collect(Collectors.toSet());
    }

    @Override
    public OrderPayment getOrderPayment() {
        return orderPaymentData;
    }

    @Override
    public ClientData getClientData() {
        return orderClientData;
    }

    void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
