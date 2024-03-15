import java.util.Random;

public class DetailClean implements Cleans {
    String complete;
    int washCount; 

    DetailClean(FNCD fncd, Intern intern){
        this.complete = "true";
        this.washCount = 0;
    }

    public void behave(FNCD fncd, Intern intern){
        //int index = 0;
        int internCount1 = 0;
        boolean flag = false;
        while(internCount1 <= 2) {
            fncd.LoggerReport("      * "+intern.getName()+" Washing Report");
            for (Vehicle car : fncd.getVehicleList()) {
                Random random = new Random();
                double randomBroke = random.nextDouble();
                if (car.getCleanliness() == "Dirty") {
                    // %60 chance on becoming clean %20 chance of becoming sparkling
                    double randomNumber = random.nextDouble();
                    if (randomNumber < 0.2) {
                        fncd.LoggerReport("          - Vehicle was dirty and is now Sparkling by Detail method" + car.getName());
                        car.setCleanliness("Sparkling");
                        intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                        internCount1++;
                        this.washCount++;
                        //.out.println("Met at wash count !!!!!!!!!!!!!!!!!!!!" + this.washCount);
                        double curr = fncd.getStaffTotalEarn();
                        fncd.setStaffTotalEarn(curr += car.getVehicleBonus());
                        // increase bonus intern.bonus =
                    } else if (randomNumber < 0.6) {
                        fncd.LoggerReport("          - Vehicle was dirty and is now Clean by Detail method" + car.getName());
                        car.setCleanliness("Clean");
                        internCount1++;
                        this.washCount++;
                        //System.out.println("Met at wash count !!!!!!!!!!!!!!!!!!!!" + this.washCount);
                    } else if (randomNumber >= 0.6) {
                        fncd.LoggerReport("          - Was not able to wash: " + car.getName());
                        internCount1++;
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
                        // %05 chance of becoming dirty, %40 chance on becoming sparkling
                        double randomNumber = random.nextDouble();
                        if (randomNumber < 0.05) {
                            fncd.LoggerReport("          - Vehicle was Clean and is now Dirty by Detail method " + car.getName());
                            car.setCleanliness("Dirty");
                            internCount1++;
                            this.washCount++;
                        } else if (randomNumber < 0.4) {
                            fncd.LoggerReport("          - Vehicle was Clean and is now Sparkling by Detail method " + car.getName());
                            car.setCleanliness("Sparkling");
                            intern.setBonusTemp(intern.getBonusTemp() + car.getVehicleBonus());
                            internCount1++;
                            this.washCount++;
                            double curr = fncd.getStaffTotalEarn();
                            fncd.setStaffTotalEarn(curr += car.getVehicleBonus());
                        } else {
                            fncd.LoggerReport("          - Was not able to wash: " + car.getName());
                            internCount1++;
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
