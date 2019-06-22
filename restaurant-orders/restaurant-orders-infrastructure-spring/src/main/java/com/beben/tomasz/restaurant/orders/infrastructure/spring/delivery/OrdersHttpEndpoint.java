package com.beben.tomasz.restaurant.orders.infrastructure.spring.delivery;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.query.user.OrderListQuery;
import com.beben.tomasz.restaurant.orders.application.command.create.CreateOrderCommand;
import com.beben.tomasz.restaurant.orders.application.query.user.details.OrderDetailsQuery;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
@AllArgsConstructor
public class OrdersHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    @PostMapping("create")
    Option<OrderId> createOrder(@Valid @RequestBody CreateOrderCommand createOrderCommand) throws Exception {
        return commandExecutor.execute(createOrderCommand);
    }

    @PostMapping("details")
    OrderDetailsView orderDetails(@Valid @RequestBody OrderDetailsQuery orderDetailsQuery) throws Exception {
        return queryExecutor.execute(orderDetailsQuery);
    }

    @GetMapping("list")
    List<OrderDetailsView> orderListByUser(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) throws Exception {
        OrderListQuery orderListQuery = OrderListQuery.of(page, size);
        return queryExecutor.execute(orderListQuery);
    }
}
