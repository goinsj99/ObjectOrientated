import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Vehicle{
    private String vim;
    private String make;
    private String model;
    private int year;
    private double cost;
    private String condition;
    private double sales_price;
    private String cleanliness;
    private String type;
    private Double vehicleBonus;
    private int winCount;
    // temp list
    private List<String> conditionList = Arrays.asList("New", "Used", "Broken");
    private List<String> cleanlinessList = Arrays.asList("Sparkling", "Clean", "Dirty");

    // constructor
    public Vehicle(String vim, String make, String model){
        this.vim = vim;
        this.make =  make;
        this.model = model;
        this.year = decideCarYear();
        this.cost = 0.0;
        this.type = "null";
        this.vehicleBonus = 0.0;
        this.condition = "null";
        this.sales_price = 0.0;
        this.cleanliness = decideCleanliness();
        this.winCount = 0;
    }

    // setters method implementation
    public void setVim(String vim){
        this.vim = vim;
    }
    public void setName(String make, String model){
        this.make = make;
        this.model = model;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setCost(float cost){
        this.cost = cost;
    }
    public void setCondition(String conditionll){
        this.condition = conditionll;
    }
    public void setSalesPrice(double sales_price){
        this.sales_price = sales_price;
    }
    public void setCleanliness(String cleanliness){
        this.cleanliness = cleanliness;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setVehicleBonus(Double vehicleBonus){
        this.vehicleBonus = vehicleBonus;
    }
    public void addWinCount(){
        this.winCount += 1;
    }

    // getters method implementation
    public String getVim(){
        return this.vim;
    }
    public String getName(){
        return this.make + " " + this.model;
    }
    public Integer getYear(){
        return this.year;
    }
    public Double getCost(){
        return this.cost;
    }
    public String getCondition(){
        return this.condition;
    }
    public Double getSalesPrice(){
        return this.sales_price;
    }
    public String getCleanliness(){
        return this.cleanliness;
    }
    public String getType(){
        return this.type;
    }
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    public int getWinCount(){
        return this.winCount;
    }
    // decide how much the car is to buy
    public Double decideCost(double min, double max){
        // used https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range 
        // double min = 10000;
        // double max = 20000;
        Random random = new Random();
        double randomValue = min + (max - min) * random.nextDouble();
        return randomValue;
    }
    // create a random year for car
    public int decideCarYear(){
        int min = 1980;
        int max = 2023;
        Random random = new Random();
        int randomValue = min + (max - min) * random.nextInt();
        return randomValue;
    }
    // choose condition randomly per car
    public String decideCondition(){
        // https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        Random random = new Random();
        int index = random.nextInt(conditionList.size());
        return conditionList.get(index);
    }
    // set sales price depending on condition
    public double decidePrice(String condition, Double cost){
        if(condition == "New"){
            sales_price = cost * 2;
        }else if(condition == "Used"){
            sales_price = (cost * 2) * 0.8;
        }else{
            sales_price = cost;
        }
        return sales_price;
    }
    // given a random state of cleanliness
    public String decideCleanliness(){
        Random random = new Random();
        double randomNumber = random.nextDouble();
        if(randomNumber < 0.05){
            return cleanlinessList.get(0);
        }else if(randomNumber < 0.35){
            return cleanlinessList.get(1);
    
        }else{
            return cleanlinessList.get(2);
        }
    }

}
// car sub class
// cost $10,000 - $20,0000
class Car extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;

    public Car(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(10000.00, 20000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 50.00;
        this.winCount = 0;
        this.type = "Car";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
}
// performance car sub class
// cost $20,000 - $40,000
class Performance_Car extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;

    public Performance_Car(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(20000.00, 40000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 70.00;
        this.winCount = 0;
        this.type = "Performance Car";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
}
// puck up sub class
// cost $10,000 - $40,000
class Pickup_Car extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;

    public Pickup_Car(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(10000.00, 40000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 100.00;
        this.winCount = 0;
        this.type = "Pickup Car";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
}
// electric car sub class
// cost $20,000 - $40,000
class Electric_Car extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;
    private Double range;

    public Electric_Car(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(20000.00, 40000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 100.00;
        this.range = createRange();
        this.winCount = 0;
        this.type = "Electric Car";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
    // random range 
    public Double createRange(){
        int min = 60;
        int max = 400;
        Random random = new Random();
        //double randomValue = random.nextDouble(max - min) + min;
        double randomValue = random.nextDouble() * (max - min) + min;

        return randomValue;
    }
    // get range
    public Double getRange(){
        return this.range;
    }
}
// motorcycles sub class
// cost $10,000 - $20,000
class Motorcycle extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;
    private Double engineSizeCC;

    public Motorcycle(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(10000.00, 20000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 100.00;
        this.engineSizeCC = createEngineSizeCC();
        this.winCount = 0;
        this.type = "Motorcycle";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
    // random range 
    // https://stackoverflow.com/questions/31754209/can-random-nextgaussian-sample-values-from-a-distribution-with-different-mean
    public Double createEngineSizeCC(){
        Random random = new Random();
        double truncatedValue = random.nextGaussian()*300+700;
        return truncatedValue;
    }
    // get range
    public Double getRange(){
        return this.engineSizeCC;
    }
}
// motorcycles sub class
// cost $20,000 - $60,000
class Monster_Truck extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private String stageName;
    // list of names from www.rookieroad.com/monster-trucks/list-a-z-2027269/
    private List<String> monsterNames = Arrays.asList("Air Force Afterburner", "Avenger", "Bad News Travels Fast", "Batman", "Backwards Bob", "Bear Foot (1979)", "Bear Foot (F-150)", "Bear Foot (2xtreme)", "Bear Foot (Silverado)", "Bear Foot USA", "Bigfoot", "Black Stallion", "Blacksmith", "Blue Thunder", "Bounty Hunter", "Brutus", "Bulldozer", "Captain's Curse", "Cyborg", "El Toro Loco", "Grave Digger", "Grinder", "Gunslinger", "Jurassic Attack", "King Krunch", "Lucas Oil Crusader", "Madusa", "Maximum Destruction (Max-D)", "Mohawk Warrior", "Monster Mutt", "Monster Mutt Dalmatian", "Predator", "Shell Camino", "Raminator", "Snake Bite", "Stone Crusher", "Sudden Impact", "Swamp Thing", "The Destroyer", "The Felon", "USA-1", "War Wizard", "WCW Nitro Machine", "Zombie");
    private int winCount;
    public Monster_Truck(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(20000.00, 60000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 100.00;
        this.stageName = generateName();
        this.winCount = 0;
        this.type = "Monster Truck";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
    // random range 
    public String generateName(){
        // https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        Random random = new Random();
        int index = random.nextInt(monsterNames.size());
        return monsterNames.get(index);
    }
    // get range
    public String getName(){
        return this.stageName;
    }
}
// hybrid sub class
// cost $20,000 - $30,000
class Hybrid extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;
    private double batteryHealth;
    public Hybrid(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(20000.00, 30000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 100.00;
        this.winCount = 0;
        this.batteryHealth = decideCost(70, 100);
        this.type = "Hybrid";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
    
    // get battery health
    public double getBatteryHealth(){
        return this.batteryHealth;
    }
}
// moped sub class
// cost $3,000 - $12,000
class Moped extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;
    private double rangeMPG;
    public Moped(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(3000.00, 12000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 100.00;
        this.winCount = 0;
        this.rangeMPG = decideCost(60, 200);
        this.type = "Moped";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
    
    // get battery health
    public double getRangeMPG(){
        return this.rangeMPG;
    }
}
// cunstruction sub class
// cost $20,000 - $50,000
class Cunstruction extends Vehicle{
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String condition;
    private String type;
    private int winCount;
    private List<String> cTypeList = Arrays.asList("Digger", "Roller", "Lifter");
    private String Ctype;
    private boolean working;
    public Cunstruction(String vim, String make, String model){
        super(vim, make, model);
        this.cost = decideCost(20000.00, 50000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 100.00;
        this.winCount = 0;
        this.Ctype = generateCtype();
        this.working = test();
        this.type = "Cunstruction";
    }
    @Override
    public String getType(){
        return this.type;
    }
    @Override
    public Double getVehicleBonus(){
        return this.vehicleBonus;
    }
    @Override
    public Double getCost(){
        return this.cost;
    }
    @Override 
    public String getCondition(){
        return this.condition;
    }
    @Override 
    public Double getSalesPrice(){
        return this.sales_price;
    }
    @Override
    public int getWinCount(){
        return this.winCount;
    }
    @Override
    public void addWinCount(){
        this.winCount += 1;
    }
    // test function
    public Boolean test(){
        Random random = new Random();
        double index = random.nextDouble();
        if(index < 0.1){
            return false;
        }else{
            return true;
        }
    }
    // random range 
    public String generateCtype(){
        // https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        Random random = new Random();
        int index = random.nextInt(cTypeList.size());
        return cTypeList.get(index);
    }
    // get battery health
    public String getCtype(){
        return this.Ctype;
    }
}
