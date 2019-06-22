package com.beben.tomasz.restaurant.products.infrastructure.spring.delivery;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.products.api.command.AddProductCommand;
import com.beben.tomasz.restaurant.products.api.query.SearchProductsGroupedByCategoriesQuery;
import com.beben.tomasz.restaurant.products.api.view.CategoryProductsView;
import com.beben.tomasz.restaurant.products.application.query.ProductsReadRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductsHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    private ProductsReadRepository productsReadRepository;

    @PostMapping
    String addProduct(@Valid @RequestBody AddProductCommand addProductCommand) throws Exception {
        return commandExecutor.execute(addProductCommand);
    }

    @GetMapping("/restaurant/{restaurantId}")
    List<CategoryProductsView> productsByRestaurantAndGroupedByCategory(
            @PathVariable("restaurantId") String restaurantId
    ) throws Exception {
        SearchProductsGroupedByCategoriesQuery query = SearchProductsGroupedByCategoriesQuery.of(restaurantId);
        return queryExecutor.execute(query);
    }
}
