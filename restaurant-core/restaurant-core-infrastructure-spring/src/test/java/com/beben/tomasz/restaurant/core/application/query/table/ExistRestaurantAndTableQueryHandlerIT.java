package com.beben.tomasz.restaurant.core.application.query.table;

import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.query.ExistRestaurantAndTableQuery;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExistRestaurantAndTableQueryHandlerIT extends BaseCoreIntegrationTest {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @AfterMethod
    public void tearDown() {
        restaurantDatabase.clearRestaurantTableEntity();
        restaurantDatabase.clearRestaurantEntity();
    }

    @Test
    public void shouldCheckIfRestaurantAndTableExists() throws Exception {
        RestaurantTable restaurantTable = restaurantDatabase.saveRestaurantTableEntity();

        //given
        ExistRestaurantAndTableQuery restaurantAndTableQuery = ExistRestaurantAndTableQuery.of(
                restaurantTable.getRestaurantReference(),
                restaurantTable.getId()
        );

        //when
        Boolean exists = queryExecutor.execute(restaurantAndTableQuery);

        //then
        assertThat(exists).isEqualTo(true);
    }
}
