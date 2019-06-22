package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchByRestaurantNameQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.application.AssertUtils;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantFactory;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SearchByRestaurantNameQueryHandlerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Spy
    private ToRestaurantViewConverter toRestaurantViewConverter;

    @InjectMocks
    private SearchByNameRestaurantQueryHandler searchByNameRestaurantQueryHandler;

    @Captor
    private ArgumentCaptor<List<RestaurantView>> restaurantsArgumentCaptor;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindRestaurantByName() {

        //given
        SearchByRestaurantNameQuery restaurantQuery = SearchByRestaurantNameQuery.of(
                TestRestaurantFactory.TEST_NAME,
                1,
                10
        );
        TestRestaurantFactory restaurant = new TestRestaurantFactory();

        //when
        when(restaurantRepository.findByName(TestRestaurantFactory.TEST_NAME)).thenReturn(
                Collections.singletonList(restaurant)
        );

        List<RestaurantView> restaurantViews = searchByNameRestaurantQueryHandler.handle(restaurantQuery);

        //then
        verify(restaurantRepository).findByName(TestRestaurantTableFactory.TEST_NAME);
        verifyNoMoreInteractions(restaurantRepository);

        assertThat(restaurantViews).isNotNull();
        assertThat(restaurantViews).hasSizeGreaterThan(0);

        RestaurantView restaurantView = restaurantViews.get(0);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }

}
