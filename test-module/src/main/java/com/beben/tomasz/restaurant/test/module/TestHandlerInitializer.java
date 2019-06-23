package com.beben.tomasz.restaurant.test.module;


import com.beben.tomasz.cqrs.api.HandlerInitializer;
import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class TestHandlerInitializer implements HandlerInitializer, ApplicationListener<ContextRefreshedEvent> {

    private final ConfigurableListableBeanFactory beanFactory;

    private Map<Class<?>, String> commandHandlers = new HashMap<>();

    private Map<Class<?>, String> queryHandlers = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <CommandType extends Command<CommandResultType>, CommandResultType>
    CommandHandler<CommandType, CommandResultType> getCommandHandlerBean(Class<?> commandClass) {
        String beanName = commandHandlers.get(commandClass);

        return getCommandTypeCommandResultTypeCommandHandler(commandClass, beanName, CommandHandler.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <QueryType extends Query<QueryResultType>, QueryResultType> QueryHandler<QueryType, QueryResultType>
    getQueryHandlerBean(Class<?> queryClass) {
        String beanName = queryHandlers.get(queryClass);

        return getCommandTypeCommandResultTypeCommandHandler(queryClass, beanName, QueryHandler.class);
    }

    private <T> T getCommandTypeCommandResultTypeCommandHandler(Class<?> clazz, String beanName, Class<T> handlerClass) {
        if (beanName == null) {
            throw new RuntimeException("Command handler not found. Command class is " + clazz);
        }

        return beanFactory.getBean(beanName, handlerClass);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        commandHandlers = getCollect(CommandHandler.class);
        queryHandlers = getCollect(QueryHandler.class);
    }

    private Map<Class<?>, String> getCollect(Class<?> clazz) {
        return beanFactory.getBeansOfType(clazz)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> getHandledCommandType(e.getValue().getClass(), clazz),
                        Map.Entry::getKey)
                );
    }

    private Class<?> getHandledCommandType(Class<?> clazz, Class<?> handlerClass) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findByRawType(genericInterfaces, handlerClass);
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> clazz) {
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parametrized = (ParameterizedType) type;
                if (clazz.equals(parametrized.getRawType())) {
                    return parametrized;
                }
            }
        }
        throw new RuntimeException();
    }
}
