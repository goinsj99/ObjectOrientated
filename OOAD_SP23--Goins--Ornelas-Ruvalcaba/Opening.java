import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream; 

public class Opening{
    private double currBudget = 0;
    private int internCount = 0;
    private int carCount;
    private int performanceCarCount;
    private int pickupCarCount;
    private int electricCarCount;
    private int motorcycleCount;
    private int monsterTruck;
    private List<Staff> interList = new ArrayList<>();

    Opening(FNCD fncd){
        this.internCount = threeInterns(fncd.getStaffList(), fncd);
        this.interList = retrunListInterns(fncd.getStaffList(), fncd);
        // defult 3 cars
        fncd.LoggerReport("      * Current Vehicle Owned ");
        fncd.LoggerReport("       * Regular Cars ");
        this.carCount = fourCars("Car",fncd);
        fncd.LoggerReport("       * Performance Cars ");
        this.performanceCarCount = fourCars("Performance Car", fncd);
        fncd.LoggerReport("       * PickUp Cars ");
        this.pickupCarCount = fourCars("Pickup Car", fncd);
        // new vehicles 
        fncd.LoggerReport("       * Electric Cars ");
        this.electricCarCount = this.pickupCarCount = fourCars("Electric Car", fncd);
        fncd.LoggerReport("       * Motorcycle ");
        this.motorcycleCount = this.pickupCarCount = fourCars("Motorcycle", fncd);
        fncd.LoggerReport("       * Monster Truck ");
        this.monsterTruck = this.pickupCarCount = fourCars("Monster Truck", fncd);
    }
    // check intern
    public int threeInterns(List<Staff> curentStaff, FNCD fncd){
        int count = 0;
        for(Staff staff: curentStaff){
            if(staff instanceof Intern){
                count++;
            }
        }
        fncd.LoggerReport("      * Current Intern Count " + count);
        return count;
    }
    public List<Staff> retrunListInterns(List<Staff> curentStaff, FNCD fncd){
        List<Staff> temp = new ArrayList<>();
        for(Staff staff: curentStaff){
            if(staff instanceof Intern){
                //fncd.LoggerReport("      * Intern Currently Working "+staff.getName());
                fncd.LoggerReport("          - "+staff.getName());
                temp.add(staff);
            }
        }
        return temp;
    }
    public int returnInternCount(){
        return this.internCount;
    }
    public List<Staff> returnList(){
        return this.interList;
    }
    public List<Staff> internHire(List<Staff> curentStaff, FNCD fncd){
        Staff tempstaffie;
        if(this.internCount < 3){
            while(this.internCount < 3){
                tempstaffie = fncd.createInternStaff();
                curentStaff.add(tempstaffie);
                fncd.LoggerReport("      * Intern Hired "+ tempstaffie.getName());
                this.internCount+=1;
            }
        }
        return curentStaff;
    }  
    // check Car
    public int fourCars(String carType, FNCD fncd){
        int count = 0;
        for(Vehicle s: fncd.getVehicleList()){
            if(s.getType() == carType){
                count++;
            }   
        }
        return count;
        
    }
    public List<Vehicle> AddVehicle(FNCD fncd){
        // now what every dosnt have 4 go until their is four each or buget reached
        boolean flag = false;
        while((this.carCount < 4 || this.performanceCarCount < 4 || this.pickupCarCount < 4 || this.electricCarCount < 4 || this.motorcycleCount < 4 || this.motorcycleCount < 4) || flag == true){
            if(this.carCount < 4){
                Vehicle tempCar = fncd.createCar();
                if(fncd.getOpBudget() - tempCar.getCost() < 0){
                    flag = true;
                    break;
                }else{
                    fncd.setOpBudget(fncd.getOpBudget() - tempCar.getCost());
                    fncd.addVehicle(tempCar);
                    fncd.LoggerReport("      * Bought Regular Car " + tempCar.getName());
                    this.carCount+=1;
                }
            }
            if(this.performanceCarCount < 4){
                Vehicle tempCar = fncd.createPerformanceCar();
                if(fncd.getOpBudget() - tempCar.getCost() < 0){
                    flag = true;
                    break;
                }else{
                    fncd.setOpBudget(fncd.getOpBudget() - tempCar.getCost());
                    fncd.addVehicle(tempCar);
                    fncd.LoggerReport("      * Bought Performance Car "+ tempCar.getName());
                    this.performanceCarCount+=1;
                }
            }
            if(this.pickupCarCount < 4){
                Vehicle tempCar = fncd.createPickupCar();
                if(fncd.getOpBudget() - tempCar.getCost() < 0){
                    flag = true;
                    break;
                }else{
                    fncd.setOpBudget(fncd.getOpBudget() - tempCar.getCost());
                    fncd.addVehicle(tempCar);
                    fncd.LoggerReport("      * Adding PickUp Car "+ tempCar.getName());
                    this.pickupCarCount+=1;
                }
            }
            if(this.electricCarCount < 4){
                Vehicle tempCar = fncd.createElectricCar();
                if(fncd.getOpBudget() - tempCar.getCost() < 0){
                    flag = true;
                    break;
                }else{
                    fncd.setOpBudget(fncd.getOpBudget() - tempCar.getCost());
                    fncd.addVehicle(tempCar);
                    fncd.LoggerReport("      * Adding Electric Car "+ tempCar.getName());
                    this.pickupCarCount+=1;
                }
            }
            if(this.motorcycleCount < 4){
                Vehicle tempCar = fncd.createMotorcycle();
                if(fncd.getOpBudget() - tempCar.getCost() < 0){
                    flag = true;
                    break;
                }else{
                    fncd.setOpBudget(fncd.getOpBudget() - tempCar.getCost());
                    fncd.addVehicle(tempCar);
                    fncd.LoggerReport("      * Adding Motorcycle "+ tempCar.getName());
                    this.pickupCarCount+=1;
                }
            }
            if(this.monsterTruck < 4){
                Vehicle tempCar = fncd.createMonsterTruck();
                if(fncd.getOpBudget() - tempCar.getCost() < 0){
                    flag = true;
                    break;
                }else{
                    fncd.setOpBudget(fncd.getOpBudget() - tempCar.getCost());
                    fncd.addVehicle(tempCar);
                    fncd.LoggerReport("      * Adding Monster Truck "+ tempCar.getName());
                    this.pickupCarCount+=1;
                }
            }
        }
        fncd.LoggerReport("");
        return fncd.getVehicleList();
    }
}

