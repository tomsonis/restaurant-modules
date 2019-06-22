package com.beben.tomasz.restaurant.orders.application.command.confirm;

import com.beben.tomasz.restaurant.orders.api.command.ConfirmOrderCommand;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.domain.order.*;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import io.vavr.control.Option;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ConfirmOrderCommandHandlerTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderEvent orderEvent;

    @Captor
    private ArgumentCaptor<Order> confirmOrderArgumentCaptor;

    @Captor
    private ArgumentCaptor<RestaurantOrder> saveConfirmOrderArgumentCaptor;

    @InjectMocks
    private ConfirmOrderCommandHandler confirmOrderCommandHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConfirmOrder() {

        //given
        ConfirmOrderCommand confirmOrderCommand = ConfirmOrderCommand.of(
                RestaurantOrderFactoryTest.TEST_ORDER_ID
        );
        OrderId orderId = OrderId.of(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        Option<RestaurantOrder> restaurantOrder = RestaurantOrderFactoryTest.createOrder().map(order -> order);
        Option<Order> testOrder = RestaurantOrderFactoryTest.createOrder().map(order -> order);

        //when
        when(ordersRepository.readOrderToConfirm(orderId))
                .thenReturn(restaurantOrder);
        when(ordersRepository.save(any(Order.class)))
                .thenReturn(testOrder);

        confirmOrderCommandHandler.handle(confirmOrderCommand);

        verify(ordersRepository).readOrderToConfirm(orderId);
        verify(ordersRepository).save(saveConfirmOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(ordersRepository);

        verify(orderEvent).emmit(confirmOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(orderEvent);

        RestaurantOrder confirmOrder = saveConfirmOrderArgumentCaptor.getValue();
        assertThat(confirmOrder.getOrderStatus()).isEqualTo(OrderStatus.CONFIRMED);
    }
}