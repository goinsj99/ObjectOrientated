import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// to create factory pattern in java, i used https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
// and https://stackoverflow.com/questions/17581310/using-enum-for-factory-in-java-a-best-practice
enum CreatType{
    // staff
    SALES,
    MACHANIC,
    INTERN,
    DRIVER,
    // cars 
    CAR,
    PERFORMANCE,
    PICKUP,
    ELECTRIC,
    MOTORCYCLE,
    MONSTERTRUCK,
    HYBRID,
    MOPED,
    CUNSTRUCTION
}

interface Create{
    Staff createStaff(List<String> n, FNCD fncd);
    Vehicle createVehicle(List<String> n, FNCD fncd);
}
// staff
class CreateSalesPerson implements Create{
    @Override
    public SalesPerson createStaff(List<String> staffnames, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(staffnames.size());
        String name = staffnames.get(index);
        staffnames.remove(index);

        SalesPerson newStaff = new SalesPerson(name, 100.0, fncd.generateID(), 100.88, 0.0, "Sales Person", 200.5);
        return newStaff;
    }
    @Override
    public Vehicle createVehicle(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateMechanic implements Create{
    @Override
    public Mechanic createStaff(List<String> staffnames, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(staffnames.size());
        String name = staffnames.get(index);
        staffnames.remove(index);

        Mechanic newStaff = new Mechanic(name, 100.0, fncd.generateID(), 100.88, 0.0, "Sales Person", 200.5);
        return newStaff;
    }
    @Override
    public Vehicle createVehicle(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateIntern implements Create{
    @Override
    public Intern createStaff(List<String> staffnames, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(staffnames.size());
        String name = staffnames.get(index);
        staffnames.remove(index);

        Intern newStaff = new Intern(name, 100.0, fncd.generateID(), 100.88, 0.0, "Sales Person", 200.5);
        return newStaff;
    }
    @Override
    public Vehicle createVehicle(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateDriver implements Create{
    @Override
    public Driver createStaff(List<String> staffnames, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(staffnames.size());
        String name = staffnames.get(index);
        staffnames.remove(index);

        Driver newStaff = new Driver(name, 100.0, fncd.generateID(), 100.88, 0.0, "Sales Person", 200.5);
        return newStaff;
    }
    @Override
    public Vehicle createVehicle(List<String> nn, FNCD fncd){
        return null;
    }
}
// vehicles 
class CreateCar implements Create{
    @Override
    public Car createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Car newCar = new Car(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreatePerformanceCar implements Create{
    @Override
    public Performance_Car createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Performance_Car newCar = new Performance_Car(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreatePickupCar implements Create{
    @Override
    public Pickup_Car createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Pickup_Car newCar = new Pickup_Car(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateElectricCar implements Create{
    @Override
    public Electric_Car createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Electric_Car newCar = new Electric_Car(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateMotorcycle implements Create{
    @Override
    public Motorcycle createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Motorcycle newCar = new Motorcycle(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateMonsterTruck implements Create{
    @Override
    public Monster_Truck createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Monster_Truck newCar = new Monster_Truck(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateHybrid implements Create{
    @Override
    public Hybrid createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Hybrid newCar = new Hybrid(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateMoped implements Create{
    @Override
    public Moped createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Moped newCar = new Moped(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}
class CreateCunstruction implements Create{
    @Override
    public Cunstruction createVehicle(List<String> names, FNCD fncd){
        Random random = new Random();// https://www.baeldung.com/java-random-list-element#:~:text=Picking%20a%20Random%20Item%2FItems,that%20exceeds%20your%20List%27s%20size |AND| https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
        int index = random.nextInt(names.size());
        // split list. Should be in order (car, performance, pickup) used https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String[] splitstr = names.get(index).split("\\s+"); // make, model, vim
        Cunstruction newCar = new Cunstruction(splitstr[2], splitstr[0], splitstr[1]);
        //CarNames.remove(index);
        return newCar;
    }
    @Override
    public Staff createStaff(List<String> nn, FNCD fncd){
        return null;
    }
}

public class Factory_Pattern {
    public Staff createNewStaff(CreatType ct, FNCD fncd){
        switch(ct){
            case SALES:
                return new CreateSalesPerson().createStaff(fncd.staffnames, fncd);
            case MACHANIC:
                return new CreateMechanic().createStaff(fncd.staffnames, fncd);
            case INTERN:
                return new CreateIntern().createStaff(fncd.staffnames, fncd);
            case DRIVER:
                return new CreateDriver().createStaff(fncd.staffnames, fncd);
            default:
                return null;
        }
    }

    public Vehicle createNewVehicle(CreatType cvt, FNCD fncd){
        switch(cvt){
            case CAR:
                return new CreateCar().createVehicle(fncd.CarNames, fncd);
            case PERFORMANCE:
                return new CreatePerformanceCar().createVehicle(fncd.PerformanceCarNames, fncd);
            case PICKUP:
                return new CreatePickupCar().createVehicle(fncd.PickupCarNames, fncd);
            case ELECTRIC:
                return new CreateElectricCar().createVehicle(fncd.ElectricCarNames, fncd);
            case MOTORCYCLE:
                return new CreateMotorcycle().createVehicle(fncd.MotorcycleNames, fncd);
            case MONSTERTRUCK:
                return new CreateMonsterTruck().createVehicle(fncd.MonsterTruckNames, fncd);
            case HYBRID:
                return new CreateHybrid().createVehicle(fncd.HybridCarNames, fncd);
            case MOPED:
                return new CreateMoped().createVehicle(fncd.MopedNames, fncd);
            case CUNSTRUCTION:
                return new CreateCunstruction().createVehicle(fncd.CunstructionNames, fncd);
            default:
                return null;
        }
    }

}
