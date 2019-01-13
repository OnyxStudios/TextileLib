package nerdhub.textilelib.eventhandlers;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import nerdhub.textilelib.events.CancelableEvent;
import nerdhub.textilelib.events.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventRegistry {

    public static EventRegistry INSTANCE = new EventRegistry();

    // TODO: Using to compare Event class types as they may be loaded by different class loaders.
    private static final String eventClassName = Event.class.getCanonicalName();

    private final Map<Method, Object> eventSubscriberMethods;
    private final Multimap<Class<? extends Event>, Method> classMethodMultimap;
    private final Multimap<Class<? extends Event>, Consumer<? extends Event>> classLambdaMultimap;

    private EventRegistry() {
        eventSubscriberMethods = new HashMap<>();
        classMethodMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
        classLambdaMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
    }

    public void registerEventHandler(Object clazz) {
        for (Method method : clazz.getClass().getMethods()) {

            if(method.isAnnotationPresent(EventSubscriber.class)) {

                Class<?>[] parameterTypes = method.getParameterTypes();

                Class eventClass = parameterTypes[0];

                if (parameterTypes.length != 1) {
                    throw new UnsupportedOperationException("There must only be one parameter that extends nerdhub.textilelib.events.Event "
                            + method.getName());
                }

                if(Event.class.isAssignableFrom(parameterTypes[0])) {
                    eventSubscriberMethods.put(method, clazz);
                    classMethodMultimap.put(eventClass, method);
                }
            }
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

    private boolean implementsEvent(Class clazz) {
        do {
            if(clazz.getCanonicalName().equals(eventClassName)) {
                return true;
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return false;
    }
}
