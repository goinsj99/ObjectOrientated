import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream; 

public class Repair{
    private double repairChance;
    private String day;
    private double bonus;
    private int repairCount;

    Repair(){
        this.repairChance = 0.8;
        this.bonus = 0.0;
        this.repairCount = 0;
    }

    public void repairVehicle(List<Vehicle> VehicleList, Staff mech, FNCD fncd) {
        // repair the vehicle
        int mecCount = 0;
        fncd.LoggerReport("      * "+mech.getName()+" Repairing Report");
        while(mecCount < 2){
            for(Vehicle vehicle: VehicleList){
                Random random = new Random();
                double randomNumber = random.nextDouble();
                // upgrade condtition
                if(vehicle.getCondition() != "New"){
                // mechanic can repair
                    if(randomNumber < 0.80){
                        if(vehicle.getCondition() == "Used"){
                            fncd.LoggerReport("          - Vehicle was Used and is now Like New "+vehicle.getName());
                            vehicle.setCondition("Like New");
                            vehicle.setSalesPrice(vehicle.getSalesPrice() * 1.5);
                            this.repairCount++;
                        }else if(vehicle.getCondition() == "Broken"){
                            fncd.LoggerReport("          - Vehicle was Broken and is now Used "+vehicle.getName());
                            vehicle.setCondition("Used");
                            vehicle.setSalesPrice(vehicle.getSalesPrice() * 1.25);
                            this.repairCount++;
                        }
                        mech.setBonusTemp(mech.getBonusTemp()+vehicle.getVehicleBonus());
                        double curr = fncd.getStaffTotalEarn();
                        fncd.setStaffTotalEarn(curr+=mech.getBonusTemp());
                    }
                    if(vehicle.getCleanliness() != "Dirty"){
                        if(vehicle.getCleanliness() == "Sparkling"){
                            fncd.LoggerReport("          - Vehicle was Sparkling and is now clean "+vehicle.getName());
                            vehicle.setCleanliness("Clean");
                            this.repairCount++;
                        }else if(vehicle.getCleanliness() == "Clean"){
                            fncd.LoggerReport("          - Vehicle was Clean and is now Dirty "+vehicle.getName());
                            vehicle.setCleanliness("Dirty");
                            this.repairCount++;
                        }
                    }
                    mecCount++;
                    if(mecCount == 2){
                        break;
                    }
                }
            }
        }
        fncd.LoggerReport("");
    }
    public int getRepairCount(){
        return this.repairCount;
    }
}
