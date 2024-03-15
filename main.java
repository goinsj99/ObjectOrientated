import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;


import java.awt.Color;


// import org.jfree.chart.ChartFactory;
// import org.jfree.chart.ChartFrame;
// import org.jfree.chart.JFreeChart;
// import org.jfree.chart.axis.AxisLocation;
// import org.jfree.chart.axis.DateAxis;
// import org.jfree.chart.axis.NumberAxis;
// import org.jfree.chart.plot.DatasetRenderingOrder;
// import org.jfree.chart.plot.PlotOrientation;
// import org.jfree.chart.plot.XYPlot;
// import org.jfree.data.time.Day;
// import org.jfree.data.time.TimeSeries;
// import org.jfree.data.time.TimeSeriesCollection;



public class main {
    public static void main(String[] args) {
        // verables
        double buget = 500000;
        int washcount = 0;
        int repairCount = 0;
        FNCD northFNCD = new FNCD(buget);
        FNCD southFNCD = new FNCD(buget);
        Random random = new Random();
        // used to calculate for costomers on day
        List<Integer> weekDays = Arrays.asList(0,1,2,3,4,5);
        List<Integer> weekEnds = Arrays.asList(2,3,4,5,6,7,8);

        // create instances of logger and tracker singletons
        EagerTrackerSingleton trackerSingleton = EagerTrackerSingleton.getInstance();
        LazyLoggerSingleton loggerSingleton = LazyLoggerSingleton.getInstance();


        // now open all 30 days
        for(int i = 0; i < 30; i++){
            // create instances
            Event_Publisher publisher = new Event_Publisher();
            Logger logger = new Logger(i+1);
            Tracker tracker = new Tracker();
            trackerSingleton.registerSub(tracker);
            loggerSingleton.registerSub(logger);

            // start observers of logger and tracker
            // publisher.registerSub(logger);
            // publisher.registerSub(tracker);
            northFNCD.LoggerReport("----------------------------------------------");
            northFNCD.LoggerReport("***** FNCD Day "+northFNCD.getDay()+" "+(i+1)+ "****\n");

            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            // run every senerio with openeing
            northFNCD.LoggerReport("FNCD North Opening... (current budget "+northFNCD.getOpBudget()+")");

            Opening northOpen = new Opening(northFNCD);
            northOpen.internHire(northFNCD.getStaffList(), northFNCD);
            northOpen.AddVehicle(northFNCD);

            // publish event to notify observers that opening is complete
            Fncd_Event northOpeningEvent = new Fncd_Event("opening_complete", "Opening is complete for day " + (i+1), northFNCD.getOpBudget());
            publisher.publishEvent(northOpeningEvent.getType(), northOpeningEvent.getMessage(), northOpeningEvent.getData());
            logger.update(northOpeningEvent);
            southFNCD.LoggerReport("FNCD South Opening... (current budget ) " + southFNCD.getOpBudget()+")");

            Opening southOpen = new Opening(southFNCD);
            southOpen.internHire(southFNCD.getStaffList(), southFNCD);
            southOpen.AddVehicle(southFNCD);

            // publish event to notify observers that opening is complete
            Fncd_Event southOpeningEvent = new Fncd_Event("opening_complete", "Opening is complete for day " + (i+1), southFNCD.getOpBudget());
            publisher.publishEvent(southOpeningEvent.getType(), southOpeningEvent.getMessage(), southOpeningEvent.getData());

            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            // run racing
            RacingEvent northRace = new RacingEvent(northFNCD);
            northRace.racingDay(northFNCD);

            RacingEvent southRace = new RacingEvent(southFNCD);
            southRace.racingDay(southFNCD);

            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            // run washing
            northFNCD.LoggerReport("FNCD North Washing...");
//            Washing wash = new Washing(fncd);
//            wash.washVehicle(fncd);
            for(Staff staff: northFNCD.getStaffList()){
                if(staff instanceof Intern){
                    if( staff.getCleanType() == "Chemical"){
                        Cleans northWash = new ChemClean(northFNCD, (Intern) staff);
                        northWash.behave(northFNCD, (Intern) staff);
                        washcount += northWash.getWashCount();
                    }
                    else if( staff.getCleanType() == "Elbow"){
                        Cleans northWash = new ElbowClean(northFNCD, (Intern) staff);
                        northWash.behave(northFNCD, (Intern) staff);
                        washcount += northWash.getWashCount();
                    }
                    else if( staff.getCleanType() == "Detailed"){
                        Cleans northWash = new DetailClean(northFNCD, (Intern) staff);
                        northWash.behave(northFNCD, (Intern) staff);
                        washcount += northWash.getWashCount();
                    }
                }
            }

            southFNCD.LoggerReport("FNCD South Washing...");
//            Washing wash = new Washing(fncd);
//            wash.washVehicle(fncd);
            for(Staff staff: southFNCD.getStaffList()){
                if(staff instanceof Intern){
                    if( staff.getCleanType() == "Chemical"){
                        Cleans southWash = new ChemClean(southFNCD, (Intern) staff);
                        southWash.behave(southFNCD, (Intern) staff);
                        washcount += southWash.getWashCount();
                    }
                    else if( staff.getCleanType() == "Elbow"){
                        Cleans southWash = new ElbowClean(southFNCD, (Intern) staff);
                        southWash.behave(southFNCD, (Intern) staff);
                        washcount += southWash.getWashCount();
                    }
                    else if( staff.getCleanType() == "Detailed"){
                        Cleans southWash = new DetailClean(southFNCD, (Intern) staff);
                        southWash.behave(southFNCD, (Intern) staff);
                        washcount += southWash.getWashCount();
                    }
                }
            }

            // publish event to notify observers that opening is complete
            Fncd_Event northRacingEvent = new Fncd_Event("opening_complete", "FNCD North Opening is complete for day " + (i+1), northFNCD.getOpBudget());
            publisher.publishEvent(northRacingEvent.getType(), northRacingEvent.getMessage(), northRacingEvent.getData());

            Fncd_Event southRacingEvent = new Fncd_Event("opening_complete", "FNCD South Opening is complete for day " + (i+1), southFNCD.getOpBudget());
            publisher.publishEvent(southRacingEvent.getType(), southRacingEvent.getMessage(), southRacingEvent.getData());

            // publish event to notify observers that washing is complete
            Fncd_Event northWashingEvent = new Fncd_Event("FNCD North washing_complete", "Washing is complete for day " + (i+1), washcount);
            washcount = 0;
            publisher.publishEvent(northWashingEvent.getType(), northWashingEvent.getMessage(), northWashingEvent.getData());

            Fncd_Event southWashingEvent = new Fncd_Event("FNCD South washing_complete", "Washing is complete for day " + (i+1), washcount);
            washcount = 0;
            publisher.publishEvent(southWashingEvent.getType(), southWashingEvent.getMessage(), southWashingEvent.getData());

            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //run repair
            northFNCD.LoggerReport("FNCD North Repairing... ");
            Repair northRepair = new Repair();
            for(Staff mec: northFNCD.getStaffList()){
                if(mec instanceof Mechanic){
                    northRepair.repairVehicle(northFNCD.getVehicleList(), mec, northFNCD);
                    repairCount += northRepair.getRepairCount();
                }
            }

            southFNCD.LoggerReport("FNCD South Repairing... ");
            Repair southRepair = new Repair();
            for(Staff mec: southFNCD.getStaffList()){
                if(mec instanceof Mechanic){
                    southRepair.repairVehicle(southFNCD.getVehicleList(), mec, southFNCD);
                    repairCount += southRepair.getRepairCount();
                }
            }

            // publish event to notify observers that repair is complete
            Fncd_Event northRepairEvent = new Fncd_Event("repair_complete", "FNCD North Repair is complete for day " + (i+1), repairCount);
            repairCount = 0;
            publisher.publishEvent(northRepairEvent.getType(), northRepairEvent.getMessage(), northRepairEvent.getData());

            Fncd_Event southRepairEvent = new Fncd_Event("repair_complete", "FNCD South Repair is complete for day " + (i+1), repairCount);
            repairCount = 0;
            publisher.publishEvent(southRepairEvent.getType(), southRepairEvent.getMessage(), southRepairEvent.getData());

            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //run Selling
            northFNCD.LoggerReport("FNCD North Selling... ");
            Selling northMop = new Selling();
            if(northFNCD.getDayIndex() < 5){
                int indext = random.nextInt(weekDays.size());
                northFNCD.LoggerReport("      * Number of customers came in on a week day are "+indext);
                for(int j = 0; j < weekDays.get(indext); j++){
                    northFNCD.LoggerReport("      * Customer "+(j+1));
                    northMop.sellThisCar(northFNCD);
                }
            }else{
                int index = random.nextInt(weekEnds.size());
                northFNCD.LoggerReport("      * Number of customers came in on a weekend day are "+index+"\n");
                for(int j = 0; j < weekEnds.get(index); j++){
                    northMop.sellThisCar(northFNCD);
                }
            }

            southFNCD.LoggerReport("FNCD South Selling... ");
            Selling southMop = new Selling();
            if(southFNCD.getDayIndex() < 5){
                int indext = random.nextInt(weekDays.size());
                southFNCD.LoggerReport("      * Number of customers came in on a week day are "+indext);
                for(int j = 0; j < weekDays.get(indext); j++){
                    southFNCD.LoggerReport("      * Customer "+(j+1));
                    southMop.sellThisCar(southFNCD);
                }
            }else{
                int index = random.nextInt(weekEnds.size());
                southFNCD.LoggerReport("      * Number of customers came in on a weekend day are "+index+"\n");
                for(int j = 0; j < weekEnds.get(index); j++){
                    southMop.sellThisCar(southFNCD);
                }
            }
            // publish event to notify observers that selling is complete
            Fncd_Event northSellingEvent = new Fncd_Event("selling_complete", "FNCD North Selling is complete for day " + (i+1), northMop.getSellCount());
            publisher.publishEvent(northSellingEvent.getType(), northSellingEvent.getMessage(), northSellingEvent.getData());

            Fncd_Event southSellingEvent = new Fncd_Event("selling_complete", "FNCD South Selling is complete for day " + (i+1), southMop.getSellCount());
            publisher.publishEvent(southSellingEvent.getType(), southSellingEvent.getMessage(), southSellingEvent.getData());

            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            // ending
            Ending northEnd = new Ending();
            northEnd.payStaff(northFNCD);
            northEnd.pReport(northFNCD);

            Ending southEnd = new Ending();
            southEnd.payStaff(southFNCD);
            southEnd.pReport(southFNCD);

            // tracker.update(fncd.getStaffTotalEarn());
            Fncd_Event northStaffEarnedEvent = new Fncd_Event("staff_money_earned", "FNCD North Total money staff earned ",northFNCD.getStaffTotalEarn());
            tracker.update(northStaffEarnedEvent);

            Fncd_Event southStaffEarnedEvent = new Fncd_Event("staff_money_earned", " FNCD South Total money staff earned ",southFNCD.getStaffTotalEarn());
            tracker.update(southStaffEarnedEvent);

            Fncd_Event northEarnedEvent = new Fncd_Event("fncd_money_earned", " NORTH Total money fncd earned ",northFNCD.getFncdEarning());
            tracker.update(northEarnedEvent);


            Fncd_Event southEarnedEvent = new Fncd_Event("fncd_money_earned", " SOUTH Total money fncd earned ",southFNCD.getFncdEarning());
            tracker.update(southEarnedEvent);


            northFNCD.LoggerReport("\nFNCD North Checking Staff... \n");
            for(Staff pp: northFNCD.getStaffList()){
                String newline = "      * "+pp.getName()+" "+pp.getPosition()+" "+pp.getBonus()+" "+pp.getBonusTemp();
                northFNCD.LoggerReport(newline);
            }

            southFNCD.LoggerReport("\nFNCD South Checking Staff... \n");
            for(Staff pp: southFNCD.getStaffList()){
                String newline = "      * "+pp.getName()+" "+pp.getPosition()+" "+pp.getBonus()+" "+pp.getBonusTemp();
                southFNCD.LoggerReport(newline);
            }
            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            // Unsubscribe logger after day 1
            publisher.removeSub(logger);
            // print summary report
            tracker.printSummary(i+1);
            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            northFNCD.nextDay();
            southFNCD.nextDay();
            
        }
        System.out.println("******* Welcome to the FNCD Simulator ********");
        System.out.println("******* Please select from the following menu by entering the associated number ********");
        System.out.println("******* 1. Choose FNCD (Enter '1' for north FNCD Enter '2' for the South FNCD)********");
        System.out.println("******* 2. Ask for salespersons name ********");
        System.out.println("******* 3. Ask what time it is ********");
        System.out.println("******* 4. Request a different sales representative ********");
        System.out.println("******* 5. View current inventory ********");
        System.out.println("******* 6. Details on selected item ********");
        System.out.println("******* 7. Buy selected item ********");
        System.out.println("******* 8. End ********");
        Scanner myObj = new Scanner(System.in);
        Integer menuSelect = Integer.parseInt(myObj.nextLine());
        System.out.println("You Entered: " + menuSelect);

        Boolean whatStore = false;
        while(menuSelect < 7 ){

        RemoteControl remote = new RemoteControl();
        Command command = new setLocation();
        remote.setCommand(command);
        Scanner myObj3 = new Scanner(System.in);
//        Integer menuSelect = Integer.parseInt(myObj.nextLine());
//        System.out.println("You Entered: " + menuSelect);


            switch (menuSelect) {
                case 0:
                    System.out.println("Menu selection not valid, enter '"+ 8 +"' to end day 31");
                    break;
                case 1:
                    System.out.println("Choose FNCD");
                    System.out.println("***** Enter '1' for north FNCD Enter '2' for the South FNCD ******");
                    Integer fnSelect = Integer.parseInt(myObj3.nextLine());
                    if(fnSelect == 1){
                        whatStore = true;
                        System.out.println("***** North FNCD Selected ******");

                    }
                    else if(fnSelect == 2){
                        System.out.println("***** South FNCD Selected ******");
                    }
                    else{
                        System.out.println("Invalid selection");
                    }
//                    remote.execute(southFNCD);
                    break;
                case 2:
                    if(whatStore == false) {
                        remote.execute2(southFNCD);
                    }
                    else{
                        remote.execute2(northFNCD);
                    }
                    break;
                case 3:
                    if(whatStore == false) {
                        remote.execute3(southFNCD);
                    }
                    else{
                        remote.execute3(northFNCD);
                    }
                    break;
                case 4:
                    if(whatStore == false) {
                        remote.execute4(southFNCD);
                    }
                    else{
                        remote.execute4(northFNCD);
                    }
//                    System.out.println("Your new sales rep is ");
                    break;

                case 5:
//                askInventory see2 = new askInventory();
                    System.out.println("******* FNCD Current Inventory ****** ");
                    if(whatStore == false) {
                        remote.execute5(southFNCD);
                    }
                    else{
                        remote.execute5(northFNCD);
                    }

                    break;

                case 6:
                    if(whatStore == false) {
                        remote.execute6(southFNCD);
                    }
                    else{
                        remote.execute6(northFNCD);
                    }
//                System.out.println("Choose FNCD");
                    break;

                case 7:
                    if(whatStore == false) {
                        remote.execute7(southFNCD);
                    }
                    else{
                        remote.execute7(northFNCD);
                    }
                    break;

                case 8:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Menu selection not valid, enter '"+ 8 +"' to end day 31");
            }

            menuSelect = Integer.parseInt(myObj.nextLine());
            System.out.println("You Entered: " + menuSelect);

        }


    }
}
