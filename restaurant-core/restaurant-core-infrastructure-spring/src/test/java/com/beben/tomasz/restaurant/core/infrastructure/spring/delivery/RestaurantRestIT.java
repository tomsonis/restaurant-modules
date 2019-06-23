package com.beben.tomasz.restaurant.core.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.commons.view.AddressView;
import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.command.AddRestaurantCommand;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import com.beben.tomasz.restaurant.core.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantRestIT extends BaseCoreIntegrationTest {

    private static final String TEST_STREET = "TEST_STREET";
    private static final String TEST_POSTAL_CODE = "TEST_POSTAL_CODE";
    private static final String TEST_CITY = "TEST_CITY";
    private static final String TEST_COUNTRY = "TEST_COUNTRY";
    private static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_DESC = "TEST_DESC";
    private static final String TEST_PHOTO = "TEST_PHOTO";

    @Autowired
    private RestaurantDatabase restaurantDatabase;

    @AfterMethod
    public void tearDown() {
        restaurantDatabase.clearRestaurantEntity();
    }

    @Test
    public void shouldAddRestaurant() throws URISyntaxException {
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

        Response response = client().target(getRestUri())
                .path("restaurant")
                .request()
                .post(Entity.json(restaurantCommand));

        assertThat(response.getStatus()).isEqualTo(200);

        RestaurantId restaurantId = response.readEntity(RestaurantId.class);

        assertThat(restaurantId).isNotNull();
        assertThat(restaurantId.getId()).isNotBlank();

        Restaurant restaurant = restaurantDatabase.findRestaurantById(restaurantId);

        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(restaurant.getName()).isEqualTo(restaurantCommand.getName());
        assertThat(restaurant.getDescription()).isEqualTo(restaurantCommand.getDescription());
        assertThat(restaurant.getPhotoUrl()).isEqualTo(restaurantCommand.getPhotoUrl());

        assertThat(restaurant.getAddress()).isNotNull();
        assertThat(restaurant.getAddress().getStreet()).isEqualTo(restaurantCommand.getAddress().getStreet());
        assertThat(restaurant.getAddress().getPostalCode()).isEqualTo(restaurantCommand.getAddress().getPostalCode());
        assertThat(restaurant.getAddress().getCity()).isEqualTo(restaurantCommand.getAddress().getCity());
        assertThat(restaurant.getAddress().getCountry()).isEqualTo(restaurantCommand.getAddress().getCountry());
    }

    @Test
    public void shouldGetRestaurantDetailsView() throws URISyntaxException {
        //given
        Restaurant restaurant = restaurantDatabase.saveRestaurantEntity();

        //when
        Response response = client().target(getRestUri())
                .path("restaurant")
                .path("details")
                .path(restaurant.getRestaurantId().getId())
                .request()
                .get();

        RestaurantView restaurantView = response.readEntity(RestaurantView.class);

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }

    @Test
    public void shouldFindAllRestaurant() throws URISyntaxException {
        //given
        Restaurant restaurant = restaurantDatabase.saveRestaurantEntity();

        //when
        Response response = client().target(getRestUri())
                .path("restaurant")
                .request()
                .get();

        List<RestaurantView> restaurantViews = response.readEntity(new GenericType<List<RestaurantView>>(){});

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        assertThat(restaurantViews).isNotNull();
        assertThat(restaurantViews).hasSizeGreaterThan(0);

        RestaurantView restaurantView = restaurantViews.get(0);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }

    @Test
    public void shouldFindRestaurantByName() throws URISyntaxException {
        Restaurant restaurant = restaurantDatabase.saveRestaurantEntity();

        //when
        Response response = client().target(getRestUri())
                .path("restaurant")
                .path(restaurant.getName())
                .request()
                .get();

        List<RestaurantView> restaurantViews = response.readEntity(new GenericType<List<RestaurantView>>(){});

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        assertThat(restaurantViews).isNotNull();
        assertThat(restaurantViews).hasSizeGreaterThan(0);

        RestaurantView restaurantView = restaurantViews.get(0);

        AssertUtils.assertRestaurantView(restaurantView, restaurant);
    }
}
