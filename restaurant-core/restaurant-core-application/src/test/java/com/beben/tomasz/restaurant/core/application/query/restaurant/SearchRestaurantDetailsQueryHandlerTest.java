package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantDetailsQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.application.AssertUtils;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantFactory;
import com.beben.tomasz.restaurant.core.application.query.restaurant.SearchRestaurantDetailsQueryHandler;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SearchRestaurantDetailsQueryHandlerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Spy
    private ToRestaurantViewConverter toRestaurantViewConverter;

    @InjectMocks
    private SearchRestaurantDetailsQueryHandler searchRestaurantDetailsQueryHandler;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindRestaurantDetails() throws Exception {

        //given
        SearchRestaurantDetailsQuery searchRestaurantDetailsQuery = SearchRestaurantDetailsQuery.of(
                TestRestaurantFactory.TEST_RESTAURANT_ID
        );

        Restaurant restaurant = new TestRestaurantFactory();

        //when
        when(restaurantRepository.findById(TestRestaurantFactory.TEST_RESTAURANT_ID))
                .thenReturn(restaurant);

        RestaurantView restaurantView = searchRestaurantDetailsQueryHandler.handle(searchRestaurantDetailsQuery);

        //then
        verify(restaurantRepository).findById(TestRestaurantFactory.TEST_RESTAURANT_ID);
        verifyNoMoreInteractions(restaurantRepository);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }
}
