package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.read.sqlmapper;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

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
