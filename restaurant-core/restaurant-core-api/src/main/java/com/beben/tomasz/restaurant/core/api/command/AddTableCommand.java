package com.beben.tomasz.restaurant.core.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class AddTableCommand implements Command<TableId> {

    private String name;

    public String position;

    private String description;

    private RestaurantId restaurantId;

    public int capacity;
}
