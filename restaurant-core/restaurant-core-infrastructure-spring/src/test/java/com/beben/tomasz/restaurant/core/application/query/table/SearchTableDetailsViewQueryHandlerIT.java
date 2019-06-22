package com.beben.tomasz.restaurant.core.application.query.table;

import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.query.tables.SearchTableDetailsViewQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.restaurant.core.utils.AssertUtils;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class SearchTableDetailsViewQueryHandlerIT extends BaseCoreIntegrationTest {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @Test
    public void shouldFindTableById() throws Exception {
        //given
        RestaurantTable restaurantTable = restaurantDatabase.saveRestaurantTableEntity();

        SearchTableDetailsViewQuery tableDetailsViewQuery = SearchTableDetailsViewQuery.of(
                restaurantTable.getId()
        );

        //when
        RestaurantTableView tableView = queryExecutor.execute(tableDetailsViewQuery);

        //then
        AssertUtils.assertRestaurantTableView(tableView, restaurantTable);
    }
}
