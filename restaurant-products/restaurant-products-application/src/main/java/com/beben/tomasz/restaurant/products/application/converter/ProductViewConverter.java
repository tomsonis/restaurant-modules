package com.beben.tomasz.restaurant.products.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import com.beben.tomasz.restaurant.products.domain.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductViewConverter implements Converter<Product, ProductView> {

    private AllergenViewConverter allergenViewConverter;

    private IngredientViewConverter ingredientViewConverter;

    @Override
    public ProductView convert(Product product) {
        return ProductView.of(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                allergenViewConverter.convert(product.getAllergens()),
                ingredientViewConverter.convert(product.getIngredients())
        );
    }
}
