package com.beben.tomasz.restaurant.orders.application.command.given;

import com.beben.tomasz.restaurant.orders.api.command.GivenOrderStatusCommand;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class GivenOrderStatusCommandHandlerTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderEvent orderEvent;

    @Captor
    private ArgumentCaptor<RestaurantOrder> confirmOrderArgumentCaptor;

    @Captor
    private ArgumentCaptor<RestaurantOrder> saveDeleteOrderArgumentCaptor;

    @InjectMocks
    private GivenOrderStatusCommandHandler giveOrderCommandHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGiveOrder() {

        //given
        GivenOrderStatusCommand confirmOrderCommand = GivenOrderStatusCommand.of(
                RestaurantOrderFactoryTest.TEST_ORDER_ID
        );
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();

        //when
        when(ordersRepository.readOrderToGive(RestaurantOrderFactoryTest.TEST_ORDER_ID))
                .thenReturn(testOrder);

        when(ordersRepository.save(any(RestaurantOrder.class)))
                .thenReturn(Option.of(testOrder.get().marksAsGiven()));


        giveOrderCommandHandler.handle(confirmOrderCommand);

        verify(ordersRepository).readOrderToGive(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        verify(ordersRepository).save(saveDeleteOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(ordersRepository);

        verify(orderEvent).emmit(confirmOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(orderEvent);

        RestaurantOrder giveOrder = saveDeleteOrderArgumentCaptor.getValue();
        assertThat(giveOrder.getOrderStatus()).isEqualTo(OrderStatus.GIVEN);
    }
}