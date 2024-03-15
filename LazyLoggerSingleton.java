import java.io.*;
import java.util.*;

public class LazyLoggerSingleton {
    private static LazyLoggerSingleton instance = null;
    private Event_Publisher subscriber;
    private List<Fncd_Observer> sub;
    private LazyLoggerSingleton(){
        subscriber = new Event_Publisher();
    }

    public static synchronized LazyLoggerSingleton getInstance() {
        if (instance == null) {    
            instance = new LazyLoggerSingleton();
        }
        return instance;
    }

    public void registerSub(Fncd_Observer observer) {
        subscriber.registerSub(observer);
    }

    public void removeSub(Fncd_Observer observer) {
        subscriber.removeSub(observer);
    }

    public void pushSub(Fncd_Event event) {
        for (Fncd_Observer observer : sub) {
            observer.update(event);
        }
    }

    public void publishEvent(String type, String message, Object data) {
        Fncd_Event event = new Fncd_Event(type, message, data);
        pushSub(event);
    }
}
