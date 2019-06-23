package com.beben.tomasz.restaurant.products.api.view;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ProductView {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private List<AllergenView> allergens;

    private List<IngredientView> ingredients;
}
