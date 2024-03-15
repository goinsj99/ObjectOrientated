import java.util.Random;

public class ElbowClean implements Cleans {
    String complete; 
    Integer washCount;

    ElbowClean(FNCD fncd, Intern intern){
        this.washCount = 0;
        this.complete = "true";
    }

    public void behave(FNCD fncd, Intern intern){
        int index = 0;
        int washCount = 0;
        int internCount1 = 0;
        boolean flag = false;
        while(internCount1 <= 2) {
            fncd.LoggerReport("      * "+intern.getName()+" Washing Report");
            
            for (Vehicle car : fncd.getVehicleList()) {
                Random random = new Random();
                double randomBroke = random.nextDouble();

                if (car.getCleanliness() == "Dirty") {
                    // %70 chance on becoming clean %05 chance of becoming sparkling
                    double randomNumber = random.nextDouble();
                    if (randomNumber < 0.05) {
                        fncd.LoggerReport("          - Vehicle was dirty and is now Sparkling by Elbow method " + car.getName());
                        car.setCleanliness("Sparkling");
                        intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                        internCount1++;

                        this.washCount++;
                        double curr = fncd.getStaffTotalEarn();
                        fncd.setStaffTotalEarn(curr += car.getVehicleBonus());
                        // increase bonus intern.bonus =
                    } else if (randomNumber < 0.7) {
                        fncd.LoggerReport("          - Vehicle was dirty and is now Clean by Elbow method " + car.getName());
                        car.setCleanliness("Clean");
                        internCount1++;
                        this.washCount++;
                    } else if (randomNumber >= 0.7){
                        //System.out.printf("Washing did not have an affect on the vehicle 1\n");
                        fncd.LoggerReport("          - Was not able to wash: " + car.getName());
                        internCount1++;
                    }
                    if (randomBroke < 0.1) {
                        fncd.LoggerReport("          - Vehicle broke from Elbow method " + car.getName());
                        car.setCondition("Broken");
                    }
                }
                if(internCount1 == 2){
                    break;
                }
            }
            if(internCount1 < 2){
                for (Vehicle car : fncd.getVehicleList()) {
                    Random random = new Random();
                    double randomBroke = random.nextDouble();
                    if (car.getCleanliness() == "Clean") {
                        // %15 chance of becoming dirty, %15 chance on becoming sparkling
                        double randomNumber = random.nextDouble();
                        if (randomNumber < 0.15) {
                            fncd.LoggerReport("          - Vehicle was Clean and is now Dirty by Elbow method " + car.getName());
                            car.setCleanliness("Dirty");
                            internCount1++;
                            washCount++;
                        } else if (randomNumber < 0.15) {
                            fncd.LoggerReport("          - Vehicle was Clean and is now Sparkling by Elbow method" + car.getName());
                            car.setCleanliness("Sparkling");
                            intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                            internCount1++;
                            washCount++;
                            double curr = fncd.getStaffTotalEarn();
                            fncd.setStaffTotalEarn(curr += car.getVehicleBonus());
                        } else {
                            fncd.LoggerReport("          - Was not able to wash: " + car.getName());
                            internCount1++;
                        }
                        if (randomBroke < 0.1) {
                            fncd.LoggerReport("          - Vehicle broke from Elbow method " + car.getName());
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