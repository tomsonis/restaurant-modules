package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.restaurant.core.api.command.AddTableCommand;
import com.beben.tomasz.restaurant.core.domain.RestaurantNotExistException;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import com.beben.tomasz.cqrs.api.command.CommandHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddTableCommandHandler implements CommandHandler<AddTableCommand, String> {

    private RestaurantTableRepository restaurantTableRepository;

    private RestaurantTableFactory restaurantTableFactory;

    @Override
    public String handle(AddTableCommand addTableCommand) throws RestaurantNotExistException {

        RestaurantTable restaurantTable = restaurantTableFactory.createTable(
                addTableCommand.getName(),
                addTableCommand.getPosition(),
                addTableCommand.getRestaurantId(),
                addTableCommand.getCapacity()
        );

        restaurantTableRepository.save(restaurantTable);

        return restaurantTable.getId();
    }
}
