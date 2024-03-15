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
interface Fncd_Observer {
    void update(Fncd_Event event);
}
// the logger will report state changes and how many instances of each stat was performed successfully 
class Logger implements Fncd_Observer {
    private int day;
    private final String fileName;
    // constrictor will create the new file 
    public Logger(int day) {
        this.day = day;
        // /Users/adrianornelasr/Desktop/OOAD/OOAD_SP23--Goins--Ornelas-Ruvalcaba/Logger-n-folderResults
        // this.fileName = "Logger-" + day + ".txt"; // using day as the file name
        this.fileName = "/Users/adrianornelasr/Desktop/OOAD/OOAD_SP23--Goins--Ornelas-Ruvalcaba/Logger-n-folderResults/Logger-" + day + ".txt";
        try {
            FileWriter writer = new FileWriter(this.fileName, false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
    private double staffMoneyEarned;
    private double fncdMoneyEarned;

    public Tracker() {
        this.staffMoneyEarned = 0;
        this.fncdMoneyEarned = 0;
    }
    // update looks for the type to add to and increments the amount 
    public void update(Fncd_Event event) {
        if (event.getType().equals("staff_money_earned")) {
            // make data into double to increment
            double tempStaff = (double) event.getData();
            this.staffMoneyEarned += tempStaff;
        } else if (event.getType().equals("fncd_money_earned")) {
            // make data into double to increment
            double tempFncd = (double) event.getData();
            this.fncdMoneyEarned += tempFncd;
        }
    }
    // print in terminal at the end 
    // https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Format-double-Java-printf-example#:~:text=It%20is%20a%20common%20requirement,double%20to%20two%20decimal%20places.
    public void printSummary(int day) {
        System.out.println("Tracker: Day " + day);
        System.out.printf("Total money earned by all Staff: $%.2f\n", staffMoneyEarned);
        System.out.printf("Total money earned by the FNCD: $%.2f\n", fncdMoneyEarned);
    }
}


