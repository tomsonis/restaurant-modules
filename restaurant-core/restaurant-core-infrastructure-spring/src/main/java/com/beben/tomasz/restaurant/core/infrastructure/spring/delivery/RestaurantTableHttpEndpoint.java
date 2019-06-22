package com.beben.tomasz.restaurant.core.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.core.api.query.tables.SearchTablesByRestaurantQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.api.command.AddTableCommand;
import com.beben.tomasz.restaurant.core.domain.TableId;
import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("table")
@AllArgsConstructor
public class RestaurantTableHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    @PostMapping
    TableId addTable(@RequestBody AddTableCommand addTableCommand) throws Exception {
        String tableId = commandExecutor.execute(addTableCommand);
        return TableId.of(tableId);
    }

    @GetMapping("/{restaurantId}")
    List<RestaurantTableView> tablesByRestaurnat(@PathVariable("restaurantId") String restaurantId) throws Exception {
        SearchTablesByRestaurantQuery tablesByRestaurantQuery = SearchTablesByRestaurantQuery.of(restaurantId);

        return queryExecutor.execute(tablesByRestaurantQuery);
    }
}
