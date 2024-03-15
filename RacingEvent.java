import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream; 

// should occur only sunday and wednesday
public  class RacingEvent{
    private List<Vehicle> carList = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();

    RacingEvent(FNCD fncd){
        this.carList = racableCars(fncd);
        this.staffList = racingStaff(fncd);
    }

    // can only race on wendsday and sunday
    public Boolean racingDay(FNCD fncd){
        List<Vehicle> VehicleList = fncd.getVehicleList();
        List<Staff> sList = fncd.getStaffList();
        int index = 0;
        // check if we are on a day to race
        fncd.LoggerReport("Racing... ");
        if(fncd.getDay() == "Wensday" || fncd.getDay() == "Sunday"){
            fncd.LoggerReport("  - Racing Day");
            for(Vehicle car: VehicleList){
                for(Staff staff: sList){
                    Random random = new Random();
                    if(index >= this.staffList.size() || index == this.carList.size()){
                        break;
                    }
                    else if(car == this.carList.get(index) && staff == this.staffList.get(index)){
                        int place = random.nextInt(20) + 1;
                        // win
                        if(place == 1 || place == 2 || place == 3){
                            car.addWinCount();
                            car.setSalesPrice(car.getSalesPrice()*1.10);
                            staff.setBonus(staff.getBonus()+300);
                            double curr = fncd.getStaffTotalEarn();
                            fncd.setStaffTotalEarn(curr+=staff.getBonus()+300);
                            fncd.LoggerReport("      * Vehicle that won in the race: "+car.getName()+" came in position "+place+" with a win count of "+car.getWinCount()+". The Driver was "+staff.getName());
                            index++;
                        // lose
                        }else{
                            car.setCondition("Broken");
                            double injuryChance = random.nextDouble();
                            // 30% chance person is injured
                            if(injuryChance < 0.30){
                                if(staff instanceof Driver){
                                    // set driver to injured 
                                    Driver driver = (Driver) staff;
                                    driver.updateInjury();
                                    fncd.LoggerReport("      * Vehicle that lost in the race: "+car.getName()+" came in position "+place+" with a win count of "+car.getWinCount()+". The Injured Driver was "+staff.getName());
                                }
                            }else{
                                fncd.LoggerReport("      * Vehicle that lost in the race: "+car.getName()+" came in position "+place+" with a win count of "+car.getWinCount()+". The Driver was "+staff.getName());
                            }
                        }
                        index++;
                    }
                    if(index == this.carList.size()){
                        break;
                    }
                }
                if(index == this.carList.size()){
                    break;
                }
            }
            fncd.LoggerReport("");
            return true;
        }else{
            fncd.LoggerReport("  - You will not be racing today!");
            fncd.LoggerReport("");
            return false;
        }

    } 
    public List<Staff> racingStaff(FNCD fncd){
        Random random1 = new Random();
        List<Staff> staffList = fncd.getStaffList();
        List<Staff> sL = new ArrayList();
        // check vehicles race elagability 
        for(Staff person: staffList){
            if(person instanceof Driver){
                sL.add(person);
                //fncd.LoggerReport("Diver Added to list "+person.getName());
            }
        }
        if(sL.size() > 3 && carList.size() == 3){
                int index1 = random1.nextInt(sL.size());
                int index2 = random1.nextInt(sL.size());
                int index3 = random1.nextInt(sL.size());
            List<Staff> threestaff = new ArrayList();
                threestaff.add(sL.get(index1));
                threestaff.add(sL.get(index2));
                threestaff.add(sL.get(index3));
            return threestaff;
        }else if(sL.size() > 3 && carList.size() < 3){
            List<Staff> threestaff = new ArrayList();
            for(int i = 0; i < carList.size(); i++){
                int index = random1.nextInt(sL.size());
                threestaff.add(sL.get(index));
            }
            return threestaff;
        }else{
            return sL;
        }
    }
    public List<Vehicle> racableCars(FNCD fncd){
        List<Vehicle> vehicleList = fncd.getVehicleList();
        List<Vehicle> carL = new ArrayList();
        // check vehicles race elagability 
        for(Vehicle car: vehicleList){
            if(car instanceof Car || car instanceof Electric_Car){
                continue;
            }else{
                if(car.getCondition() == "Broken"){
                    continue;
                }else{
                    carL.add(car);
                }
            }
        }
        if(carL.size() > 3){
            Random random1 = new Random();
                int index1 = random1.nextInt(carL.size());
                int index2 = random1.nextInt(carL.size());
                int index3 = random1.nextInt(carL.size());
            List<Vehicle> threecars = new ArrayList();
                threecars.add(carL.get(index1));
                threecars.add(carL.get(index2));
                threecars.add(carL.get(index3));
                //fncd.LoggerReport("Vehicle Added to list "+carL.get(index1).getName());
                //fncd.LoggerReport("Vehicle Added to list "+carL.get(index2).getName());
                //fncd.LoggerReport("Vehicle Added to list "+carL.get(index3).getName());
            return threecars;
        }else{
            return carL;
        }
    }
}  
