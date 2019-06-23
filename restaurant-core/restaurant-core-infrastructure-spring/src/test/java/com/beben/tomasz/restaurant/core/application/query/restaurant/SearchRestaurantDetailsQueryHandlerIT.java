package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantDetailsQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.restaurant.core.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchRestaurantDetailsQueryHandlerIT extends BaseCoreIntegrationTest {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @AfterMethod
    public void tearDown() {
        restaurantDatabase.clearRestaurantEntity();
    }

    @Test
    public void shouldFindRestaurantDetails() throws Exception {

        //given
        Restaurant restaurant = restaurantDatabase.saveRestaurantEntity();

        SearchRestaurantDetailsQuery searchRestaurantDetailsQuery = SearchRestaurantDetailsQuery.of(
                restaurant.getRestaurantId()
        );

        //when
        RestaurantView restaurantView = queryExecutor.execute(searchRestaurantDetailsQuery);

        //then
        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }
}
