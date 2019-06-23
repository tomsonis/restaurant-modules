package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.command.AddTableCommand;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.TableId;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddTableCommandHandlerIT extends BaseCoreIntegrationTest {

    private static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_DESC = "TEST_DESC";
    private static final String TEST_POS = "TEST_POS";
    private static final int TEST_CAPACITY = 1;


    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @AfterMethod
    public void tearDown() {
        restaurantDatabase.clearRestaurantTableEntity();
        restaurantDatabase.clearRestaurantEntity();
    }

    @Test
    public void shouldAddRestaurant() throws Exception {
        //given
        Restaurant restaurant = restaurantDatabase.saveRestaurantEntity();

        AddTableCommand addTableCommand = AddTableCommand.of(
                TEST_NAME,
                TEST_POS,
                TEST_DESC,
                restaurant.getRestaurantId(),
                TEST_CAPACITY
        );

        TableId tableId = commandExecutor.execute(addTableCommand);

        assertThat(tableId).isNotNull();
        assertThat(tableId.getId()).isNotBlank();

        RestaurantTable restaurantTable = restaurantDatabase.findTableById(tableId);

        assertThat(restaurantTable).isNotNull();
        assertThat(restaurantTable.getTableId()).isEqualTo(tableId);
        assertThat(restaurantTable.getName()).isEqualTo(TEST_NAME);
        assertThat(restaurantTable.getPosition()).isEqualTo(TEST_POS);
        assertThat(restaurantTable.getCapacity()).isEqualTo(TEST_CAPACITY);
        assertThat(restaurantTable.getRestaurantReference()).isEqualTo(restaurant.getRestaurantId());
    }

}