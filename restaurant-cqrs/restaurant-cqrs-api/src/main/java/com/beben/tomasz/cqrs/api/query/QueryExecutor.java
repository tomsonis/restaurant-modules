package com.beben.tomasz.cqrs.api.query;

public interface QueryExecutor {

    <QueryType extends Query<QueryResultType>, QueryResultType> QueryResultType execute(QueryType queryType) throws Exception;
}
