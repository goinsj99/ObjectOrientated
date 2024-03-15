 // get the information about vehicle we are tring to add more sells 
 public class Selling_Vehicle implements Vehicle_Interface {
    Vehicle currCar;
    // set car
    Selling_Vehicle(Vehicle currSellCar){
        this.currCar = currSellCar;
    }
    // be able to get cars name
    @Override
    public String getData() {
        return "Name: "+this.currCar.getName()+", Vim: "+this.currCar.getVim()+", Model: "+this.currCar.getType();
    }
    // be able to get cars price
    @Override
    public double getSellsPrice() {
        return this.currCar.getSalesPrice();
    }
}
