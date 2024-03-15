import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;


//import VehicleClass.Car;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

// opening
class Opening{
    private int internCount;
    private List<Staff> staffList;

    Opening(FNCD fncd){
        this.internCount = 0;
        this.staffList = fncd.getStaffList();
        // check current intern count
        for(Staff staff: this.staffList){
            if(staff instanceof Intern){
                internCount+=1;
            }
        }
        // add back 3 interns before continuing
        if(internCount < 3){
            while(internCount != 3){
                fncd.addStaff(fncd.createInternStaff());
            }
        }

    }
}

class Selling {
    private String weekDay;
    private Double buyType;
    private String vehicleChance;
    private String vehicleType;
    private Double initialSale;
    private Double saleChance;
    private List<Vehicle> tempVehicleList;
    public List<String> vehicleChances = Arrays.asList("Just looking", "Wants one", "Needs one");
    public List<String> vehicleTypes = Arrays.asList("Performance", "Cars", "Pickups");
    public List<Staff> salesStaff;
    public List<Vehicle> vehiclesSold;
    private Double saleTemp;
    private Boolean car4Sale;
    private Double commissionBonus;

    Selling(String weekDay, Double buyType, String vehicleChance, String vehicleType, Double initialSale, Boolean car4Sale) {
        this.weekDay = weekDay;
        this.buyType = 0.0;
        this.vehicleChance = vehicleChance;
        this.vehicleType = vehicleType;
        this.initialSale = initialSale;
        this.tempVehicleList = new ArrayList();
        this.saleChance = 0.0;
        this.vehiclesSold = new ArrayList();
        this.car4Sale = car4Sale;
        this.saleTemp = 0.0;
        this.salesStaff = new ArrayList();

    }

//    public void sellVehicle(Staff Staff) {
//        Random randomBuy = new Random();
//        double randomNumber = randomBuy.nextDouble();
//        Random randomCarType = new Random();
//        double randomCar = randomCarType.nextDouble();
//    }

    public Staff whoSold(FNCD fncd) {
        List<Staff> staffList = fncd.getStaffList();
        Staff tempStaff;
        for (Staff staff : staffList) {
            if (staff.getPosition() == "Sales") {
                salesStaff.add(staff);
            }
        }
        Random randoms = new Random();
        int temp = randoms.nextInt(salesStaff.size());
        tempStaff = salesStaff.get(temp);
        return tempStaff;
    }

    //random chance for preferred car
    public String chooseRandomCar(double randomCar) {
        Random random1 = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int temp = random1.nextInt(vehicleTypes.size());
        this.vehicleType = vehicleTypes.get(temp);
        if (this.vehicleType == "Performance") {
            this.vehicleType = "Performance";
        } else if (this.vehicleType == "Cars") {
            this.vehicleType = "Cars";
        } else {
            this.vehicleType = "Pickups";
        }
        return vehicleType;
    }

    //random chance for looking, wants one, needs one
    public Double chanceLooking(double randomNumber) {
        Random random2 = new Random();
        buyType = random2.nextDouble();
        if (this.buyType < 0.1) {
            this.saleChance = 0.1;
        } else if (this.buyType < 0.4) {
            this.saleChance = 0.4;
        } else {
            this.saleChance = 0.7;
        }
        return saleChance;
    }

    //highest selling vehicle
    public Vehicle mostExpensive(FNCD fncd) {
        List<Vehicle> VehicleList = fncd.getVehicleList();
        Vehicle tempCar = new Vehicle("", "", "");
        for (Vehicle car : VehicleList) {
            if (car.getCondition() != "Broken" && car.getType() == vehicleType) {
                if (saleTemp <= car.getSalesPrice()) {
                    saleTemp = car.getSalesPrice();
                    tempCar = car;
                }
                car4Sale = false;
            } else if (car.getCondition() != "Broken" && car.getType() != vehicleType) {
                if (saleTemp <= car.getSalesPrice()) {
                    saleTemp = car.getSalesPrice();
                    tempCar = car;
                }
                car4Sale = true;
            } else {
                System.out.println("No cars for sale");
            }
        }
        return tempCar;
    }

    public Vehicle sellThisCar(FNCD fncd) {
        Staff tempStaff;
        List<Vehicle> VehicleList = fncd.getVehicleList();
        Vehicle sVehicle;
        sVehicle = mostExpensive(fncd);
        if (sVehicle.getCondition() == "New") {
            saleChance += 0.1;
            if (car4Sale == true) {
                saleChance -= 0.2;
            }
        } else if (sVehicle.getCleanliness() == "Sparkling") {
            saleChance += 0.1;
            if (car4Sale == true) {
                saleChance -= 0.2;
            }
        }
//        selling vehicle and removing/adding to appropriate list
        Random random2 = new Random();
        Double temp3 = random2.nextDouble();
        Double bnus;
        if (temp3 < saleChance) {
            vehiclesSold.add(sVehicle);
            VehicleList.remove(sVehicle);
            tempStaff = whoSold(fncd);
            bnus = sVehicle.getVehicleBonus();
            tempStaff.setBonus(bnus);
        }

    }
}
// washing
class Washing{
    private int randomChoice1;
    private int randomChoice2;
    private String weekDay;
    private List<Intern> staffList;


    Washing(String WeekDay){
        this.randomChoice1 = 0;
        this.randomChoice2 = 0;
        this.weekDay = WeekDay;
        this.staffList = null;
    }

