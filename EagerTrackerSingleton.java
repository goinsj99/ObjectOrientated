import java.io.*;
import java.util.*;

public class EagerTrackerSingleton {
    private static final EagerTrackerSingleton instance = new EagerTrackerSingleton();
    private Event_Publisher subscriber;

    private EagerTrackerSingleton(){
        subscriber = new Event_Publisher();
    }

    public static EagerTrackerSingleton getInstance() {
        return instance;
    }

    public void registerSub(Fncd_Observer observer) {
        subscriber.registerSub(observer);
    }

    public void removeSub(Fncd_Observer observer) {
        subscriber.removeSub(observer);
    }

}