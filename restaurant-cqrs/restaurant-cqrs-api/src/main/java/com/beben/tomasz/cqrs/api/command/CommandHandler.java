package com.beben.tomasz.cqrs.api.command;

public interface CommandHandler<CommandType extends Command<CommandResultType>, CommandResultType> {

    CommandResultType handle(CommandType commandType) throws Exception;
}
