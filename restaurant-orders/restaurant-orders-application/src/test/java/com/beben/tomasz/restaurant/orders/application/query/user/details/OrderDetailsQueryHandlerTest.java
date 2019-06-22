package com.beben.tomasz.restaurant.orders.application.query.user.details;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import io.vavr.control.Option;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class OrderDetailsQueryHandlerTest {

    @Mock
    private OrderReadRepository orderReadRepository;

    @Mock
    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    @Mock
    private ContextHolder contextHolder;

    @InjectMocks
    private OrderDetailsQueryHandler orderDetailsQueryHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() {

        //given
        OrderId orderId = OrderId.of(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        OrderDetailsQuery orderDetailsQuery = OrderDetailsQuery.of(orderId);
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();
        Option<String> testUserReference = Option.of(RestaurantOrderFactoryTest.TEST_USER_REFERENCE);

        //when
        when(contextHolder.getContext()).thenReturn(UserContext.of(testUserReference));
        when(orderReadRepository.orderDetailsView(RestaurantOrderFactoryTest.TEST_ORDER_ID, testUserReference.get()))
                .thenReturn(Option.of(testOrder.get()));

        orderDetailsQueryHandler.handle(orderDetailsQuery);

        //then
        verify(orderReadRepository).orderDetailsView(RestaurantOrderFactoryTest.TEST_ORDER_ID, testUserReference.get());
        verifyNoMoreInteractions(orderReadRepository);

        verify(toOrderDetailsViewConverter).convert(testOrder.get());
        verifyNoMoreInteractions(toOrderDetailsViewConverter);
    }
}