package com.beben.tomasz.restaurant.orders.application.command.finish;

import com.beben.tomasz.restaurant.orders.api.command.FinishOrderCommand;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import io.vavr.control.Option;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FinishOrderCommandHandlerTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderEvent orderEvent;

    @Captor
    private ArgumentCaptor<RestaurantOrder> confirmOrderArgumentCaptor;

    @Captor
    private ArgumentCaptor<RestaurantOrder> saveDeleteOrderArgumentCaptor;

    @InjectMocks
    private FinishOrderCommandHandler finishOrderCommandHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFinishOrder() {

        //given
        FinishOrderCommand confirmOrderCommand = FinishOrderCommand.of(
                RestaurantOrderFactoryTest.TEST_ORDER_ID
        );
        OrderId orderId = OrderId.of(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();

        //when
        when(ordersRepository.readOrderToFinish(orderId))
                .thenReturn(testOrder);

        finishOrderCommandHandler.handle(confirmOrderCommand);

        verify(ordersRepository).readOrderToFinish(orderId);
        verify(ordersRepository).save(saveDeleteOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(ordersRepository);

        verify(orderEvent).emmit(confirmOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(orderEvent);

        RestaurantOrder finishOrder = saveDeleteOrderArgumentCaptor.getValue();
        assertThat(finishOrder.getOrderStatus()).isEqualTo(OrderStatus.DONE);
    }
}