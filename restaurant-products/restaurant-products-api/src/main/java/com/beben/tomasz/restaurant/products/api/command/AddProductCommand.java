package com.beben.tomasz.restaurant.products.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class AddProductCommand implements Command<String> {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private Collection<String> ingredientIds;

    @NotEmpty
    private Collection<String> allergenIds;

    @NotEmpty
    private String volumeId;

    @NotNull
    private BigDecimal price;

    private String photoUrl;

    private boolean available;

    @NotEmpty
    private String categoryId;

    @NotEmpty
    private String restaurantReference;
}
