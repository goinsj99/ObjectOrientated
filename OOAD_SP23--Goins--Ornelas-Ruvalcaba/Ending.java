import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.FileOutputStream; 

public class Ending{
    private Double tempPay;
    private List<Staff> tempReport;
    List<Staff> quitList = new ArrayList<>();
    

    Ending(){
        this.tempPay = 0.0;
        this.tempReport = new ArrayList<>();
        this.quitList = new ArrayList<>();
    }
    public void payStaff(FNCD fncd){
        List<Staff> staffList = fncd.getStaffList();
        List<Staff> coppyStaff = List.copyOf(staffList);
        Double quitChance = 0.1;
        double staffTotalEarning = 0;
        int mecCount = 3;
        int salesCout = 3;
        //Staff tempIntern1;
        Staff tempIntern2;
        
        for(Staff staff: coppyStaff){
            Random random2 = new Random();
            Double mechChance = random2.nextDouble();
            Double salesChance = random2.nextDouble();
            Double internChance = random2.nextDouble();
            // pay staff
            if(fncd.getOpBudget() >= staff.getSalary()){
                fncd.setOpBudget(fncd.getOpBudget() - staff.getSalary());
                staff.setHours(staff.getHours() +8.0);
                double curr = fncd.getStaffTotalEarn();
                //fncd.setStaffTotalEarn(curr+=staff.getSalary());
            }
            // intern quits
            if(staff instanceof Intern) {
                if(internChance < quitChance){
                    this.quitList.add(staff);
                    staffList.remove(staff);
                    break;
                    //fncd.setStaffList(staffList);
                }
            }
            // mec quit
            if (staff instanceof Mechanic) {
                if(mechChance < quitChance){
                    mecCount--;
                    this.quitList.add(staff);
                    staffList.remove(staff);
                    //fncd.setStaffList(staffList);
                    break;
                }
            }
            // sals person quits
            if(staff instanceof SalesPerson) {
                if(salesChance < quitChance){
                    salesCout--;
                    this.quitList.add(staff);
                    staffList.remove(staff);
                    //fncd.setStaffList(staffList);
                    break;
                }
            }
            // is driver injuried 
            if(staff instanceof Driver){
                Driver s = (Driver) staff;
                boolean flag = s.getInjuryStat();
                if(flag == true){
                    this.quitList.add(s);
                    staffList.remove(s);
                }
            }
        }
        for(Staff staff: coppyStaff){
            if(staff instanceof Intern){
                if(mecCount < 3){
                    Mechanic newmec = fncd.createMecanicStaff();
                    newmec.setName(staff.getName());
                    staffList.add(newmec);
                    staffList.remove(staff);
                    mecCount++;
                }
                if(salesCout < 3){
                    SalesPerson newsale = fncd.createSalesPersonStaff();
                    newsale.setName(staff.getName());
                    staffList.add(newsale);
                    staffList.remove(staff);
                    salesCout++;
                }
            }
        }
        fncd.setStaffList(staffList);
    }

    public void pReport(FNCD fncd){
        List<Staff> staffList = fncd.getStaffList();
        List<Vehicle> vehicleList = fncd.getVehicleList();
        List<Staff> tempQuit = this.quitList;
        Double totalSales = 0.0;

        fncd.LoggerReport("| Staff that quit |");
        for (Staff staff : tempQuit){
        // staff.setHours(staff.getHours() + 8.0);
            fncd.LoggerReport("      * "+staff.getName() + ", " + staff.getHours() + ", "+ staff.getSalary() + ", " + staff.getBonusTemp() + ", Quits" );
        }
        fncd.LoggerReport("| Staff still working that |");
        for (Staff staff : staffList){
        // staff.setHours(8.0);
            fncd.LoggerReport("      * "+staff.getName() + ", " + staff.getHours() + ", " + staff.getSalary() + ", "+ staff.getBonus() + ", "+staff.getPosition() + ", Working" );
        }
        fncd.LoggerReport("| Sold Vehicles |");
        for (Vehicle car : fncd.getSoldVehicle() ){
        // staff.setHours(8.0);
            fncd.LoggerReport("      * "+car.getName() + ", " + car.getCost() + ", "+ car.getSalesPrice() + ","+ car.getCondition()+ "," +car.getCleanliness()+  ", Sold" );
        }
        fncd.LoggerReport("| Current Vehicle Inventory |");
        for (Vehicle car : fncd.getVehicleList()){
        // staff.setHours(8.0);
            fncd.LoggerReport("      * "+car.getName() + ", " + car.getCost() + ", "+ car.getSalesPrice() + ","+ car.getCondition()+ "," +car.getCleanliness()+  ", In Stock" );
        }
        fncd.LoggerReport("|O ur Budget & Total Sale Number? |");
        for (Vehicle car : fncd.getSoldVehicle()){
            totalSales += car.getSalesPrice();
        }
        fncd.LoggerReport("      * "+fncd.getOpBudget()+" "+totalSales);
    }
}
