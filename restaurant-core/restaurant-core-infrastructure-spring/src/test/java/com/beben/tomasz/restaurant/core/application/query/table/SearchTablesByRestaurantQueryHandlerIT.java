package com.beben.tomasz.restaurant.core.application.query.table;

import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.query.tables.SearchTablesByRestaurantQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.restaurant.core.utils.AssertUtils;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTablesByRestaurantQueryHandlerIT extends BaseCoreIntegrationTest {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @Test
    public void shouldFindTablesByRestaurantId() throws Exception {

        RestaurantTable restaurantTable = restaurantDatabase.saveRestaurantTableEntity();

        //given
        SearchTablesByRestaurantQuery tablesByRestaurantQuery = SearchTablesByRestaurantQuery.of(
                restaurantTable.getRestaurantReference()
        );

        //when
        List<RestaurantTableView> restaurantTableViews = queryExecutor.execute(tablesByRestaurantQuery);

        //then
        assertThat(restaurantTableViews).isNotNull();
        assertThat(restaurantTableViews).hasSizeGreaterThan(0);

        RestaurantTableView tableView = restaurantTableViews.get(0);

        AssertUtils.assertRestaurantTableView(tableView, restaurantTable);

    }
}
