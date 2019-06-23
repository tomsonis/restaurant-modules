package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.restaurant.user.api.query.RestaurantUserQuery;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import com.beben.tomasz.restaurant.user.application.converters.ToRestaurantUserViewConverter;
import com.beben.tomasz.restaurant.user.application.model.TestApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import io.vavr.control.Option;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.beben.tomasz.restaurant.user.application.model.TestApplicationUser.TEST_USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class RestaurantUserQueryHandlerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContextHolder contextHolder;

    @Spy
    private ToRestaurantUserViewConverter toRestaurantUserViewConverter;

    @InjectMocks
    private RestaurantUserQueryHandler restaurantUserQueryHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() {

        //given
        RestaurantUserQuery restaurantUserQuery = RestaurantUserQuery.of();
        TestApplicationUser testApplicationUser = new TestApplicationUser();

        //when
        when(contextHolder.getContext())
                .thenReturn(UserContext.of(Option.of(TEST_USER_ID)));
        when(userRepository.findByUserId(TEST_USER_ID))
                .thenReturn(Option.of(testApplicationUser));

        Option<RestaurantUserView> restaurantUserViewOption = restaurantUserQueryHandler.handle(restaurantUserQuery);

        //then
        assertThat(restaurantUserViewOption).isNotNull();

        RestaurantUserView restaurantUserView = restaurantUserViewOption.get();
        assertThat(restaurantUserView).isNotNull();

        verify(userRepository).findByUserId(TEST_USER_ID);
        verifyNoMoreInteractions(userRepository);

        assertThat(restaurantUserView.getRestaurantReference()).isEqualTo(testApplicationUser.getRestaurantUser().getRestaurantReference());
    }
}