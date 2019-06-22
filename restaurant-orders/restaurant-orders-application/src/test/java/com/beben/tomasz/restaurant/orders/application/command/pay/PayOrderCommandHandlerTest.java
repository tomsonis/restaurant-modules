package com.beben.tomasz.restaurant.orders.application.command.pay;

import com.beben.tomasz.restaurant.orders.api.command.PayOrderCommand;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.application.model.TestOrderPayment;
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

public class PayOrderCommandHandlerTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderEvent orderEvent;

    @Captor
    private ArgumentCaptor<RestaurantOrder> confirmOrderArgumentCaptor;

    @Captor
    private ArgumentCaptor<RestaurantOrder> saveDeleteOrderArgumentCaptor;

    @InjectMocks
    private PayOrderCommandHandler payOrderCommandHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPayOrder() {

        //given
        PayOrderCommand confirmOrderCommand = PayOrderCommand.of(
                RestaurantOrderFactoryTest.TEST_ORDER_ID,
                Option.of(TestOrderPayment.TEST_PAYMENT_REFERENCE_NUMBER)
        );
        OrderId orderId = OrderId.of(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();

        //when
        when(ordersRepository.readOrderToPay(orderId))
                .thenReturn(testOrder);

        payOrderCommandHandler.handle(confirmOrderCommand);

        verify(ordersRepository).readOrderToPay(orderId);
        verify(ordersRepository).save(saveDeleteOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(ordersRepository);

        verify(orderEvent).emmit(confirmOrderArgumentCaptor.capture());
        verifyNoMoreInteractions(orderEvent);

        RestaurantOrder payOrder = saveDeleteOrderArgumentCaptor.getValue();
        assertThat(payOrder.getOrderStatus()).isEqualTo(OrderStatus.PAID);
    }
}