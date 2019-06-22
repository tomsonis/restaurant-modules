package com.beben.tomasz.restaurant.orders.application.command.create;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.EmptyContext;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.application.model.TestOrderPayment;
import com.beben.tomasz.restaurant.orders.domain.order.*;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import io.vavr.control.Option;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class CreateOrderCommandHandlerTest {

    public static final String TEST_USER_ID = "TEST_USER_ID";
    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderFactory orderFactory;

    @Mock
    private ContextHolder contextHolder;

    @Mock
    private OrderEvent orderEvent;

    @Mock
    private QueryExecutor queryExecutor;

    @InjectMocks
    private CreateOrderCommandHandler createOrderCommandHandler;

    @Captor
    private ArgumentCaptor<RestaurantOrder> orderArgumentCaptor;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() throws Exception {

        //given
        CreateOrderCommand createOrderCommand = CreateOrderCommand.of(
                RestaurantOrderFactoryTest.TEST_RESTAURANT_REFERENCE,
                RestaurantOrderFactoryTest.TEST_TABLE_REFERENCE,
                RestaurantOrderFactoryTest.orderItems(),
                TestOrderPayment.TEST_PAYMENT_TYPE,
                OrderClient.of(
                        RestaurantOrderFactoryTest.TEST_CLIENT_NAME,
                        RestaurantOrderFactoryTest.TEST_CLIENT_SURNAME,
                        RestaurantOrderFactoryTest.TEST_CLIENT_EMAIL,
                        RestaurantOrderFactoryTest.TEST_CLIENT_PHONE_NUMBER
                ),
                RestaurantOrderFactoryTest.TEST_ARRIVAL_TIME
        );
        Option<Order> testOrder = RestaurantOrderFactoryTest.createOrder()
                .map(restaurantOrder -> restaurantOrder);

        // when
        when(queryExecutor.execute(any()))
//                .thenThrow(new IllegalArgumentException("Brak użytkownika o podanym ID"))
                .thenReturn(Boolean.TRUE)
                .thenReturn(Boolean.TRUE)
                .thenReturn(Collections.singletonList(ProductView.of(
                        RestaurantOrderFactoryTest.TEST_ORDER_ITEM_ID,
                        RestaurantOrderFactoryTest.TEST_ORDER_ITEM_NAME,
                        null,
                        BigDecimal.ZERO,
                        Collections.emptyList(),
                        Collections.emptyList()
                )));

        when(contextHolder.getContext()).thenReturn(UserContext.of(Option.of(RestaurantOrderFactoryTest.TEST_USER_REFERENCE)));
        when(orderFactory.createOrder(
                anyString(),
                anyString(),
                anyString(),
                anySetOf(OrderItem.class),
                any(PaymentType.class),
                any(OrderClient.class),
                any(LocalTime.class))
        ).thenReturn(testOrder);
        when(ordersRepository.save(any(Order.class)))
                .thenReturn(testOrder);

        Option<OrderId> orderId = createOrderCommandHandler.handle(createOrderCommand);

        // then
//        verify(orderFactory).createOrder(
//                RestaurantOrderFactoryTest.TEST_USER_REFERENCE,
//                RestaurantOrderFactoryTest.TEST_RESTAURANT_REFERENCE,
//                RestaurantOrderFactoryTest.TEST_TABLE_REFERENCE,
//                RestaurantOrderFactoryTest.orderItems(),
//                TestOrderPayment.TEST_PAYMENT_TYPE,
//                OrderClient.of(
//                        RestaurantOrderFactoryTest.TEST_CLIENT_NAME,
//                        RestaurantOrderFactoryTest.TEST_CLIENT_SURNAME,
//                        RestaurantOrderFactoryTest.TEST_CLIENT_EMAIL,
//                        RestaurantOrderFactoryTest.TEST_CLIENT_PHONE_NUMBER
//                ),
//                RestaurantOrderFactoryTest.TEST_ARRIVAL_TIME
//        );
//        verifyNoMoreInteractions(orderFactory);

        verify(ordersRepository).save(orderArgumentCaptor.capture());
        verifyNoMoreInteractions(ordersRepository);

        verify(orderEvent).emmit(any(Order.class));
        verifyNoMoreInteractions(orderEvent);

        RestaurantOrder newOrder = orderArgumentCaptor.getValue();
        assertThat(newOrder.getOrderStatus()).isEqualTo(OrderStatus.CREATED);
    }
}