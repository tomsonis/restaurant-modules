package com.beben.tomasz.restaurant.core.infrastructure.spring.delivery;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.core.api.command.AddTableCommand;
import com.beben.tomasz.restaurant.core.api.query.tables.SearchTablesByRestaurantQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.TableId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("table")
@AllArgsConstructor
public class RestaurantTableHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    @PostMapping
    TableId addTable(@RequestBody AddTableCommand addTableCommand) throws Exception {
        return commandExecutor.execute(addTableCommand);
    }

    @GetMapping("/{restaurantId}")
    List<RestaurantTableView> tablesByRestaurnat(@PathVariable("restaurantId") String restaurantId) throws Exception {
        SearchTablesByRestaurantQuery tablesByRestaurantQuery = SearchTablesByRestaurantQuery.of(
                RestaurantId.of(restaurantId)
        );

        return queryExecutor.execute(tablesByRestaurantQuery);
    }
}
