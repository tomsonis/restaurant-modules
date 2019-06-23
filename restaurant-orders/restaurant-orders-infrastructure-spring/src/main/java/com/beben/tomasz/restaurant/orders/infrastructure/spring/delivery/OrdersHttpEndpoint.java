package com.beben.tomasz.restaurant.orders.infrastructure.spring.delivery;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.query.user.OrderListQuery;
import com.beben.tomasz.restaurant.orders.application.command.create.CreateOrderCommand;
import com.beben.tomasz.restaurant.orders.application.query.user.details.OrderDetailsQuery;
import com.beben.tomasz.restaurant.orders.domain.order.CreateOrderException;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
@AllArgsConstructor
public class OrdersHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    @PostMapping("create")
    OrderId createOrder(@Valid @RequestBody CreateOrderCommand createOrderCommand) throws Exception {
        return commandExecutor.execute(createOrderCommand).getOrElseThrow(() -> new CreateOrderException("Cannot create order."));
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
