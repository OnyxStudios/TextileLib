package nerdhub.textilelib.eventhandlers;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventRegistry {

    public static EventRegistry INSTANCE = new EventRegistry();

    private final Map<Method, Object> eventSubscriberMethods;
    private final Multimap<Class, Method> classMethodMultimap;

    private EventRegistry() {
        eventSubscriberMethods = new HashMap<>();
        classMethodMultimap = MultimapBuilder.hashKeys().arrayListValues().build();
    }

    public void registerEventHandler(Object clazz) {
        for (Method method : clazz.getClass().getMethods()) {

            if(method.isAnnotationPresent(EventSubscriber.class)) {
                if (method.getParameters().length > 1) {
                    throw new UnsupportedOperationException("Cannot have multiple parameters on a @EventSubscriber method! " + method.getName());
                }

                for (Class paramClass : method.getParameterTypes()) {
                    try {
                        paramClass.asSubclass(Event.class);
                    } catch (ClassCastException ex) {
                        throw new UnsupportedOperationException("Methods annotated with EventSubscriber argument must be a subtype of the Event interface", ex);
                    }
                    eventSubscriberMethods.put(method, clazz);
                    classMethodMultimap.put(paramClass, method);
                }
            }
        }
    }

    public void fireEvent(Event event) {
        for (Method method : classMethodMultimap.get(event.getClass())) {
            invokeMethod(eventSubscriberMethods.get(method), method, event);
        }
    }

    public <T extends CancelableEvent> void fireEvent(T event) {
        for (Method method : classMethodMultimap.get(event.getClass())) {
            invokeMethod(eventSubscriberMethods.get(method), method, event);
            if(event.isCanceled()) {
                break;
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
