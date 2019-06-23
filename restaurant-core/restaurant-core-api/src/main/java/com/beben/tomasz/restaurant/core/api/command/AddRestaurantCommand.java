package com.beben.tomasz.restaurant.core.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.restaurant.commons.view.AddressView;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
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
public class AddRestaurantCommand implements Command<RestaurantId> {

    private String name;

    private String description;

    private String photoUrl;

    private AddressView address;
}
