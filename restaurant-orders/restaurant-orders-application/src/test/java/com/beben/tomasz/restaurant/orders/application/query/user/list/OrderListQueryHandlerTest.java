package com.beben.tomasz.restaurant.orders.application.query.user.list;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.query.user.OrderListQuery;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import io.vavr.control.Option;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderListQueryHandlerTest {

    private static final int PAGE = 1;
    private static final int SIZE = 10;

    @Mock
    private OrderReadRepository orderReadRepository;

    @Mock
    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    @Mock
    private ContextHolder contextHolder;

    @InjectMocks
    private OrderListQueryHandler orderListQueryHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() {
        //given
        OrderListQuery orderListQuery = OrderListQuery.of(PAGE, SIZE);
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();
        List<Order> orderList = Collections.singletonList(testOrder.get());
        Option<String> testUserReference = Option.of(RestaurantOrderFactoryTest.TEST_USER_REFERENCE);

        //when
        when(contextHolder.getContext()).thenReturn(UserContext.of(testUserReference));
        when(orderReadRepository.findOrdersByUser(testUserReference, PAGE, SIZE))
                .thenReturn(orderList);

        List<OrderDetailsView> handle = orderListQueryHandler.handle(orderListQuery);

        //then
        verify(orderReadRepository).findOrdersByUser(testUserReference, PAGE, SIZE);
        verifyNoMoreInteractions(orderReadRepository);

        verify(toOrderDetailsViewConverter).convert(orderList);
        verifyNoMoreInteractions(toOrderDetailsViewConverter);
    }
}