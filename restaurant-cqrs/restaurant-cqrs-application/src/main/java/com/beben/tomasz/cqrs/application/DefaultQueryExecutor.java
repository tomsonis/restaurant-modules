package com.beben.tomasz.cqrs.application;

import com.beben.tomasz.cqrs.api.HandlerInitializer;
import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.StopWatch;

//@Slf4j
@AllArgsConstructor
public class DefaultQueryExecutor implements QueryExecutor {

    private final HandlerInitializer springHandlerInitializer;

    @Override
    public <QueryType extends Query<QueryResultType>, QueryResultType> QueryResultType execute(QueryType query) throws Exception {
        QueryHandler<QueryType, QueryResultType> queryHandlerBean = springHandlerInitializer.getQueryHandlerBean(query.getClass());

        String canonicalName = query.getClass().getCanonicalName();

//        log.info(String.format("Query %s started.", canonicalName));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        QueryResultType resultType = queryHandlerBean.handle(query);

        stopWatch.stop();
//        log.info(String.format("Query %s completed in %s.", canonicalName, stopWatch));

        return resultType;
    }
}
