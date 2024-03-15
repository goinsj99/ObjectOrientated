import java.util.Random;
// create an interface for the vehicle 
public interface Vehicle_Interface {
    String getData();
    double getSellsPrice();
}

// now created a decorator abstract class
abstract class Decorator implements Vehicle_Interface {
    // create an interface
    Vehicle_Interface dec_car;
    // decorator sets vehicle
    public Decorator(Vehicle_Interface currDecCar) {
        this.dec_car = currDecCar;
    }
    // return its name+vim+type 
    public String getData() {
        return dec_car.getData();
    }
    // return its sales price 
    public double getSellsPrice() {
        return dec_car.getSellsPrice();
    }
}

// If customer wants to add Exteneded Warranty 
class Extended_Warranty extends Decorator {
    private double upSale;

    public Extended_Warranty(Vehicle_Interface decoratedVehicle) {
        super(decoratedVehicle);
        this.upSale = 1.2;
    }
    // add extended warranty to discription 
    @Override
    public String getData() {
        return super.getData() + ", Added: Extended Warranty";
    }
    // add price difference to car sales price 
    @Override
    public double getSellsPrice() {
        // may need to logg
        return(super.getSellsPrice() * this.upSale);
    }
}
// If customer wants to add Undercoating 
class Undercoating extends Decorator {
    private double upSale;

    public Undercoating(Vehicle_Interface decoratedVehicle) {
        super(decoratedVehicle);
        this.upSale = 1.05;
    }
    // add undercoating to discription 
    @Override
    public String getData() {
        return super.getData() + ", Added: Undercoating!";
    }
    // add price difference to car sales price 
    @Override
    public double getSellsPrice() {
        // may need to logg
        return(super.getSellsPrice() * this.upSale);
    }
}
// If customer wants to add Road Rescue Coverage 
class Road_Rescue_Coverage extends Decorator {
    private double upSale;

    public Road_Rescue_Coverage(Vehicle_Interface decoratedVehicle) {
        super(decoratedVehicle);
        this.upSale = 1.02;
    }
    // add road rescue coverage to discription 
    @Override
    public String getData() {
        return super.getData() + ", Added: Road Rescue Coverage!";
    }
    // add price difference to car sales price 
    @Override
    public double getSellsPrice() {
        // may need to logg
        return(super.getSellsPrice() * this.upSale);
    }
}
// If customer wants to add Raido 
class Radio extends Decorator {
    private double upSale;

    public Radio(Vehicle_Interface decoratedVehicle) {
        super(decoratedVehicle);
        this.upSale = 1.05;
    }
    // add radio to discription 
    @Override
    public String getData() {
        return super.getData() + ", Added: Satellite Radio!";
    }
    // add price difference to car sales price 
    @Override
    public double getSellsPrice() {
        // may need to logg
        return(super.getSellsPrice() * this.upSale);
    }
}

