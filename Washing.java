import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream; 

public class Washing{
    private int randomChoice1;
    private int randomChoice2;
    private String weekDay;
    private int washCount;
    private List<Staff> staffList = new ArrayList<>();


    Washing(FNCD fncd){
        this.randomChoice1 = 0;
        this.randomChoice2 = 0;
        this.washCount = 0;
        this.staffList = retrunListInterns(fncd.getStaffList(), fncd);
    }
    // code to wash the vehicle
    public void washVehicle(FNCD fncd) {
        // get two random staff
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int internCount1 = 0;

        for(Staff intern: this.staffList){
            // only can wash 2 cars per day
            while(internCount1 < 2){
                fncd.LoggerReport("      * "+intern.getName()+" Washing Report");
                // start cleaning dirty cars
                for(Vehicle car: fncd.getVehicleList()){
                    if(car.getCleanliness() == "Dirty"){
                        // %80 chance on becoming clean %10 chance of becoming sparkling
                        double randomNumber = random.nextDouble();
                        if(randomNumber < 0.1){
                            fncd.LoggerReport("          - Vehicle was dirty and is now Sparkling "+car.getName());
                            car.setCleanliness("Sparkling");
                            intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                            internCount1++;

                            this.washCount++;
                            double curr = fncd.getStaffTotalEarn();
                            fncd.setStaffTotalEarn(curr+=car.getVehicleBonus());
                            // increase bonus intern.bonus = 
                        }else if(randomNumber < 0.8){
                            fncd.LoggerReport("          - Vehicle was dirty and is now Clean "+car.getName());
                            car.setCleanliness("Clean");
                            internCount1++;
                            this.washCount++;
                        }else{
                            //System.out.printf("Washing did not have an affect on the vehicle 1\n");
                            internCount1++;
                        }
                    }
                    if(internCount1 == 2){
                        break;
                    }
                }
                if(internCount1 == 2){
                    break;
                }
                // move on to clean cars
                for(Vehicle car: fncd.getVehicleList()){
                    if(car.getCleanliness() == "Clean"){
                        // %5 chance of becoming dirty, %30 chance on becmoming sparkling
                        double randomNumber = random.nextDouble();
                        if(randomNumber < 0.05){
                            fncd.LoggerReport("          - Vehicle was Clean and is now Dirty "+car.getName());
                            car.setCleanliness("Dirty");
                            internCount1++;
                            this.washCount++;
                        }else if(randomNumber < 0.3){
                            fncd.LoggerReport("          - Vehicle was Clean and is now Sparkling "+car.getName());
                            car.setCleanliness("Sparkling");
                            intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                            internCount1++;

                            this.washCount++;
                            double curr = fncd.getStaffTotalEarn();
                            fncd.setStaffTotalEarn(curr+=car.getVehicleBonus());
                        }else{
                            //System.out.printf("Washing did not have an affect on the vehicle 2 \n");
                            internCount1++;
                        }
                    }
                    if(internCount1 == 2){
                        break;
                    }
                }
            }
            internCount1=0;
        }
        fncd.LoggerReport("");
    }
    public List<Staff> retrunListInterns(List<Staff> curentStaff, FNCD fncd){
        int count = 0;
        List<Staff> temp = new ArrayList<>();
        fncd.LoggerReport("      * Intern Currently Working ");
        for(Staff staff: curentStaff){
            if(staff instanceof Intern){
                fncd.LoggerReport("          - "+ staff.getName());
                temp.add(staff);
                count+=1;
            }
            if(count == 3){
                break;
            }
        }
        return temp;
    }
    public int getWashCount(){
        return this.washCount;
    }
    public void washing(FNCD fncd, double cleanChance, double sparkingChance, double dirtyChance, double brokenChance, double newChance){
        
    }
}
