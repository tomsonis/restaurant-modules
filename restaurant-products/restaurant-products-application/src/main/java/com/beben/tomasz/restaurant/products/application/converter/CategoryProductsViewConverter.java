package com.beben.tomasz.restaurant.products.application.converter;

import com.beben.tomasz.restaurant.products.api.view.CategoryProductsView;
import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Product;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CategoryProductsViewConverter {

    private CategoryViewConverter categoryViewConverter;

    private ProductViewConverter productViewConverter;

    public List<CategoryProductsView> convert(Map<Category, List<Product>> categoryListMap) {

        return categoryListMap.entrySet()
                .stream()
                .map(categoryEntityListEntry -> CategoryProductsView.of(
                        categoryViewConverter.convert(categoryEntityListEntry.getKey()),
                        productViewConverter.convert(categoryEntityListEntry.getValue())
                ))
                .collect(Collectors.toList());
    }
}
