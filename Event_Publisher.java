import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream;  


// sorced used to creat observer patteren 
// https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html
// event is used to create an instance of what should be published by a certain event
// will handle events to the subscriberss
public class Event_Publisher implements Event_Manager {
    private List<Fncd_Observer> sub;
    
    Event_Publisher(){
        this.sub = new ArrayList();
    }
    // add the event to subscriber
    public void registerSub(Fncd_Observer observer) {
        sub.add(observer);
    }
    // remove subscriber
    public void removeSub(Fncd_Observer observer) {
        sub.remove(observer);
    }
    // push the event to the subscriber
    public void pushSub(Fncd_Event event) {
        for (Fncd_Observer observer : sub) {
            observer.update(event);
        }
    }
    // push the event to the subscriber to print at end 
    public void publishEvent(String type, String message, Object data) {
        Fncd_Event event = new Fncd_Event(type, message, data);
        pushSub(event);
    }
}
class Fncd_Event {
    // holds message, type, and data associated 
    private String type;
    private String message;
    private Object data;
    // constructor
    public Fncd_Event(String type, String message, Object data){
        this.type = type;
        this.message = message;
        this.data = data;
    }
    // get type 
    public String getType(){
        return type;
    }
    // set type
    public void setType(String newType){
        this.type = newType;
    }
    public String getMessage(){
        return message;
    }
    // set message
    public void setMessage(String newMessage){
        this.message = newMessage;
    }
    // get data
    public Object getData(){
        return data;
    }
    // set type
    public void setData(String newData){
        this.data = newData;
    }
}
// the event manager will handle the fncd subscribers
interface Event_Manager {
    // register a new subscriber
    void registerSub(Fncd_Observer sub);
    // remove subscriber
    void removeSub(Fncd_Observer sub);
    // push a new subscriber 
    void pushSub(Fncd_Event event);
}
// set up observer/su to update each event added


// Eager Singleton

// class EagerTrackerSingleton {
//     private static final EagerTrackerSingleton tracker = new EagerTrackerSingleton();

//     private EagerTrackerSingleton(){}

//     public static EagerTrackerSingleton getInstance() {
//         return tracker;
//     }
// }

// // Lazy Singleton that's thread safe

// class LazyLoggerSingleton {
//     private static LazyLoggerSingleton logger;

//     private LazyLoggerSingleton(){}

//     public static LazyLoggerSingleton getInstance() {
//         if (logger == null) {
//             synchronized (LazyLoggerSingleton.class) {
//                 if (logger == null) {
//                     logger = new LazyLoggerSingleton();
//                 }
//             }
//         }
//         return logger;
//     }
// }
interface Fncd_Observer {
    void update(Fncd_Event event);
}
// the logger will report state changes and how many instances of each stat was performed successfully 
class Logger implements Fncd_Observer {
    private int day;
    private final String fileName;
    // constrictor will create the new file 
    public Logger(int day) {
        // /Users/adrianornelasr/Desktop/OOAD/OOAD_SP23--Goins--Ornelas-Ruvalcaba/Logger-n-folderResults
        // this.fileName = "Logger-" + day + ".txt"; // using day as the file name
        this.day = day;
        this.fileName = "Logger-n-folderResults/Logger-" + day + ".txt";
        try {
            FileWriter writer = new FileWriter(this.fileName, false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public void setDay(FNCD fncd) {
    //     this.day = fncd.dayNum;
    // }
    // public int getDay() {
    //     return this.day;
    // }
    // be able to write to the file with update given
    public void update(Fncd_Event event) {
        String logMessage = "Type: " + event.getType() + ", Message: " + event.getMessage() + ", Data: " + event.getData();
        // write logMessage to file
        try (FileWriter file = new FileWriter(this.fileName, true); 
        BufferedWriter buffer = new BufferedWriter(file); 
        PrintWriter write = new PrintWriter(buffer);) { 
            write.println(logMessage); 
        } catch (IOException i) { 
            i.printStackTrace(); 
        }
    }
}

// tracker will keep track of how much money is earned by staff and fncd
class Tracker implements Fncd_Observer {
    private double southStaffMoneyEarned;
    private double southFncdMoneyEarned;
    private double northStaffMoneyEarned;
    private double northFncdMoneyEarned;

    public Tracker() {
        this.southStaffMoneyEarned = 0;
        this.southFncdMoneyEarned = 0;
        this.northStaffMoneyEarned = 0;
        this.northFncdMoneyEarned = 0;
    }
    // update looks for the type to add to and increments the amount 
    public void update(Fncd_Event event) {
        String northKeyword = "north";
        String southKeyword = "south";
        if (event.getType().equals("staff_money_earned")) {
            if (event.getMessage().toLowerCase().indexOf(northKeyword.toLowerCase()) != -1) {
                double tempStaff = (double) event.getData();
                this.northStaffMoneyEarned += tempStaff;
            } else if (event.getMessage().toLowerCase().indexOf(southKeyword.toLowerCase()) != -1) {
                double tempStaff = (double) event.getData();
                this.southStaffMoneyEarned += tempStaff;
            } 
        }
        if (event.getType().equals("fncd_money_earned")) {
            if (event.getMessage().toLowerCase().indexOf(northKeyword.toLowerCase()) != -1) {
                // make data into double to increment
                double tempFncd = (double) event.getData();
                this.northFncdMoneyEarned += tempFncd;
            } else if (event.getMessage().toLowerCase().indexOf(southKeyword.toLowerCase()) != -1) {
                double tempFncd = (double) event.getData();
                this.southFncdMoneyEarned += tempFncd;
            } 

        }

    }
    // print in terminal at the end 
    // https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Format-double-Java-printf-example#:~:text=It%20is%20a%20common%20requirement,double%20to%20two%20decimal%20places.
    public void printSummary(int day) {
        System.out.println("Tracker: Day " + day);
        System.out.printf("***FNCD North*** Total money earned by all Staff: $%.2f\n", northStaffMoneyEarned);
        System.out.printf("***FNCD North*** Total money earned by the FNCD: $%.2f\n", northFncdMoneyEarned);
        System.out.printf("***FNCD South*** Total money earned by all Staff: $%.2f\n", southStaffMoneyEarned);
        System.out.printf("***FNCD South*** Total money earned by the FNCD: $%.2f\n", southFncdMoneyEarned);
    }
}




