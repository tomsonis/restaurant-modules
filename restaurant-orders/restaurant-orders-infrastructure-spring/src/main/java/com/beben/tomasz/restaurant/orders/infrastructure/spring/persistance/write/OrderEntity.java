package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write;

import com.beben.tomasz.restaurant.orders.domain.order.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@Table
@Entity
@Getter
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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

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

    private LocalTime arrivalTime;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

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
}
