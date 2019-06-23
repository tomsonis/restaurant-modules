package com.beben.tomasz.restaurant.orders.application.command.delete;

import com.beben.tomasz.restaurant.orders.api.command.DeleteOrderCommand;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
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

public class DeleteOrderCommandHandlerTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderEvent orderEvent;

    @Captor
    private ArgumentCaptor<Order> confirmOrderArgumentCaptor;

    @Captor
    private ArgumentCaptor<RestaurantOrder> saveDeleteOrderArgumentCaptor;

    @InjectMocks
    private DeleteOrderCommandHandler deleteOrderCommandHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDeleteOrder() {

        //given
        DeleteOrderCommand confirmOrderCommand = DeleteOrderCommand.of(
                RestaurantOrderFactoryTest.TEST_ORDER_ID
        );
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();

        //when
        when(ordersRepository.readOrderToDelete(RestaurantOrderFactoryTest.TEST_ORDER_ID))
                .thenReturn(testOrder);

        when(ordersRepository.save(any()))
                .thenReturn(Option.of(testOrder.get().delete()));


        deleteOrderCommandHandler.handle(confirmOrderCommand);

        verify(ordersRepository).readOrderToDelete(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        verify(ordersRepository).save(saveDeleteOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(ordersRepository);

        verify(orderEvent).emmit(confirmOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(orderEvent);

        RestaurantOrder deleteOrder = saveDeleteOrderArgumentCaptor.getValue();
        assertThat(deleteOrder.getOrderStatus()).isEqualTo(OrderStatus.DELETED);
    }
}