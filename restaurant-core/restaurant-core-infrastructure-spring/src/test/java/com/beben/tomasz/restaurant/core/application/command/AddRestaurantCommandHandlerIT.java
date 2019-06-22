package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.restaurant.commons.view.AddressView;
import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.command.AddRestaurantCommand;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AddRestaurantCommandHandlerIT extends BaseCoreIntegrationTest {

    private static final String TEST_STREET = "TEST_STREET";
    private static final String TEST_POSTAL_CODE = "TEST_POSTAL_CODE";
    private static final String TEST_CITY = "TEST_CITY";
    private static final String TEST_COUNTRY = "TEST_COUNTRY";
    private static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_DESC = "TEST_DESC";
    private static final String TEST_PHOTO = "TEST_PHOTO";

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @AfterMethod
    public void tearDown() {
        restaurantDatabase.clearRestaurantEntity();
    }

    @Test
    public void shouldAddRestaurant() throws Exception {
        //given
        AddRestaurantCommand restaurantCommand = AddRestaurantCommand.of(
                TEST_NAME,
                TEST_DESC,
                TEST_PHOTO,
                AddressView.of(
                        TEST_STREET,
                        TEST_POSTAL_CODE,
                        TEST_CITY,
                        TEST_COUNTRY
                )
        );

        String restaurantId = commandExecutor.execute(restaurantCommand);

        assertThat(restaurantId).isNotBlank();

        Restaurant restaurant = restaurantDatabase.findRestaurantById(restaurantId);

        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getId()).isEqualTo(restaurantId);
        assertThat(restaurant.getName()).isEqualTo(restaurantCommand.getName());
        assertThat(restaurant.getDescription()).isEqualTo(restaurantCommand.getDescription());
        assertThat(restaurant.getPhotoUrl()).isEqualTo(restaurantCommand.getPhotoUrl());

        assertThat(restaurant.getAddress()).isNotNull();
        assertThat(restaurant.getAddress().getStreet()).isEqualTo(restaurantCommand.getAddress().getStreet());
        assertThat(restaurant.getAddress().getPostalCode()).isEqualTo(restaurantCommand.getAddress().getPostalCode());
        assertThat(restaurant.getAddress().getCity()).isEqualTo(restaurantCommand.getAddress().getCity());
        assertThat(restaurant.getAddress().getCountry()).isEqualTo(restaurantCommand.getAddress().getCountry());
    }

}