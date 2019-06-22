package com.beben.tomasz.restaurant.products.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.products.api.view.IngredientView;
import com.beben.tomasz.restaurant.products.api.view.UnitEnumView;
import com.beben.tomasz.restaurant.products.api.view.VolumeView;
import com.beben.tomasz.restaurant.products.domain.Ingredient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IngredientViewConverter implements Converter<Ingredient, IngredientView> {

    @Override
    public IngredientView convert(Ingredient ingredient) {
        return IngredientView.of(
                ingredient.getName(),
                VolumeView.of(
                        ingredient.getVolume().getCapacity(),
                        UnitEnumView.valueOf(ingredient.getVolume().getUnitEnum().name())
                )
        );
    }
}
