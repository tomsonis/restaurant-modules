package com.beben.tomasz.restaurant.orders.infrastructure.spring.delivery;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.command.*;
import com.beben.tomasz.restaurant.orders.api.query.restaurant.RestaurantOrderListQuery;
import com.beben.tomasz.restaurant.orders.api.query.restaurant.SearchOrderToTrackQuery;
import com.beben.tomasz.restaurant.orders.application.query.restaurant.RestaurantOrderDetailsQuery;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "restaurant/orders")
@AllArgsConstructor
public class RestaurantOrdersHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    @PostMapping("details")
    OrderDetailsView restaurantOrderDetails(
            @Valid @RequestBody RestaurantOrderDetailsQuery restaurantOrderDetailsQuery
    ) throws Exception {
        return queryExecutor.execute(restaurantOrderDetailsQuery)
                .getOrElseThrow(() -> new IllegalArgumentException("Problem jest."));
    }

    @GetMapping("/list")
    List<OrderDetailsView> restaurantOrders(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) throws Exception {
        RestaurantOrderListQuery restaurantOrderListQuery = RestaurantOrderListQuery.of(page, size);
        return queryExecutor.execute(restaurantOrderListQuery);
    }

    @GetMapping("track")
    @RolesAllowed({"ROLE_WAITER", "ROLE_COOK", "ROLE_MANAGER", "ROLE_ADMIN"})
    List<OrderDetailsView> orderToTrack(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) throws Exception {
        return queryExecutor.execute(SearchOrderToTrackQuery.of(page, size));
    }

    @PostMapping("confirm")
    @RolesAllowed({"ROLE_WAITER", "ROLE_COOK", "ROLE_MANAGER", "ROLE_ADMIN"})
    void confirmOrder(@Valid @RequestBody ConfirmOrderCommand confirmOrderCommand) throws Exception {
        commandExecutor.execute(confirmOrderCommand);
    }

    @PostMapping("prepare")
    @RolesAllowed({"ROLE_WAITER", "ROLE_COOK", "ROLE_MANAGER", "ROLE_ADMIN"})
    void prepareOrder(@Valid @RequestBody PrepareOrderCommand prepareOrderCommand) throws Exception {
        commandExecutor.execute(prepareOrderCommand);
    }

    @PostMapping("done")
    @RolesAllowed({"ROLE_WAITER", "ROLE_COOK", "ROLE_MANAGER", "ROLE_ADMIN"})
    void doneOrder(@Valid @RequestBody FinishOrderCommand finishOrderCommand) throws Exception {
        commandExecutor.execute(finishOrderCommand);
    }

    @PostMapping("pay")
    @RolesAllowed({"ROLE_WAITER", "ROLE_COOK", "ROLE_MANAGER", "ROLE_ADMIN"})
    void payOrder(@Valid @RequestBody PayOrderCommand payOrderCommand) throws Exception {
        commandExecutor.execute(payOrderCommand);
    }

    @PostMapping("give")
    @RolesAllowed({"ROLE_WAITER", "ROLE_COOK", "ROLE_MANAGER", "ROLE_ADMIN"})
    void givenOrderStatus(@Valid @RequestBody GivenOrderStatusCommand givenOrderStatusCommand) throws Exception {
        commandExecutor.execute(givenOrderStatusCommand);
    }

    @DeleteMapping("delete/{orderId}")
    @RolesAllowed({"ROLE_WAITER", "ROLE_COOK", "ROLE_MANAGER", "ROLE_ADMIN"})
    void deleteOrder(@PathVariable("orderId") String orderId) throws Exception {
        DeleteOrderCommand deleteOrderCommand = DeleteOrderCommand.of(orderId);
        commandExecutor.execute(deleteOrderCommand);
    }
}
