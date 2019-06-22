package com.beben.tomasz.restaurant.products.application.query;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.products.api.query.SearchProductsGroupedByCategoriesQuery;
import com.beben.tomasz.restaurant.products.api.view.CategoryProductsView;
import com.beben.tomasz.restaurant.products.application.converter.CategoryProductsViewConverter;
import com.beben.tomasz.restaurant.products.application.query.ProductsReadRepository;
import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Product;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class SearchProductsGroupedByCategoriesQueryHandler
        implements QueryHandler<SearchProductsGroupedByCategoriesQuery, List<CategoryProductsView>> {

    private ProductsReadRepository productsReadRepository;

    private CategoryProductsViewConverter categoryProductsViewConverter;

    @Override
    public List<CategoryProductsView> handle(SearchProductsGroupedByCategoriesQuery searchProductsGroupedByCategoriesQuery) {

        Map<Category, List<Product>> categoryProductsMap = productsReadRepository.findProductsGroupedByCategory(
                searchProductsGroupedByCategoriesQuery.getRestaurantId()
        );

        return categoryProductsViewConverter.convert(categoryProductsMap);
    }
}
