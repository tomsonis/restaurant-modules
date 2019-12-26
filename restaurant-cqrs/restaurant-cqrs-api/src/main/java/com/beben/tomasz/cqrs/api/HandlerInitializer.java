package com.beben.tomasz.cqrs.api;

import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.cqrs.api.query.QueryHandler;

public interface HandlerInitializer {

    <CommandType extends Command<CommandResultType>, CommandResultType>
    CommandHandler<CommandType, CommandResultType> getCommandHandlerBean(Class<?> commandClass);

    <QueryType extends Query<QueryResultType>, QueryResultType>
    QueryHandler<QueryType, QueryResultType> getQueryHandlerBean(Class<?> queryClass);
}
