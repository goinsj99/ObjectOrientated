import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream; 
import java.text.DecimalFormat;

public class Selling {
    private String weekDay;
    private Double buyType;
    private String vehicleChance;
    private String vehicleType;
    private Double initialSale;
    private Double saleChance;
    private List<Vehicle> tempVehicleList;
    public List<String> vehicleChances = Arrays.asList("Just looking", "Wants one", "Needs one");
    public List<String> vehicleTypes = Arrays.asList("Performance Car", "Car", "Pickup Car");
    public List<Staff> salesStaff;
    public int numOfBuys;
    private Double saleTemp;
    private Boolean car4Sale;
    private Double commissionBonus;
    private int sellingCount;

    Selling() {
        this.tempVehicleList = new ArrayList();
        this.saleChance = chanceLooking();
        this.saleTemp = 0.0;
        this.salesStaff = new ArrayList();
        this.car4Sale = false;
        this.sellingCount = 0;
    }
    public Staff whoSold(FNCD fncd) {
        List<Staff> staffList = fncd.getStaffList();
        Staff tempStaff;
        for (Staff staff : staffList) {
            if (staff instanceof SalesPerson) {
                this.salesStaff.add(staff);
            }
        }
        if(this.salesStaff.size() == 0){
            //System.out.printf("      * ! No current Sales person works here !%s\n");
            return null;
        }
        Random randoms = new Random();
        int temp = randoms.nextInt(salesStaff.size());

        tempStaff = salesStaff.get(temp);
        return tempStaff;
    }
    //random chance for preferred car
    public String chooseRandomCar() {
        Random random1 = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int temp = random1.nextInt(vehicleTypes.size());

        String vehicleType1 = vehicleTypes.get(temp);
        return vehicleType1;
    }
    //random chance for looking, wants one, needs one
    public Double chanceLooking() {
        Random random2 = new Random();
        buyType = random2.nextDouble();
        double ranDob = 0;
        if (buyType < 0.1) {
            ranDob = 0.1;
        } else if (buyType < 0.4) {
            ranDob = 0.4;
        } else {
            ranDob = 0.7;
        }
        return ranDob;
    }
    //highest selling vehicle
    public Vehicle mostExpensive(FNCD fncd) {
        String tempt = chooseRandomCar();
        Double saleTemp1 = 0.0;
        Vehicle tempCar = new Vehicle("", "", "");

        for (Vehicle car : fncd.getVehicleList()) {
            if (car.getCondition() != "Broken" && car.getType() == tempt) {
                if (saleTemp1 <= car.getSalesPrice()) {
                    saleTemp1 = car.getSalesPrice();
                    tempCar = car;
                }
                this.car4Sale = false;
            }else if (car.getCondition() != "Broken") {
                if (saleTemp <= car.getSalesPrice()) {
                    saleTemp = car.getSalesPrice();
                    tempCar = car;
                }
                this.car4Sale = true;
            }
        }
        return tempCar;
    }
    public List<Vehicle> sellThisCar(FNCD fncd) {
        Staff tempStaff;
        List<Vehicle> VehicleList = fncd.getVehicleList();
        List<Staff> staffList = fncd.getStaffList();
        Vehicle sVehicle = mostExpensive(fncd);

        if(sVehicle.getVim() == ""){
            fncd.LoggerReport("            * Didn't Find a Valid Vehicle!");
            return fncd.getSoldVehicle();
        }else{
            if (sVehicle.getCondition() == "New") {
                this.saleChance += 0.1;
                if (this.car4Sale == true) {
                    this.saleChance -= 0.2;
                }
            } else if (sVehicle.getCleanliness() == "Sparkling") {
                this.saleChance += 0.1;
                if (this.car4Sale == true) {
                    this.saleChance -= 0.2;
                }
            }
            //selling vehicle and removing/adding to appropriate list
            Random random2 = new Random();
            Double temp3 = random2.nextDouble();
            //System.out.printf("This is the sales Chance: %f | %f", this.saleChance, temp3);
            if (temp3 < this.saleChance) {
                
                fncd.addVehiclesSold(sVehicle);
                VehicleList.remove(sVehicle);
                tempStaff = whoSold(fncd);
                if(tempStaff == null){
                    fncd.LoggerReport("            * Customer Interested but No current Sales person works here !");
                    return fncd.getSoldVehicle();
                }
                //Double bnus = sVehicle.getVehicleBonus();
                for(Staff staff : staffList) {
                    if(staff.getName() == tempStaff.getName()) {
                        // add decorator 
                        Vehicle_Interface currCar = new Selling_Vehicle(sVehicle);
                        fncd.LoggerReport("            * You are buying "+currCar.getData()+" Would you like to add on \n             + Extended_Warranty\n             + Undercoating\n             + Road Rescue Coverage\n             + and/or Radio?");
                        double customerDecisionAddON = random2.nextDouble();
                        // road rescue coverage 
                        if(customerDecisionAddON < 0.05){
                            fncd.LoggerReport("                = Great choice in adding Road Rescue Coverage!");
                            currCar = new Road_Rescue_Coverage(currCar);
                        }
                        // undercoating 
                        else if(customerDecisionAddON < 0.10){
                            fncd.LoggerReport("                = Great choice in adding Undercoating!");
                            currCar = new Undercoating(currCar);
                        }
                        // extended warrenty
                        else if(customerDecisionAddON < 0.25){
                            fncd.LoggerReport("                = Great choice in adding Extended Warrenty!");
                            currCar = new Extended_Warranty(currCar);
                        }
                        // satellite radio
                        else if(customerDecisionAddON < 0.40){
                            fncd.LoggerReport("                = Great choice in adding Satellite Radio!");
                            currCar = new Radio(currCar);
                        }else{
                            fncd.LoggerReport("                = I understand, Cars are already expensive!");
                        }

                        staff.setBonus(staff.getBonus() + staff.getBonus());
                        fncd.setOpBudget(fncd.getOpBudget() + currCar.getSellsPrice());
                        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
                        // used to round price 
                        DecimalFormat df = new DecimalFormat("##.00");
                        fncd.LoggerReport("            * Vehicle was Sold "+currCar.getData()+", at a total price of "+ df.format(currCar.getSellsPrice()) +". It was fixed by "+staff.getName());

                        this.sellingCount++;
                        double curr = fncd.getFncdEarning();
                        fncd.setFncdEarning(curr+=currCar.getSellsPrice());
                    }
                }
            }else{
                fncd.LoggerReport("            * Customer Didnt Want A Car");
            }
            fncd.LoggerReport("");
            return fncd.getSoldVehicle();
        }
    }
    public int getSellCount(){
        return this.sellingCount;
    }
}
