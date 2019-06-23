package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.restaurant.commons.view.AddressView;
import com.beben.tomasz.restaurant.core.api.command.AddRestaurantCommand;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantAddressConverter;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantFactory;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class AddRestaurantCommandHandlerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantFactory restaurantFactory;

    @Spy
    private ToRestaurantAddressConverter toRestaurantAddressConverter;

    @InjectMocks
    private AddRestaurantCommandHandler commandHandler;

    @Captor
    private ArgumentCaptor<Restaurant> restaurantArgumentCaptor;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddRestaurant() {

        //given
        AddRestaurantCommand restaurantCommand = AddRestaurantCommand.of(
                TestRestaurantFactory.TEST_NAME,
                TestRestaurantFactory.TEST_DESC,
                TestRestaurantFactory.TEST_PHOTO,
                AddressView.of(
                        TestRestaurantFactory.TEST_STREET,
                        TestRestaurantFactory.TEST_POSTAL_CODE,
                        TestRestaurantFactory.TEST_CITY,
                        TestRestaurantFactory.TEST_COUNTRY
                )
        );
        Restaurant restaurant = TestRestaurantFactory.createRestaurant();

        //when

        when(restaurantFactory.createRestaurant(any(), any(), any(), any()))
                .thenReturn(restaurant);

        RestaurantId restaurantId = commandHandler.handle(restaurantCommand);

        assertThat(restaurantId).isNotNull();
        assertThat(restaurantId.getId()).isNotBlank();
        assertThat(restaurantId).isEqualTo(TestRestaurantFactory.TEST_RESTAURANT_ID);

        verify(restaurantRepository, times(1)).save(restaurantArgumentCaptor.capture());
        verifyNoMoreInteractions(restaurantRepository);

        Restaurant restaurantCaptor = restaurantArgumentCaptor.getValue();

        assertThat(restaurantCaptor.getRestaurantId()).isEqualTo(restaurant.getRestaurantId());
        assertThat(restaurantCaptor.getName()).isEqualTo(restaurant.getName());
        assertThat(restaurantCaptor.getPhotoUrl()).isEqualTo(restaurant.getPhotoUrl());
        assertThat(restaurantCaptor.getAddress().getStreet()).isEqualTo(restaurant.getAddress().getStreet());
        assertThat(restaurantCaptor.getAddress().getCity()).isEqualTo(restaurant.getAddress().getCity());
        assertThat(restaurantCaptor.getAddress().getPostalCode()).isEqualTo(restaurant.getAddress().getPostalCode());
        assertThat(restaurantCaptor.getAddress().getCountry()).isEqualTo(restaurant.getAddress().getCountry());
    }
}