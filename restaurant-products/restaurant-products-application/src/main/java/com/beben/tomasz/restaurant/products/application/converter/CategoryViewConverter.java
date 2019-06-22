package com.beben.tomasz.restaurant.products.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.products.api.view.CategoryView;
import com.beben.tomasz.restaurant.products.domain.Category;

public class CategoryViewConverter implements Converter<Category, CategoryView> {

    @Override
    public CategoryView convert(Category categoryEntity) {
        return CategoryView.of(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getOrderOnList(),
                categoryEntity.getRestaurantReference()
        );
    }
}
