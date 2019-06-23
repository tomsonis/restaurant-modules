package com.beben.tomasz.restaurant.orders.application.command.prepare;

import com.beben.tomasz.restaurant.orders.api.command.PrepareOrderCommand;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import io.vavr.control.Option;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PrepareOrderCommandHandlerTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderEvent orderEvent;

    @Captor
    private ArgumentCaptor<RestaurantOrder> confirmOrderArgumentCaptor;

    @Captor
    private ArgumentCaptor<RestaurantOrder> saveDeleteOrderArgumentCaptor;

    @InjectMocks
    private PrepareOrderCommandHandler prepareOrderCommandHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPrepareOrder() {

        //given
        PrepareOrderCommand confirmOrderCommand = PrepareOrderCommand.of(
                RestaurantOrderFactoryTest.TEST_ORDER_ID
        );
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();

        //when
        when(ordersRepository.readOrderToPrepare(RestaurantOrderFactoryTest.TEST_ORDER_ID))
                .thenReturn(testOrder);

        when(ordersRepository.save(testOrder.get()))
                .thenReturn(Option.of(testOrder.get().prepare()));

        prepareOrderCommandHandler.handle(confirmOrderCommand);

        verify(ordersRepository).readOrderToPrepare(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        verify(ordersRepository).save(saveDeleteOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(ordersRepository);

        verify(orderEvent).emmit(confirmOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(orderEvent);

        RestaurantOrder prepareOrder = saveDeleteOrderArgumentCaptor.getValue();
        assertThat(prepareOrder.getOrderStatus()).isEqualTo(OrderStatus.PREPARING);
    }
}