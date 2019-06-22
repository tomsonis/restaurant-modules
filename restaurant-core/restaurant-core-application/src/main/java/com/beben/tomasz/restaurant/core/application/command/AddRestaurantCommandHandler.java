package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.restaurant.core.api.command.AddRestaurantCommand;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantAddressConverter;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.cqrs.api.command.CommandHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddRestaurantCommandHandler implements CommandHandler<AddRestaurantCommand, String> {

    private RestaurantRepository restaurantRepository;

    private RestaurantFactory restaurantFactory;

    private ToRestaurantAddressConverter toRestaurantAddressConverter;

    @Override
    public String handle(AddRestaurantCommand addRestaurantCommand) {

        Restaurant restaurant = restaurantFactory.createRestaurant(
                addRestaurantCommand.getName(),
                addRestaurantCommand.getDescription(),
                toRestaurantAddressConverter.convert(addRestaurantCommand.getAddress()),
                addRestaurantCommand.getPhotoUrl()
        );

        restaurantRepository.save(restaurant);

        return restaurant.getId();
    }
}
