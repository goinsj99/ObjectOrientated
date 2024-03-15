import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream;  

public class FNCD {
    // added days of week
    private List<String> weekDays = Arrays.asList("Monday", "Tuesday", "Wensday", "Thursday", "Friday", "Saturday", "Sunday");
    private String currDay;
    private int dayIndex;
    public int dayNum = 1;
    public Double opBudget;
    public int currIDCount;
    private double staffTotal;
    private double fncdTotal;
    public List<Staff> staffList = new ArrayList();
    public List<Vehicle> vehicleList = new ArrayList();
    public List<String> staffnames= new ArrayList();;
    public List<String> CarNames = new ArrayList();
    public List<String> PerformanceCarNames = new ArrayList();
    public List<String> PickupCarNames = new ArrayList();
    public List<String> ElectricCarNames = new ArrayList();
    public List<String> MotorcycleNames = new ArrayList();
    public List<String> MonsterTruckNames = new ArrayList();
    public List<String> HybridCarNames = new ArrayList();
    public List<String> MopedNames = new ArrayList();
    public List<String> CunstructionNames = new ArrayList();
    public List<String> report = Arrays.asList();
    private List<Vehicle> vehiclesSold;

    //    report parameters need to be filled
    public Integer staffCount;

    // used to delete logger-n.text to any new instance of fncd 
    //https://www.baeldung.com/java-delete-file-contents 
    public FNCD(Double opBudget){
        // start on monday 
        this.dayNum++;
        this.currDay = weekDays.get(0);
        this.dayIndex = 0;
        this.opBudget = opBudget;
        this.currIDCount = 101017;
        this.CarNames = setCarNmaes(CarNames, "nameFilesUsed/RegularCars.txt");
        this.PerformanceCarNames = setCarNmaes(PerformanceCarNames, "nameFilesUsed/PerformanceCar.txt");
        this.PickupCarNames = setCarNmaes(PickupCarNames, "nameFilesUsed/PickupCars.txt");
        this.ElectricCarNames = setCarNmaes(ElectricCarNames, "nameFilesUsed/ElectricCarNames.txt");
        this.MotorcycleNames = setCarNmaes(MotorcycleNames, "nameFilesUsed/MotorcycleName.txt");
        this.MonsterTruckNames = setCarNmaes(MonsterTruckNames, "nameFilesUsed/MonsterTruckName.txt");
        this.HybridCarNames = setCarNmaes(HybridCarNames, "nameFilesUsed/HybridCarNames.txt");
        this.MopedNames = setCarNmaes(MopedNames, "nameFilesUsed/MonsterTruckName.txt");
        this.CunstructionNames = setCarNmaes(CunstructionNames, "nameFilesUsed/CunstructionCarNames.txt");
        this.staffnames =  setCarNmaes(staffnames, "nameFilesUsed/workerNames.csv");
        this.vehicleList = dayOneVehicle();
        this.staffList = dayOneStaff();
        this.staffCount = 0;
        this.report = null;
        this.vehiclesSold = new ArrayList<>();
        this.staffTotal = 0;
        this.fncdTotal = 0;
        try {
            FileWriter writer = new FileWriter("SimResults.txt", false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Setters
    // go to next day
    public void nextDay(){
        budgetCheck();
        if(this.dayIndex == 6){
            this.dayIndex = 0;
        }else{
            this.dayIndex += 1;
            this.currDay = weekDays.get(this.dayIndex);
        }
    }
    // check budget and incresse if needed
    public void budgetCheck(){
        if(this.getOpBudget() <= 1000){
            this.setOpBudget(10000.00);
        }
    }
    // load car names
    public List<String> setCarNmaes(List<String> currList, String filesname){
        try {
            File file = new File(filesname);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line1 = scanner.nextLine();
                currList.add(line1);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return currList;
    }
    // set new budget
    public void setOpBudget(Double opBudgett){
        this.opBudget = opBudgett;
    }
    public void addVehiclesSold(Vehicle car){
        this.vehiclesSold.add(car);
    }
    // add all vehicle in list
    public void setVehicleList(List<Vehicle> vehicleList){
        this.vehicleList = vehicleList;
    }
    // add all staff in list
    public void setStaffList(List<Staff> newstaffList){
        this.staffList = newstaffList;
    }
    // set new staff count
    public void setStaffCount(Integer staffCount){
        this.staffCount = staffCount;
    }
    // set a report
    public void setReport(List<String> report){
        this.report = report;
    }
    // add a staff to list
    public void addStaff(Staff newStaff){
        this.staffList.add(newStaff);
    }
    // add a vehicle to list
    public void addVehicle(Vehicle newCar){
        this.vehicleList.add(newCar);
    }
    // Getters
    // return day
    public String getDay(){
        return this.currDay;
    }
    // return budget
    public Double getOpBudget(){
        return this.opBudget;
    }
    public List<Vehicle> getSoldVehicle(){
        return this.vehiclesSold;
    }
    // return vehicle list
    public List<Vehicle> getVehicleList(){
        return this.vehicleList;
    }
    // return staff list
    public List<Staff>  getStaffList(){
        return this.staffList;
    }
    public Integer getStaffCount(){
        return this.staffCount;
    }
    public int getDayIndex(){
        return this.dayIndex;
    }
    public List<String> setReport(){
        return this.report;
    }
    // creates a random 5 digit id for each employee
    public int generateID(){
        return this.currIDCount++;
    }

    // create Monster Truck 
    // create Starting Staff
    public List<Staff> dayOneStaff(){
        List<Staff> listTemp = new ArrayList();
        Factory_Pattern fp = new Factory_Pattern();
        for(int i = 0; i < 3; i++){
            listTemp.add(fp.createNewStaff(CreatType.SALES, this));
            listTemp.add(fp.createNewStaff(CreatType.MACHANIC, this));
            listTemp.add(fp.createNewStaff(CreatType.INTERN, this));
            listTemp.add(fp.createNewStaff(CreatType.DRIVER, this));
        }
        return listTemp;
    }
    // create Starting Vehicles
    public List<Vehicle> dayOneVehicle(){
        List<Vehicle> listtemp = new ArrayList<>();
        Factory_Pattern fp = new Factory_Pattern();
        for(int i = 0; i < 6; i++){
            listtemp.add(fp.createNewVehicle(CreatType.CAR, this));
            listtemp.add(fp.createNewVehicle(CreatType.PERFORMANCE, this));
            listtemp.add(fp.createNewVehicle(CreatType.PICKUP, this));
            listtemp.add(fp.createNewVehicle(CreatType.ELECTRIC, this));
            listtemp.add(fp.createNewVehicle(CreatType.MOTORCYCLE, this));
            listtemp.add(fp.createNewVehicle(CreatType.MONSTERTRUCK, this));
            listtemp.add(fp.createNewVehicle(CreatType.HYBRID, this));
            listtemp.add(fp.createNewVehicle(CreatType.MOPED, this));
            listtemp.add(fp.createNewVehicle(CreatType.CUNSTRUCTION, this));
        }
        return listtemp;
    }
    // to create loging to a file, I used this source 
    // https://www.java67.com/2015/07/how-to-append-text-to-existing-file-in-java-example.html
    public void LoggerReport(String line){
        try (FileWriter file = new FileWriter("SimResults.txt", true); 
        BufferedWriter buffer = new BufferedWriter(file); 
        PrintWriter write = new PrintWriter(buffer);) { 
            write.println(line); 
        } catch (IOException i) { 
            i.printStackTrace(); 
        }
    }


    public double getStaffTotalEarn(){
        return this.staffTotal;
    }
    public void setStaffTotalEarn(double newAmount){
        this.staffTotal=newAmount;
    }
    public double getFncdEarning(){
        return this.fncdTotal;
    }
    public void setFncdEarning(double newAmount){
        this.fncdTotal = newAmount;
    }

}
