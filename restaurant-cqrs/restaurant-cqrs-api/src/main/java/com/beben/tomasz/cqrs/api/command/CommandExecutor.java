package com.beben.tomasz.cqrs.api.command;

public interface CommandExecutor {

    <CommandType extends Command<CommandResultType>, CommandResultType> CommandResultType execute(CommandType commandType) throws Exception;
}
