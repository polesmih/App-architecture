package ru.handler;

import org.reflections.Reflections;
import ru.config.ServerConfig;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.service.FileService;
import ru.service.SocketService;

import java.lang.invoke.MethodHandle;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class MethodHandlerFactory {
    public static MethodHandler create(SocketService socketService, ResponseSerializerImpl responseSerializer,
                                       ServerConfig config, FileService fileService) {
        PutMethodHandler putMethodHandler = new PutMethodHandler(null, socketService, responseSerializer, config);
        PostMethodHandler postMethodHandler = new PostMethodHandler(putMethodHandler, socketService, responseSerializer, config);
        return new GetMethodHandler(postMethodHandler, socketService, responseSerializer, config, fileService);

    }

    public static MethodHandle createAnnotated(){
        Reflections reflections = new Reflections("ru.handler");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Handler.class);

        Set<Class<?>> sortMethods = classes.stream()
                .sorted(Comparator.comparing(m -> m.getAnnotation(Handler.class).order()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        sortMethods.forEach(System.out::println);
        return null;
    }
}
