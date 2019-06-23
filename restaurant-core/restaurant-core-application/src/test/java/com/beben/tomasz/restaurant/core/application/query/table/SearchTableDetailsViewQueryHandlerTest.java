package com.beben.tomasz.restaurant.core.application.query.table;

import com.beben.tomasz.restaurant.core.api.query.tables.SearchTableDetailsViewQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.application.AssertUtils;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantTableViewConverter;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantTableFactory;
import com.beben.tomasz.restaurant.core.application.query.tables.SearchTableDetailsViewQueryHandler;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchTableDetailsViewQueryHandlerTest {

    @Mock
    private RestaurantTableRepository restaurantTableRepository;

    @Spy
    private ToRestaurantTableViewConverter toRestaurantTableViewConverter;

    @InjectMocks
    private SearchTableDetailsViewQueryHandler searchTableDetailsViewQueryHandler;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindTableById() {

        //given
        SearchTableDetailsViewQuery tableDetailsViewQuery = SearchTableDetailsViewQuery.of(
                TestRestaurantTableFactory.TEST_ID
        );

        RestaurantTable restaurantTable = new TestRestaurantTableFactory();

        //when
        when(restaurantTableRepository.find(TestRestaurantTableFactory.TEST_ID))
                .thenReturn(restaurantTable);

        RestaurantTableView tableView = searchTableDetailsViewQueryHandler.handle(tableDetailsViewQuery);

        //then
        verify(restaurantTableRepository).find(TestRestaurantTableFactory.TEST_ID);
        verifyNoMoreInteractions(restaurantTableRepository);

        AssertUtils.assertRestaurantTableView(tableView, restaurantTable);
    }
}
