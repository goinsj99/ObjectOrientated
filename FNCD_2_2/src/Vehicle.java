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
    // temp list
    private List<String> conditionList = Arrays.asList("New", "Used", "Broken");
    private List<String> cleanlinessList = Arrays.asList("Sparkling", "Clean", "Dirty");

    // constructor
    public Vehicle(String vim, String make, String model){
        this.vim = vim;
        this.make =  make;
        this.model = model;
        this.year = 0;
        this.cost = 0.0;
        this.type = "null";
        this.vehicleBonus = 0.0;
        this.condition = "null";
        this.sales_price = 0.0;
        this.cleanliness = "null";
    }

    public void oneSentence(){
        System.out.printf("\n",this.vim, this.make, this.model, this.year, this.cost, this.type, this.vehicleBonus, this.condition, this.sales_price, this.cleanliness);
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

    public void setCondition(String condition){
        this.condition = condition;
    }

    public void setSalesPrice(String condition){
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

    // getters method implementation
    public String getVim(){
        return vim;
    }

    public String getName(){
        return make + model;
    }

    public Integer getYear(){
        return year;
    }

    public Double getCost(){
        return cost;
    }

    public String getCondition(){
        return condition;
    }

    public Double getSalesPrice(){
        return sales_price;
    }

    public String getCleanliness(){
        return cleanliness;
    }
    public String getType(){
        return type;
    }
    public Double getVehicleBonus(){
        return vehicleBonus;
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
    private int year;
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String type;
    private String condition;
    //private float sales_price;

    public Car(String vim, String make, String model){
        super(vim, make, model);
        // super.condition = "hello";
        this.year = decideCarYear();
        this.cost = decideCost(10000.00, 20000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 1.05;
        this.type = "Car";
    }
    @Override
    public String getType(){
        return type;
    }
    public Double getVehicleBonus(){
        return vehicleBonus;
    }

}
// performance car sub class
// cost $20,000 - $40,000
class Performance_Car extends Vehicle{
    private int year;
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String type;
    private String condition;
    //private float sales_price;

    public Performance_Car(String vim, String make, String model){
        super(vim, make, model);
        // super.condition = "hello";
        this.year = decideCarYear();
        this.cost = decideCost(20000.00, 40000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 1.15;
        this.type = "Performance Car";
    }
    @Override
    public String getType(){
        return type;
    }
    public Double getVehicleBonus(){
        return vehicleBonus;
    }

}
// puck up sub class
// cost $10,000 - $40,000
class Pickup_Car extends Vehicle{
    private int year;
    private Double cost;
    private Double sales_price;
    private Double vehicleBonus;
    private String type;
    private String condition;
    //private float sales_price;

    public Pickup_Car(String vim, String make, String model){
        super(vim, make, model);
        // super.condition = "hello";
        this.year = decideCarYear();
        this.cost = decideCost(10000.00, 40000.00);
        this.condition = decideCondition();
        this.sales_price = decidePrice(this.condition, this.cost);
        this.vehicleBonus = 1.1;
        this.type = "Pickup Car";
    }
    @Override
    public String getType(){
        return type;
    }
    public Double getVehicleBonus(){
        return vehicleBonus;
    }

}