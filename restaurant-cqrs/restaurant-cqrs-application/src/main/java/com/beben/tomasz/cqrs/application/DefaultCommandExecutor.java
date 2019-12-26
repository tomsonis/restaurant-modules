package com.beben.tomasz.cqrs.application;

import com.beben.tomasz.cqrs.api.HandlerInitializer;
import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.StopWatch;

//@Slf4j
@AllArgsConstructor
public class DefaultCommandExecutor implements CommandExecutor {

    private final HandlerInitializer handlerInitializer;

    @Override
    public <CommandType extends Command<CommandResultType>, CommandResultType> CommandResultType execute(CommandType command) throws Exception{
        CommandHandler<CommandType, CommandResultType> commandHandler = handlerInitializer.getCommandHandlerBean(command.getClass());

        String canonicalName = command.getClass().getCanonicalName();

//        log.info(String.format("Command %s started.", canonicalName));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CommandResultType handle = commandHandler.handle(command);
        stopWatch.stop();

//        log.info(String.format("Command %s completed in %s.", canonicalName, stopWatch));

        return handle;
    }
}
