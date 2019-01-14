package nerdhub.textilelib.eventhandlers;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import nerdhub.textilelib.events.CancelableEvent;
import nerdhub.textilelib.events.Event;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;

public class EventRegistry {

    public static EventRegistry INSTANCE = new EventRegistry();

    private final Multimap<Class<? extends Event>, MethodHandle> classMethodMultimap;
    private final Multimap<Class<? extends Event>, Consumer<? extends Event>> classLambdaMultimap;
    private final MethodHandles.Lookup methodLookup;

    private EventRegistry() {
        methodLookup = MethodHandles.publicLookup();
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

            try {
                MethodHandle temp = methodLookup.unreflect(method);
                temp = temp.bindTo(clazz);

                synchronized (classMethodMultimap) {
                    classMethodMultimap.put((Class<? extends Event>)parameterTypes[0], temp);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Method visibility has changed while being operated on. Method: " + method.getName());
            }
        }
    }

    public <E extends Event> void registerEventHandler(Class<E> eventClass, Consumer<E> handler) {
        synchronized (classLambdaMultimap) {
            classLambdaMultimap.put(eventClass, handler);
        }
    }

    public <T extends Event> void fireEvent(T event) {
        for (MethodHandle method : classMethodMultimap.get(event.getClass())) {
            try {
                method.invoke(event);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        for(Consumer<? extends Event> consumer: classLambdaMultimap.get(event.getClass())) {
            ((Consumer<T>)consumer).accept(event);
        }
    }

    public <T extends CancelableEvent> void fireEvent(T event) {
        for (MethodHandle method : classMethodMultimap.get(event.getClass())) {
            try {
                method.invoke(event);
                if(event.isCanceled()) {
                    return;
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        for(Consumer<? extends Event> consumer: classLambdaMultimap.get(event.getClass())) {
            ((Consumer<T>)consumer).accept(event);
            if(event.isCanceled()) {
                return;
            }
        }
    }
}
