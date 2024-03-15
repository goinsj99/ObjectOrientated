import java.util.List;
import java.util.Scanner;

public class askInventory {
    Integer carSelect2;
    public Vehicle getInv(FNCD fncd){
        List <Vehicle> tempList = fncd.getVehicleList();
        Integer index = 1;
        System.out.println("Enter the number by the vehicle you wish to select and view detail");
        for (Vehicle car : fncd.getVehicleList()){

            System.out.println(index + ": "+car.getName() );
            index ++;
        }
        Scanner myObj = new Scanner(System.in);


//       System.out.println("Enter 5 to select a vehicle: " + carSelect2);
        carSelect2 = Integer.parseInt(myObj.nextLine());
        Vehicle t4 = fncd.getVehicleList().get(carSelect2);
        if (carSelect2 > 0 && carSelect2 <= fncd.getVehicleList().size()){
            System.out.println("      * "+t4.getName() + ", " + t4.getCost() + ", "+ t4.getSalesPrice() + ","+ t4.getCondition()+ "," + t4.getCleanliness() );
        }


        return t4;
    }
}
