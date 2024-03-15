//import java.util.Random;
//import java.util.ArrayList;
//import java.util.*;
//import java.util.List;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.FileOutputStream;
//import java.text.DecimalFormat;
//
//public class useBuyCar {
//
//    public void sellIT(FNCD fncd){
//    Staff tempStaff;
//    List<Vehicle> VehicleList = fncd.getVehicleList();
//    List<Staff> staffList = fncd.getStaffList();
//    Vehicle sVehicle;
//
//
//        //selling vehicle and removing/adding to appropriate list
//        Random random2 = new Random();
//        Double temp3 = random2.nextDouble();
//
//            fncd.addVehiclesSold(sVehicle);
//            VehicleList.remove(sVehicle);
//            tempStaff = whoSold(fncd);
//            if(tempStaff == null){
//                fncd.LoggerReport("            * Customer Interested but No current Sales person works here !");
//                return fncd.getSoldVehicle();
//            }
//            //Double bnus = sVehicle.getVehicleBonus();
//            for(Staff staff : staffList) {
//                if(staff.getName() == tempStaff.getName()) {
//                    // add decorator
//                    Vehicle_Interface currCar = new Selling_Vehicle(sVehicle);
//                    fncd.LoggerReport("            * You are buying "+currCar.getData()+" Would you like to add on \n             + Extended_Warranty\n             + Undercoating\n             + Road Rescue Coverage\n             + and/or Radio?");
//                    double customerDecisionAddON = random2.nextDouble();
//                    // road rescue coverage
//                    if(customerDecisionAddON < 0.05){
//                        fncd.LoggerReport("                = Great choice in adding Road Rescue Coverage!");
//                        currCar = new Road_Rescue_Coverage(currCar);
//                    }
//                    // undercoating
//                    else if(customerDecisionAddON < 0.10){
//                        fncd.LoggerReport("                = Great choice in adding Undercoating!");
//                        currCar = new Undercoating(currCar);
//                    }
//                    // extended warrenty
//                    else if(customerDecisionAddON < 0.25){
//                        fncd.LoggerReport("                = Great choice in adding Extended Warrenty!");
//                        currCar = new Extended_Warranty(currCar);
//                    }
//                    // satellite radio
//                    else if(customerDecisionAddON < 0.40){
//                        fncd.LoggerReport("                = Great choice in adding Satellite Radio!");
//                        currCar = new Radio(currCar);
//                    }else{
//                        fncd.LoggerReport("                = I understand, Cars are already expensive!");
//                    }
//
//                    staff.setBonus(staff.getBonus() + staff.getBonus());
//                    fncd.setOpBudget(fncd.getOpBudget() + currCar.getSellsPrice());
//                    // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
//                    // used to round price
//                    DecimalFormat df = new DecimalFormat("##.00");
//                    fncd.LoggerReport("            * Vehicle was Sold "+currCar.getData()+", at a total price of "+ df.format(currCar.getSellsPrice()) +". It was fixed by "+staff.getName());
//
//                    this.sellingCount++;
//                    double curr = fncd.getFncdEarning();
//                    fncd.setFncdEarning(curr+=currCar.getSellsPrice());
//                }
//            }
//
//        fncd.LoggerReport("");
//        return fncd.getSoldVehicle();
//
//}
//}
//}
//}
