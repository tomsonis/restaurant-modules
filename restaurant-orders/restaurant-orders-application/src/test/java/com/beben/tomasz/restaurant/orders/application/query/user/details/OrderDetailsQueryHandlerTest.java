package com.beben.tomasz.restaurant.orders.application.query.user.details;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.model.RestaurantOrderFactoryTest;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import io.vavr.control.Option;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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
        OrderDetailsQuery orderDetailsQuery = OrderDetailsQuery.of(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        Option<RestaurantOrder> testOrder = RestaurantOrderFactoryTest.createOrder();
        Option<UserId> testUserReference = Option.of(RestaurantOrderFactoryTest.TEST_USER_REFERENCE);

        //when
        when(contextHolder.getContext()).thenReturn(UserContext.of(testUserReference.map(UserId::getId)));
        when(orderReadRepository.orderDetailsView(any()))
                .thenReturn(Option.of(testOrder.get()));

        orderDetailsQueryHandler.handle(orderDetailsQuery);

        //then
        verify(orderReadRepository).orderDetailsView(RestaurantOrderFactoryTest.TEST_ORDER_ID);
        verifyNoMoreInteractions(orderReadRepository);

        verify(toOrderDetailsViewConverter).convert(testOrder.get());
        verifyNoMoreInteractions(toOrderDetailsViewConverter);
    }
}