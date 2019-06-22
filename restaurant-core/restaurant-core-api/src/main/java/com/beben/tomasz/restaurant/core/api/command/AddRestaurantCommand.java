package com.beben.tomasz.restaurant.core.api.command;

import com.beben.tomasz.restaurant.commons.view.AddressView;
import com.beben.tomasz.cqrs.api.command.Command;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class AddRestaurantCommand implements Command<String> {

    private String name;

    private String description;

    private String photoUrl;

    private AddressView address;
}
