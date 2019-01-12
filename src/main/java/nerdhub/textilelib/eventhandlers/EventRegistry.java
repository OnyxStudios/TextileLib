package nerdhub.textilelib.eventhandlers;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventRegistry {

    public static Map<Method, Object> eventSubscriberMethods = new HashMap<>();
    public static Multimap<Class, Method> classMethodMultimap = LinkedListMultimap.create();

    public static void registerEventHandler(Object clazz) {
        for (Method method : clazz.getClass().getMethods()) {

            if(method.isAnnotationPresent(EventSubscriber.class)) {
                if (method.getParameters().length > 1) {
                    throw new UnsupportedOperationException("Cannot have multiple parameters on a @EventSubscriber method! " + method.getName());
                }

                for (Class paramClass : method.getParameterTypes()) {
                    eventSubscriberMethods.put(method, clazz);
                    classMethodMultimap.put(paramClass, method);
                }
            }
        }
    }

    public static void fireEvent(Event event) {
        for (Method method : classMethodMultimap.get(event.getClass())) {
            invokeMethod(eventSubscriberMethods.get(method), method, event);
        }
    }

    public static void invokeMethod(Object classOBject, Method method, Event event) {
        try {
            method.invoke(classOBject, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
