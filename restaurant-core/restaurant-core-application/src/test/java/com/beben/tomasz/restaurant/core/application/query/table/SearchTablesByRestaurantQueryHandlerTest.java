package com.beben.tomasz.restaurant.core.application.query.table;

import com.beben.tomasz.restaurant.core.api.query.tables.SearchTablesByRestaurantQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.application.AssertUtils;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantTableViewConverter;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantFactory;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantTableFactory;
import com.beben.tomasz.restaurant.core.application.query.tables.SearchTablesByRestaurantQueryHandler;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchTablesByRestaurantQueryHandlerTest {

    @Mock
    private RestaurantTableRepository restaurantTableRepository;

    @Spy
    private ToRestaurantTableViewConverter toRestaurantTableViewConverter;

    @InjectMocks
    private SearchTablesByRestaurantQueryHandler searchTablesByRestaurantQueryHandler;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindTablesByRestaurantId() {

        //given
        SearchTablesByRestaurantQuery tablesByRestaurantQuery = SearchTablesByRestaurantQuery.of(
                TestRestaurantFactory.TEST_RESTAURANT_ID
        );

        RestaurantTable restaurantTable = new TestRestaurantTableFactory();

        //when
        when(restaurantTableRepository.findByRestaurantAndDistinctCapacity(TestRestaurantFactory.TEST_RESTAURANT_ID))
                .thenReturn(Collections.singletonList(restaurantTable));

        List<RestaurantTableView> restaurantTableViews = searchTablesByRestaurantQueryHandler.handle(tablesByRestaurantQuery);

        //then
        verify(restaurantTableRepository).findByRestaurantAndDistinctCapacity(TestRestaurantFactory.TEST_RESTAURANT_ID);
        verifyNoMoreInteractions(restaurantTableRepository);

        assertThat(restaurantTableViews).isNotNull();
        assertThat(restaurantTableViews).hasSizeGreaterThan(0);

        RestaurantTableView tableView = restaurantTableViews.get(0);

        AssertUtils.assertRestaurantTableView(tableView, restaurantTable);

    }
}
