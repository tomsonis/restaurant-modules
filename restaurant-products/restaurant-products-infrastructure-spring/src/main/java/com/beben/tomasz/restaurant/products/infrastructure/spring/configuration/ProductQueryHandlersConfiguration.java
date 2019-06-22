package com.beben.tomasz.restaurant.products.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.products.application.converter.CategoryProductsViewConverter;
import com.beben.tomasz.restaurant.products.application.converter.ProductViewConverter;
import com.beben.tomasz.restaurant.products.application.query.ProductsReadRepository;
import com.beben.tomasz.restaurant.products.application.query.SearchProductByIdsQueryHandler;
import com.beben.tomasz.restaurant.products.application.query.SearchProductsGroupedByCategoriesQueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductQueryHandlersConfiguration {

    @Bean
    SearchProductsGroupedByCategoriesQueryHandler searchProductsGroupedByCategoriesQueryHandler(
            ProductsReadRepository productsReadRepository,
            CategoryProductsViewConverter categoryProductsViewConverter
    ) {
        return new SearchProductsGroupedByCategoriesQueryHandler(
                productsReadRepository,
                categoryProductsViewConverter
        );
    }

    @Bean
    SearchProductByIdsQueryHandler searchProductByIdsQueryHandler(
            ProductsReadRepository productsReadRepository,
            ProductViewConverter productViewConverter
    ) {
        return new SearchProductByIdsQueryHandler(
                productsReadRepository,
                productViewConverter
        );
    }
}
