package com.beben.tomasz.restaurant.core.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.core.BaseCoreIntegrationTest;
import com.beben.tomasz.restaurant.core.api.command.AddTableCommand;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.TableId;
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


public class RestaurantTableRestIT extends BaseCoreIntegrationTest {

    private static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_DESC = "TEST_DESC";
    private static final String TEST_POS = "TEST_POS";
    private static final int TEST_CAPACITY = 1;

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
                restaurant.getId(),
                TEST_CAPACITY
        );

        Response response = client().target(getRestUri())
                .path("table")
                .request()
                .post(Entity.json(addTableCommand));

        assertThat(response.getStatus()).isEqualTo(200);

        TableId tableId = response.readEntity(TableId.class);
        assertThat(tableId).isNotNull();
        assertThat(tableId.getId()).isNotBlank();

        RestaurantTable restaurantTable = restaurantDatabase.findTableById(tableId.getId());

        assertThat(restaurantTable).isNotNull();
        assertThat(restaurantTable.getId()).isEqualTo(tableId.getId());
        assertThat(restaurantTable.getName()).isEqualTo(TEST_NAME);
        assertThat(restaurantTable.getPosition()).isEqualTo(TEST_POS);
        assertThat(restaurantTable.getCapacity()).isEqualTo(TEST_CAPACITY);
        assertThat(restaurantTable.getRestaurantReference()).isEqualTo(restaurant.getId());
    }

    @Test
    public void shouldFindTablesByRestaurantId() throws URISyntaxException {
        // given
        RestaurantTable restaurantTable = restaurantDatabase.saveRestaurantTableEntity();

        Response response = client().target(getRestUri())
                .path("table")
                .path(restaurantTable.getRestaurantReference())
                .request()
                .get();

        List<RestaurantTableView> restaurantTableViews = response.readEntity(new GenericType<List<RestaurantTableView>>() {
        });

        // then
        assertThat(response.getStatus()).isEqualTo(200);

        assertThat(restaurantTableViews).isNotNull();
        assertThat(restaurantTableViews).hasSizeGreaterThan(0);


        RestaurantTableView tableView = restaurantTableViews.get(0);

        AssertUtils.assertRestaurantTableView(tableView, restaurantTable);
    }

}