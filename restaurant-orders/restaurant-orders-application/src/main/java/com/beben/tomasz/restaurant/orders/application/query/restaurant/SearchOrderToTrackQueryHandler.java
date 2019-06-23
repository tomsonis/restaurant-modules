package com.beben.tomasz.restaurant.orders.application.query.restaurant;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.query.restaurant.SearchOrderToTrackQuery;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.user.api.query.RestaurantUserQuery;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import com.beben.tomasz.restaurant.user.api.view.RoleTypeView;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class SearchOrderToTrackQueryHandler implements QueryHandler<SearchOrderToTrackQuery, List<OrderDetailsView>> {

    private OrderReadRepository orderReadRepository;

    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    private QueryExecutor queryExecutor;

    @Override
    public List<OrderDetailsView> handle(SearchOrderToTrackQuery searchOrderToTrackQuery) throws Exception {
        RestaurantUserView restaurantUserView = queryExecutor.execute(RestaurantUserQuery.of()).get();//TODO: to refactor

        List<OrderStatus> statuses = getStatuses(restaurantUserView.getRoleTypeViews());

        List<Order> ordersToTrack = orderReadRepository.findOrdersToTrack(
                RestaurantId.of(restaurantUserView.getRestaurantReference()),
                statuses,
                searchOrderToTrackQuery.getPage(),
                searchOrderToTrackQuery.getSize()
        );

        return toOrderDetailsViewConverter.convert(ordersToTrack);
    }

    private List<OrderStatus> getStatuses(List<RoleTypeView> roleTypeViews) {
        if (roleTypeViews.contains(RoleTypeView.ROLE_MANAGER)) {
            return Arrays.asList(
                    OrderStatus.CONFIRMED,
                    OrderStatus.PAID,
                    OrderStatus.PREPARING,
                    OrderStatus.CREATED,
                    OrderStatus.DONE
            );
        } else if (roleTypeViews.contains(RoleTypeView.ROLE_COOK)) {
            return Arrays.asList(
                    OrderStatus.CONFIRMED,
                    OrderStatus.PAID,
                    OrderStatus.PREPARING
            );
        } else {
            return Arrays.asList(
                    OrderStatus.CREATED,
                    OrderStatus.DONE
            );
        }
    }
}
