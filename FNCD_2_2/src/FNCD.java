import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FNCD {
    public Double opBudget;
    public int currIDCount;
    public List<Staff> staffList = new ArrayList();
    public List<Vehicle> vehicleList = new ArrayList();
    public List<String> staffnames= new ArrayList();;
    public List<String> CarNames = new ArrayList();
    public List<String> PerformanceCarNames = new ArrayList();;
    public List<String> PickupCarNames = new ArrayList();;
    public List<String> report = Arrays.asList();
    private List<Vehicle> vehiclesSold = new ArrayList<>();

    //    report parameters need to be filled
    public Integer staffCount;

    public FNCD(Double opBudget){
        this.opBudget = opBudget;
        this.currIDCount = 101017;
        this.CarNames = setCarNmaes(CarNames, "RegularCars.txt");
        this.PerformanceCarNames = setCarNmaes(PerformanceCarNames, "PerformanceCar.txt");
        this.PickupCarNames = setCarNmaes(PickupCarNames, "PickupCars.txt");
        this.staffnames =  setCarNmaes(staffnames, "workerNames.csv");
        this.vehicleList = dayOneVehicle();
        this.staffList = dayOneStaff();
        this.staffCount = 0;
        this.report = null;
        this.vehiclesSold = null;
    }
    // Setters
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
    public void setOpBudget(Double opBudget){
        this.opBudget = opBudget;
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
    public List<String> setReport(){
        return this.report;
    }
    // creates a random 5 digit id for each employee
    public int generateID(){
        return this.currIDCount++;
    }
    // create a SalesPerson
    public SalesPerson createSalesPersonStaff(){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(this.staffnames.size());
        String name = this.staffnames.get(index);
        this.staffnames.remove(index);

        SalesPerson newStaff = new SalesPerson(name, 0.0, generateID(), 100.88, 0.0, "Sales Person", 200.5);
        return newStaff;
    }
    // create a intern
    public Intern createInternStaff(){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(this.staffnames.size());
        String name = this.staffnames.get(index);
        this.staffnames.remove(index);

        Intern newStaff = new Intern(name, 0.0, generateID(), 100.88, 0.0, "Intern", 300.5);
        return newStaff;
    }
    // create a Machanic
    public Mechanic createMecanicStaff(){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(this.staffnames.size());
        String name = this.staffnames.get(index);
        this.staffnames.remove(index);

        Mechanic newStaff = new Mechanic(name, 0.0, generateID(), 100.88, 0.0, "Mechanic", 500.5);
        return newStaff;
    }
    // create a Car
    public Car createCar(){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(CarNames.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = this.CarNames.get(index).split("\\s+"); // make, model, vim
        Car newCar = new Car(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    // create a Performance Car
    public Performance_Car createPerformanceCar(){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(this.PerformanceCarNames.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = this.PerformanceCarNames.get(index).split("\\s+"); // make, model, vim
        Performance_Car newCar = new Performance_Car(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    // create Pickup car
    public Pickup_Car createPickupCar(){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(this.PickupCarNames.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = this.PickupCarNames.get(index).split("\\s+"); // make, model, vim
        System.out.printf(splitstr[0], splitstr[1], splitstr[2]);
        Pickup_Car newCar = new Pickup_Car(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    // create Starting Staff
    public List<Staff> dayOneStaff(){
        List<Staff> listTemp = new ArrayList();
        for(int i = 0; i < 3; i++){
            listTemp.add(createSalesPersonStaff());
            listTemp.add(createMecanicStaff());
            listTemp.add(createInternStaff());
        }
        return listTemp;
    }
    // create Starting Vehicles
    public List<Vehicle> dayOneVehicle(){
        List<Vehicle> listtemp = new ArrayList();
        for(int i = 0; i < 4; i++){
            listtemp.add(createCar());
            listtemp.add(createPerformanceCar());
            listtemp.add(createPickupCar());
        }
        return listtemp;
    }
}