package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.read.sqlmapper;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = RestaurantTableDistinctCapacityQueryMapper.QUERY_NAME,
                query = "select DISTINCT capacity from RestaurantTableEntity",
                resultSetMapping = RestaurantTableDistinctCapacityQueryMapper.QUERY_MAPPING_NAME
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = RestaurantTableDistinctCapacityQueryMapper.QUERY_MAPPING_NAME,
                classes = {
                        @ConstructorResult(
                                targetClass = RestaurantTableDistinctCapacityQueryMapper.class,
                                columns = {
                                        @ColumnResult(name = "id"),
                                        @ColumnResult(name = "clientId"),
                                        @ColumnResult(name = "orderStatus"),
                                })
                }
        )
})
@Entity
class RestaurantTableDistinctCapacityQueryMapper {

    static final String QUERY_NAME = "RestaurantTableDistinctCapacityQueryMapper";
    static final String QUERY_MAPPING_NAME = "RestaurantTableDistinctCapacityQueryMapper";

    @Id
    private int id;
}
