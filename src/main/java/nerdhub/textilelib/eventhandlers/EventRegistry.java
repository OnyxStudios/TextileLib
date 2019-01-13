package nerdhub.textilelib.eventhandlers;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import nerdhub.textilelib.events.CancelableEvent;
import nerdhub.textilelib.events.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventRegistry {

    public static EventRegistry INSTANCE = new EventRegistry();

    private final Map<Method, Object> eventSubscriberMethods;
    private final Multimap<Class<? extends Event>, Method> classMethodMultimap;
    private final Multimap<Class<? extends Event>, Consumer<? extends Event>> classLambdaMultimap;

    private EventRegistry() {
        eventSubscriberMethods = new HashMap<>();
        classMethodMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
        classLambdaMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
    }

    public void registerEventHandler(Object clazz) {
        for (Method method : clazz.getClass().getDeclaredMethods()) {
            if(!method.isAnnotationPresent(EventSubscriber.class)) {
                continue;
            }

            if(!Modifier.isPublic(method.getModifiers())) {
                throw new UnsupportedOperationException("Event Subscriber methods must be public. Method: " + method.getName());
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

            eventSubscriberMethods.put(method, clazz);
            classMethodMultimap.put((Class<? extends Event>)parameterTypes[0], method);
        }
    }

    public <E extends Event> void registerEventHandler(Class<E> eventClass, Consumer<E> handler) {
        classLambdaMultimap.put(eventClass, handler);
    }

    public <T extends Event> void fireEvent(T event) {
        for (Method method : classMethodMultimap.get(event.getClass())) {
            invokeMethod(eventSubscriberMethods.get(method), method, event);
        }

        for(Consumer<? extends Event> consumer: classLambdaMultimap.get(event.getClass())) {
            ((Consumer<T>)consumer).accept(event);
        }
    }

    public <T extends CancelableEvent> void fireEvent(T event) {
        for (Method method : classMethodMultimap.get(event.getClass())) {
            invokeMethod(eventSubscriberMethods.get(method), method, event);
            if(event.isCanceled()) {
                return;
            }
        }

        for(Consumer<? extends Event> consumer: classLambdaMultimap.get(event.getClass())) {
            ((Consumer<T>)consumer).accept(event);
            if(event.isCanceled()) {
                return;
            }
        }
    }

    private void invokeMethod(Object classOBject, Method method, Event event) {
        try {
            method.invoke(classOBject, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
