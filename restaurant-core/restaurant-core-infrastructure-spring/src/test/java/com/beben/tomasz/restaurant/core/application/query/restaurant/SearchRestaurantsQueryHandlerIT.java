package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantsQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.restaurant.core.utils.AssertUtils;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchRestaurantsQueryHandlerIT extends BaseCoreIntegrationTest {

    private static final int SIZE = 10;
    private static final int PAGE = 1;

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @AfterMethod
    public void tearDown() {
        restaurantDatabase.clearRestaurantEntity();
    }


    @Test
    public void shouldfindRestaurantViews() throws Exception {

        //given
        Restaurant restaurant = restaurantDatabase.saveRestaurantEntity();

        SearchRestaurantsQuery searchRestaurantsQuery = SearchRestaurantsQuery.of(
                PAGE, SIZE
        );

        //when
        List<RestaurantView> restaurantViews = queryExecutor.execute(searchRestaurantsQuery);

        //then

        assertThat(restaurantViews).isNotNull();
        assertThat(restaurantViews).hasSizeGreaterThan(0);

        RestaurantView restaurantView = restaurantViews.get(0);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }

}