    // code to wash the vehicle
    public void washVehicle(List<Vehicle> VehicleList, Intern intern) {
        // get two random staff
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int internCount1 = 0;

        // only can wash 2 cars per day
        while(internCount1 < 2){
            // start cleaning dirty cars
            for(Vehicle car: VehicleList){
                if(car.getCleanliness() == "Dirty"){
                    // %80 chance on becoming clean %10 chance of becoming sparkling
                    double randomNumber = random.nextDouble();
                    if(randomNumber < 0.1){
                        car.setCondition("Sparkling");
                        internCount1 += 1;
                    }else if(randomNumber < 0.8){
                        car.setCondition("Clean");
                        internCount1 += 1;
                    }else{
                        System.out.printf("Washing did not have an affect on the vehicle\n");
                        internCount1 += 1;
                    }
                }
            }
            // move on to clean cars
            for(Vehicle car: VehicleList){
                if(car.getCleanliness() == "Clean"){
                    // %5 chance of becoming dirty, %30 chance on becmoming sparkling
                    double randomNumber = random.nextDouble();
                    if(randomNumber < 0.05){
                        car.setCondition("Dirty");
                        internCount1 += 1;
                    }else if(randomNumber < 0.3){
                        car.setCondition("Sparkling");
                        internCount1 += 1;
                    }else{
                        System.out.printf("Washing did not have an affect on the vehicle\n");
                        internCount1 += 1;
                    }
                }
            }
        }

    }
}

// repair
class Repair{
    private double repairChance;
    private String day;
    private double bonus;

    Repair(String curr_day){
        this.repairChance = 0.8;
        this.day = curr_day;
        this.bonus = 0.0;

    }

    public void repairVehicle(Vehicle vehicle) {
        // repair the vehicle
        Random random = new Random();
        double randomNumber = random.nextDouble();
        // mechanic can repair
        if(randomNumber > 0 && randomNumber <= 0.80){
            // upgrade condtition
            if(vehicle.getCondition() != "New"){
                if(vehicle.getCondition() == "Used"){
                    vehicle.setCondition("Like New");
                }else if(vehicle.getCondition() == "Broken"){
                    vehicle.setCondition("Used");
                }
                // downgrade cleanliness
                if(vehicle.getCleanliness() != "Dirty"){
                    if(vehicle.getCleanliness() == "Sparkling"){
                        vehicle.setCleanliness("Clean");
                    }else if(vehicle.getCleanliness() == "Clean"){
                        vehicle.setCleanliness("Dirty");
                    }
                }
            }
            // mechanic cant repair
        }else{
            System.out.printf("The Mechanic cant repair");
        }
    }
}

// ending
class Ending{
    private Double tempPay;
    private List<Staff> tempReport;
    Ending(Double tempPay){
        this.tempPay = tempPay;
    }
    public void payStaff(FNCD fncd){
        List<Staff> staffList = fncd.getStaffList();
        List<Staff> quitList = new ArrayList<>();
        Double quitChance = 0.1;
        Random random2 = new Random();
        Integer zero = 0;
        Double mechChance = random2.nextDouble();
        Double salesChance = random2.nextDouble();
        Double internChance = random2.nextDouble();
        List<Staff> StaffList = fncd.getStaffList();
//        Staff tempIntern1;
        Staff tempIntern2;
//      should I make a payStaff option in
        for(Staff staff: StaffList){
            if(fncd.opBudget >= staff.getSalary()){
                fncd.opBudget -= staff.getSalary();
                staff.getSalary();
//                *****
            }
        }
//        if the random intern chance is within 10% fire/quit intern
//        **** while
        if(internChance < quitChance){
                for (Staff staff : StaffList) {
                    if (staff.getPosition() == "Intern") {
                        quitList.add(staff);
                        staffList.remove(staff);
//                        zero = 1;
                        break;
                    }
                }

        }
        else{
            if(mechChance < quitChance){
                Staff tempIntern1 = new Staff("",0.0,0,0.0,0.0,"",0.0);

                for (Staff staff : StaffList) {
                    if (staff.getPosition() == "Mechanic") {
                        quitList.add(staff);
                        staffList.remove(staff);
                    }
                }
//                grabbing an intern to replace a mechanic
                for (Staff staff : StaffList) {
                    if (staff.getPosition() == "Intern") {
                        tempIntern1 = fncd.createMecanicStaff();
                        tempIntern1.setName(staff.getName());
                        staffList.remove(staff);
                    }
                    break;
                }
            }
            if(salesChance < quitChance){
                for (Staff staff : StaffList) {
                    if (staff.getPosition() == "Sales") {
                        quitList.add(staff);
                        staffList.remove(staff);
                    }
                }//                grabbing an intern to replace a salesperson

                for (Staff staff : StaffList) {
                    if (staff.getPosition() == "Intern") {
                        tempIntern2 = fncd.createSalesPersonStaff();
                        tempIntern2.setName(staff.getName());
                        staffList.remove(staff);
                    }
                    break;

                }
            }
        }
        tempReport = quitList;
    }
    public List<String> pReport(FNCD fncd){
        List<Staff> staffList = fncd.getStaffList();
        List<Staff> tempQuit = new ArrayList<>();
        tempQuit =
        for (Staff staff : staffList){
            staff.setHours(8.0);
            tempQuit.add(staff.getName() + ", " + staff.getHours() + ", "+ staff.getBonus() + ", Quit" );
        }
        for (Staff staff : staffList){
            staff.setHours(8.0);
            tempReport.add(staff.getName() + ", " + staff.getHours() + ", "+ staff.getBonus() + ", Working" );
        }
        return tempReport;
    }


}

// not needed right now
public class Task {

}

