package com.beben.tomasz.restaurant.core.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchByRestaurantNameQuery;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantDetailsQuery;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantsQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.api.command.AddRestaurantCommand;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurant")
@AllArgsConstructor
public class RestaurantHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    @PostMapping
    RestaurantId addRestaurant(@RequestBody AddRestaurantCommand addRestaurantCommand) throws Exception {
        String restaurantId = commandExecutor.execute(addRestaurantCommand);

        return RestaurantId.of(restaurantId);
    }

    @GetMapping("details/{restaurantId}")
    RestaurantView restaurantViewDetails(@PathVariable("restaurantId") String restaurantId) throws Exception {
        SearchRestaurantDetailsQuery restaurantDetailsQuery = SearchRestaurantDetailsQuery.of(restaurantId);
        return queryExecutor.execute(restaurantDetailsQuery);
    }

    @GetMapping
    List<RestaurantView> findAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) throws Exception {
        SearchRestaurantsQuery searchRestaurantsQuery = SearchRestaurantsQuery.of(page, size);
        return queryExecutor.execute(searchRestaurantsQuery);
    }

    @GetMapping("{name}")
    List<RestaurantView> searchByRestaurantName(
            @PathVariable("name") String name,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) throws Exception {
        return queryExecutor.execute(SearchByRestaurantNameQuery.of(name, page, size));
    }
}
