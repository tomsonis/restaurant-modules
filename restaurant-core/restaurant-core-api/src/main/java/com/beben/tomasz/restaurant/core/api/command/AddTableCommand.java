package com.beben.tomasz.restaurant.core.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class AddTableCommand implements Command<String> {

    private String name;

    public String position;

    private String description;

    private String restaurantId;

    public int capacity;
}
