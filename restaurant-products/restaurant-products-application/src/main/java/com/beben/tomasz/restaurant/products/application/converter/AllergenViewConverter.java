package com.beben.tomasz.restaurant.products.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.products.api.view.AllergenView;
import com.beben.tomasz.restaurant.products.domain.Allergen;

public class AllergenViewConverter implements Converter<Allergen, AllergenView> {

    @Override
    public AllergenView convert(Allergen allergen) {
        return AllergenView.of(
                allergen.getName(),
                allergen.getDescription()
        );
    }
}
