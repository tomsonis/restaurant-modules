package com.beben.tomasz.restaurant.core.application.query.table;

import com.beben.tomasz.restaurant.core.api.query.ExistRestaurantAndTableQuery;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantFactory;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantTableFactory;
import com.beben.tomasz.restaurant.core.application.query.tables.ExistRestaurantAndTableQueryHandler;
import com.beben.tomasz.restaurant.core.domain.RestaurantNotExistException;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ExistRestaurantAndTableQueryHandlerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantTableRepository restaurantTableRepository;

    @InjectMocks
    private ExistRestaurantAndTableQueryHandler existRestaurantAndTableQueryHandler;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCheckIfRestaurantAndTableExists() {

        //given
        ExistRestaurantAndTableQuery restaurantAndTableQuery = ExistRestaurantAndTableQuery.of(
                TestRestaurantFactory.TEST_RESTAURANT_ID,
                TestRestaurantTableFactory.TEST_ID
        );

        when(restaurantRepository.exists(TestRestaurantFactory.TEST_RESTAURANT_ID))
                .thenReturn(true);

        when(restaurantTableRepository.exists(TestRestaurantTableFactory.TEST_ID))
                .thenReturn(true);

        //when
        Boolean exists = existRestaurantAndTableQueryHandler.handle(restaurantAndTableQuery);

        //then
        assertThat(exists).isEqualTo(true);
    }
}
