package nerdhub.textilelib.eventhandlers;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import nerdhub.textilelib.events.CancelableEvent;
import nerdhub.textilelib.events.Event;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;

public class EventRegistry {

    public static EventRegistry INSTANCE = new EventRegistry();

    private final Multimap<Class<? extends Event>, Consumer<? extends Event>> classLambdaMultimap;
    private final MethodHandles.Lookup methodLookup;

    private EventRegistry() {
        methodLookup = MethodHandles.lookup();
        classLambdaMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
    }

    public void registerEventHandler(Object clazz) {
        for (Method method : clazz.getClass().getDeclaredMethods()) {
            if(!method.isAnnotationPresent(EventSubscriber.class)) {
                continue;
            }

            if(!Modifier.isStatic(method.getModifiers())) {
                throw new UnsupportedOperationException("Event Subscriber methods must be static. Method: " + method.getName());
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new UnsupportedOperationException("Event Subscriber methods must only accept one argument. Method: "
                        + method.getName());
            }

            if(!Event.class.isAssignableFrom(parameterTypes[0])) {
                throw new UnsupportedOperationException("Event Subscriber method arguments must be of type nerdhub.textilelib.events.Event. Method: "
                        + method.getName());
            }

            try {
                MethodHandle mh = methodLookup.unreflect(method);
                Consumer<? extends Event> methodLambda =
                        (Consumer<? extends Event>) LambdaMetafactory
                            .metafactory(
                                methodLookup,
                                "accept",
                                MethodType.methodType(Consumer.class),
                                MethodType.methodType(void.class, Object.class),
                                mh,
                                MethodType.methodType(void.class, parameterTypes[0]))
                            .getTarget().invoke();

                synchronized (classLambdaMultimap) {
                    classLambdaMultimap.put((Class<? extends Event>)parameterTypes[0], methodLambda);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Method visibility has changed while being operated on. Method: " + method.getName(), e);
            } catch (LambdaConversionException e) {
                throw new RuntimeException("PLEASE REPORT THIS: Method was unable to be converted to a lambda. Method: " + method.getName(), e);
            } catch (Throwable throwable) {
                throw new RuntimeException("PLEASE REPORT THIS: Exception thrown during lambda construction. Method: " + method.getName(), throwable);
            }
        }
    }

    public <E extends Event> void registerEventHandler(Class<E> eventClass, Consumer<E> handler) {
        synchronized (classLambdaMultimap) {
            classLambdaMultimap.put(eventClass, handler);
        }
    }

    public <T extends Event> void fireEvent(T event) {
        for(Consumer<? extends Event> consumer: classLambdaMultimap.get(event.getClass())) {
            ((Consumer<T>)consumer).accept(event);
        }
    }

    public <T extends CancelableEvent> void fireEvent(T event) {
        for(Consumer consumer: classLambdaMultimap.get(event.getClass())) {
            consumer.accept(event);
            if(event.isCanceled()) {
                return;
            }
        }
    }
}
