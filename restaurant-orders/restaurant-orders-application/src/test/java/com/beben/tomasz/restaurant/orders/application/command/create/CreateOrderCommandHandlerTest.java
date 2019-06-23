package com.beben.tomasz.restaurant.orders.application.command.create;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.application.model.TestOrderPayment;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderClient;
import com.beben.tomasz.restaurant.orders.domain.order.OrderFactory;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.TableId;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import io.vavr.control.Option;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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
        Order testOrder = RestaurantOrderFactoryTest.createOrder().get();

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

        when(contextHolder.getContext()).thenReturn(UserContext.of(Option.of(RestaurantOrderFactoryTest.TEST_USER_REFERENCE.getId())));
        when(orderFactory.createOrder(
                any(UserId.class),
                any(RestaurantId.class),
                any(TableId.class),
                anySetOf(OrderItem.class),
                any(PaymentType.class),
                any(OrderClient.class),
                any(LocalTime.class))
        ).thenReturn(testOrder);
        when(ordersRepository.save(any(Order.class)))
                .thenReturn(Option.of(testOrder));

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