package com.beben.tomasz.restaurant.products.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.products.application.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConvertersConfiguration {

    @Bean
    CategoryViewConverter categoryViewConverter() {
        return new CategoryViewConverter();
    }

    @Bean
    AllergenViewConverter allergenViewConverter() {
        return new AllergenViewConverter();
    }

    @Bean
    IngredientViewConverter ingredientViewConverter() {
        return new IngredientViewConverter();
    }

    @Bean
    ProductViewConverter productViewConverter(
            AllergenViewConverter allergenViewConverter,
            IngredientViewConverter ingredientViewConverter
    ) {
        return new ProductViewConverter(
                allergenViewConverter,
                ingredientViewConverter
        );
    }

    @Bean
    CategoryProductsViewConverter categoryProductsViewConverter(
            CategoryViewConverter categoryViewConverter,
            ProductViewConverter productViewConverter
    ) {
        return new CategoryProductsViewConverter(
                categoryViewConverter,
                productViewConverter
        );
    }
}
