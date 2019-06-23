package com.beben.tomasz.restaurant.orders.application.command.create;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.commons.Allowance;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.Rejection;
import com.beben.tomasz.restaurant.core.api.query.ExistRestaurantAndTableQuery;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.TableId;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderFactory;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import com.beben.tomasz.restaurant.orders.domain.order.event.OrderEvent;
import com.beben.tomasz.restaurant.products.api.query.SearchProductByIdsQuery;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import com.beben.tomasz.restaurant.user.api.query.UserExistQuery;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand, Option<OrderId>> {

    private OrdersRepository ordersRepository;

    private OrderFactory orderFactory;

    private ContextHolder contextHolder;

    private OrderEvent orderEvent;

    private QueryExecutor queryExecutor;

    @Override
    public Option<OrderId> handle(CreateOrderCommand createOrderCommand) {

        UserId userId = validateUserReference()
                .getOrElseThrow(rejection -> new IllegalArgumentException(rejection.getReasonMessage()));

        validateRestaurantAndTable(createOrderCommand)
                .flatMap(this::validateArrivalTime)
                .flatMap(this::validateOrderItems)
                .getOrElseThrow(rejection -> new IllegalArgumentException(rejection.getReasonMessage()));

        Order order = orderFactory.createOrder(
                userId,
                createOrderCommand.getRestaurantReference(),
                createOrderCommand.getTableReference(),
                createOrderCommand.getOrderItems(),
                createOrderCommand.getPaymentType(),
                createOrderCommand.getClient(),
                createOrderCommand.getArrivalTime()
        );

        return ordersRepository.save(order)
                .onDefined(orderEvent::emmit)
                .map(Order::getOrderId);
    }

    private Either<Rejection, CreateOrderCommand> validateArrivalTime(CreateOrderCommand createOrderCommand) {
        LocalTime arrivalTime = createOrderCommand.getArrivalTime();
        if (Objects.nonNull(arrivalTime) && arrivalTime.isBefore(LocalTime.now().plusMinutes(30))) {
            return Either.left(Rejection.withReason("Godzina przybycia musi być conajmniej 30min później od aktualnej godziny."));
        }

        return Either.right(createOrderCommand);
    }

    private Either<Rejection, UserId> validateUserReference() {
        return contextHolder.getContext().getUserId()
                .map(this::validUser)
                .getOrElse(Either.right(UserId.of("")));

    }

    private Either<Rejection, UserId> validUser(String userReference) {
        return Try.of(() -> {
                    UserExistQuery userExistQuery = UserExistQuery.of(userReference);
                    return queryExecutor.execute(userExistQuery);
                })
                .filter(aBoolean -> aBoolean)
                .map(abc -> Either.<Rejection, UserId>right(UserId.of(userReference)))
                .getOrElse(Either.left(Rejection.withReason("User not exists.")));

    }

    private Either<Rejection, CreateOrderCommand> validateRestaurantAndTable(CreateOrderCommand createOrderCommand) {
        return Try.of(() -> {
                    ExistRestaurantAndTableQuery restaurantAndTableQuery = ExistRestaurantAndTableQuery.of(
                            RestaurantId.of(createOrderCommand.getRestaurantReference().getId()),
                            TableId.of(createOrderCommand.getTableReference().getId())
                    );
                    return queryExecutor.execute(restaurantAndTableQuery);
                })
                .filter(aBoolean -> aBoolean)
                .map(aBoolean -> Either.<Rejection, CreateOrderCommand>right(createOrderCommand))
                .getOrElse(Either.left(Rejection.withReason("Błąd podczas walidacji restauracji lub stolika.")));
    }

    private Either<Rejection, Allowance> validateOrderItems(CreateOrderCommand createOrderCommand) {
        try {
            Set<OrderItem> orderItems = createOrderCommand.getOrderItems();
            List<String> orderItemIds = collectOrderItemIds(orderItems);

            SearchProductByIdsQuery productByIdsQuery = SearchProductByIdsQuery.of(orderItemIds);
            List<ProductView> productViews = queryExecutor.execute(productByIdsQuery);

            return orderItems.stream()
                    .map(orderItem -> validProducts(productViews, orderItem))
                    .findFirst()
                    .orElse(Either.left(Rejection.withReason("Other error")));

        } catch (Exception e) {
            return Either.left(Rejection.withReason(e.getMessage()));
        }
    }

    private Either<Rejection, Allowance> validProducts(List<ProductView> productViews, OrderItem orderItem) {
        return getProduct(productViews, orderItem.getId())
                .map(productView -> validOneProduct(orderItem, productView))
                .orElse(Either.left(Rejection.withReason("Brak potrawy: " + orderItem.getName() + "\n")));
    }

    private Either<Rejection, Allowance> validOneProduct(OrderItem orderItem, ProductView productView) {
        if (orderItem.getPrice().compareTo(productView.getPrice()) != 0) {
            return Either.left(Rejection.withReason("Cena potrawy: " + orderItem.getName() + " uległa zmianie" + "\n"));
        }
        return Either.right(Allowance.newInstance());
    }

    private Optional<ProductView> getProduct(List<ProductView> productViews, String id) {
        return productViews.stream()
                .filter(productView -> productView.getId().equals(id))
                .findFirst();
    }

    private List<String> collectOrderItemIds(Set<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(OrderItem::getId)
                .collect(Collectors.toList());
    }
}
