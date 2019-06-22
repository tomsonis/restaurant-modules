package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantsQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.application.AssertUtils;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantFactory;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SearchRestaurantsQueryHandlerTest {

    private static final int SIZE = 10;
    private static final int PAGE = 1;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Spy
    private ToRestaurantViewConverter toRestaurantViewConverter;

    @InjectMocks
    private SearchRestaurantsQueryHandler searchRestaurantsQueryHandler;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldfindRestaurantViews() {

        //given
        SearchRestaurantsQuery searchRestaurantsQuery = SearchRestaurantsQuery.of(
                PAGE, SIZE
        );

        Restaurant restaurant = new TestRestaurantFactory();

        //when
        when(restaurantRepository.findAll(PAGE, SIZE)).thenReturn(Collections.singletonList(restaurant));

        List<RestaurantView> restaurantViews = searchRestaurantsQueryHandler.handle(searchRestaurantsQuery);

        //then
        verify(restaurantRepository).findAll(PAGE, SIZE);
        verifyNoMoreInteractions(restaurantRepository);

        assertThat(restaurantViews).isNotNull();
        assertThat(restaurantViews).hasSizeGreaterThan(0);

        RestaurantView restaurantView = restaurantViews.get(0);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }

}
