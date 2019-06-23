package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.core.api.command.AddRestaurantCommand;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantAddressConverter;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddRestaurantCommandHandler implements CommandHandler<AddRestaurantCommand, RestaurantId> {

    private RestaurantRepository restaurantRepository;

    private RestaurantFactory restaurantFactory;

    private ToRestaurantAddressConverter toRestaurantAddressConverter;

    @Override
    public RestaurantId handle(AddRestaurantCommand addRestaurantCommand) {

        Restaurant restaurant = restaurantFactory.createRestaurant(
                addRestaurantCommand.getName(),
                addRestaurantCommand.getDescription(),
                toRestaurantAddressConverter.convert(addRestaurantCommand.getAddress()),
                addRestaurantCommand.getPhotoUrl()
        );

        restaurantRepository.save(restaurant);

        return restaurant.getRestaurantId();
    }
}
