package com.beben.tomasz.restaurant.orders.configuration;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.commons.mail.EmailService;
import com.beben.tomasz.restaurant.core.api.query.ExistRestaurantAndTableQuery;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.TestOrdersDatabase;
import com.beben.tomasz.restaurant.products.api.query.SearchProductByIdsQuery;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import com.beben.tomasz.restaurant.user.api.query.RestaurantUserQuery;
import com.beben.tomasz.restaurant.user.api.query.UserExistQuery;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import com.beben.tomasz.restaurant.user.api.view.RoleTypeView;
import io.vavr.control.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Configuration
public class TestOrdersConfiguration {

    @Bean
    TestOrdersDatabase testOrdersDatabase(EntityManager entityManager) {
        return new TestOrdersDatabase(entityManager);
    }

    @Bean
    SimpMessagingTemplate simpMessagingTemplate() {
        return new TestSimpMessagingTemplate();
    }

    @Bean
    RestaurantUserQueryHandler restaurantUserQuery() {
        return new RestaurantUserQueryHandler();
    }

    @Bean
    UserExistQueryHandler userExistQuery() {
        return new UserExistQueryHandler();
    }

    @Bean
    SearchProductByIdsQueryHandler searchProductByIdsQueryListQueryHandler() {
        return new SearchProductByIdsQueryHandler();
    }

    @Bean
    ExistRestaurantAndTableQueryHandler existRestaurantAndTableQueryHandler() {
        return new ExistRestaurantAndTableQueryHandler();
    }

    @Bean
    EmailService emailService() {
        return (to, subject, text) -> {};
    }
}

class ExistRestaurantAndTableQueryHandler implements QueryHandler<ExistRestaurantAndTableQuery, Boolean> {

    @Override
    public Boolean handle(ExistRestaurantAndTableQuery existRestaurantAndTableQuery) {
        return true;
    }
}

class RestaurantUserQueryHandler implements QueryHandler<RestaurantUserQuery, Option<RestaurantUserView>> {

    @Override
    public Option<RestaurantUserView> handle(RestaurantUserQuery restaurantUserQuery) {
        return Option.of(RestaurantUserView.of(
                TestOrdersDatabase.TEST_RESTAURANT_REFERENCE.getId(),
                Collections.singletonList(RoleTypeView.ROLE_WAITER)
        ));
    }
}
class UserExistQueryHandler implements QueryHandler<UserExistQuery, Boolean> {

    @Override
    public Boolean handle(UserExistQuery userExistQuery) {
        return true;
    }
}

class SearchProductByIdsQueryHandler implements QueryHandler<SearchProductByIdsQuery, List<ProductView>> {

    @Override
    public List<ProductView> handle(SearchProductByIdsQuery searchProductByIdsQuery) {
        return Collections.singletonList(ProductView.of(
                TestOrdersDatabase.TEST_ORDER_ITEM_ID,
                TestOrdersDatabase.TEST_ORDER_ITEM_NAME,
                TestOrdersDatabase.TEST_ORDER_ITEM_NAME,
                TestOrdersDatabase.TEST_ORDER_ITEM_PRICE,
                Collections.emptyList(),
                Collections.emptyList()
        ));
    }
}

class TestSimpMessagingTemplate extends SimpMessagingTemplate {

    TestSimpMessagingTemplate() {
        super((message, timeout) -> true);
    }

    @Override
    public void send(Message<?> message) {
    }
}
