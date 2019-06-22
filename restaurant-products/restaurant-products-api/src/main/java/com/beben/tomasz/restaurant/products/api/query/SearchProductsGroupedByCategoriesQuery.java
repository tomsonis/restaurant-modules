package com.beben.tomasz.restaurant.products.api.query;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.products.api.view.CategoryProductsView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SearchProductsGroupedByCategoriesQuery implements Query<List<CategoryProductsView>> {

    private String restaurantId;
}
