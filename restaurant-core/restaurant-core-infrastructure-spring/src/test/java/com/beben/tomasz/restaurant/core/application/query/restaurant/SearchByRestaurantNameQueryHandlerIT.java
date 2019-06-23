package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchByRestaurantNameQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.restaurant.core.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchByRestaurantNameQueryHandlerIT extends BaseCoreIntegrationTest {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @AfterMethod
    public void tearDown() {
        restaurantDatabase.clearRestaurantEntity();
    }

    @Test
    public void shouldFindRestaurantByName() throws Exception {
        Restaurant restaurant = restaurantDatabase.saveRestaurantEntity();

        //given
        SearchByRestaurantNameQuery restaurantQuery = SearchByRestaurantNameQuery.of(
                restaurant.getName(),
                1,
                10
        );

        //when
        List<RestaurantView> restaurantViews = queryExecutor.execute(restaurantQuery);

        //then
        assertThat(restaurantViews).isNotNull();
        assertThat(restaurantViews).hasSizeGreaterThan(0);

        RestaurantView restaurantView = restaurantViews.get(0);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }

}
