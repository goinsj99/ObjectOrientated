import java.util.Random;

// Clean Behvior, chemical clean

public class ChemClean implements Cleans {
    String complete; 
    int washCount;

    ChemClean(FNCD fncd, Intern intern){
        this.complete = "true";
        this.washCount = 0;
    }

    public void behave(FNCD fncd, Intern intern){
        int index = 0;
        int internCount1 = 0;
        boolean flag = false;
//         while the intern count for clean vehicle is 2 or less 
        while(internCount1 <= 2) {
            fncd.LoggerReport("      * "+intern.getName()+" Washing Report");
//             Iterating through vehicle list to clean if possible
            for (Vehicle car : fncd.getVehicleList()) {
                Random random = new Random();
                double randomBroke = random.nextDouble();
                if (car.getCleanliness() == "Dirty") {
                    // %80 chance on becoming clean %10 chance of becoming sparkling
                    double randomNumber = random.nextDouble();
//                     sparkingling case
                    if (randomNumber < 0.1) {
                        fncd.LoggerReport("          - Vehicle was dirty and is now Sparkling by chemical method " + car.getName());
                        car.setCleanliness("Sparkling");
                        intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                        internCount1++;

                        this.washCount++;
                        double curr = fncd.getStaffTotalEarn();
                        fncd.setStaffTotalEarn(curr += car.getVehicleBonus());
                        // increase bonus intern.bonus =
                   
                    } 
//                     Clean case
                    else if (randomNumber < 0.8) {
                        fncd.LoggerReport("          - Vehicle was dirty and is now Clean by chemical method " + car.getName());
                        car.setCleanliness("Clean");
                        internCount1++;
                        this.washCount++;
                    
                    } 
//                     Could not wash case
                    else if (randomNumber >= 0.8) {
                        fncd.LoggerReport("          - Was not able to wash: " + car.getName());
                        internCount1++;
                    }
//                     If the intern broke the car
                    if (randomBroke < 0.1) {
                        fncd.LoggerReport("          - Vehicle broke from chemical method " + car.getName());
                        car.setCondition("Broken");
                    }
                }
                if(internCount1 == 2){
                    break;
                }
            }
            if(internCount1 < 2){
//                 itterating through clean vehicles vehicle
                for (Vehicle car : fncd.getVehicleList()) {
                    Random random = new Random();
                    double randomBroke = random.nextDouble();
                    if (car.getCleanliness() == "Clean") {
                        // %10 chance of becoming dirty, %20 chance on becoming sparkling
                        double randomNumber = random.nextDouble();
                        if (randomNumber < 0.1) {
                            fncd.LoggerReport("          - Vehicle was Clean and is now Dirty by chemical method" + car.getName());
                            car.setCleanliness("Dirty");
                            internCount1++;
                            this.washCount++;
                        } else if (randomNumber < 0.2) {
                            fncd.LoggerReport("          - Vehicle was Clean and is now Sparkling by chemical method " + car.getName());
                            car.setCleanliness("Sparkling");
                            intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                            internCount1++;

                            this.washCount++;
                            double curr = fncd.getStaffTotalEarn();
                            fncd.setStaffTotalEarn(curr += car.getVehicleBonus());
                        } 
//                         Cant wash case
                        else {
                            fncd.LoggerReport("          - Was not able to wash: " + car.getName());
                            internCount1++;
                        }
//       Broke clean vehicle case                  
         
                        if (randomBroke < 0.1) {
                            fncd.LoggerReport("          - Vehicle broke from chemical method " + car.getName());
                            car.setCondition("Broken");
                        }
                    }
                    flag = false;
                    if(internCount1 == 2){
                        break;
                    }
                }
            }
            if(internCount1 == 2){
                break;
            }
        }
    }
    public Integer getWashCount(){
        return this.washCount;
    }
}
