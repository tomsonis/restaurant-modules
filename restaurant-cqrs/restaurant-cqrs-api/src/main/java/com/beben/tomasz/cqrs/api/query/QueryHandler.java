package com.beben.tomasz.cqrs.api.query;

public interface QueryHandler<QueryType extends Query<QueryResultType>, QueryResultType> {

    QueryResultType handle(QueryType queryType) throws Exception;
}
