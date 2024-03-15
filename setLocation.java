import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
public class setLocation implements Command{

    Staff staff;
    double buget;
    private Vehicle tempV;
    askInventory tee;
    ask4All tee2;
    String staffN;
    String carN;

    public setLocation (){
        this.tee = new askInventory();
        this.tee2 = new ask4All();
//        this.tempV = tempV;
//        this.carN = carN;
//        this.staffN = staffN;

    }

    @Override
    public void execute(FNCD fncd) {
        Scanner myObj3 = new Scanner(System.in);


        System.out.println("***** Enter '1' for north FNCD Enter '2' for the South FNCD ******");
        Integer fnSelect = Integer.parseInt(myObj3.nextLine());
        if(fnSelect == 1){

        }
        else if(fnSelect == 2){

        }
        else{
            System.out.println("Invalid selection");
        }

    }
    public void execute2(FNCD fncd) {
        getStaffName test = new getStaffName(fncd);
        test.getTheName();
        String tempName;
//        System.out.println("Salesperson name is " + test.getTheName());
    }
//    Time command
    public void execute3(FNCD fncd) {
        LocalTime time = LocalTime.now();
        System.out.println("Current time: "+time);
    }
    public void execute4(FNCD fncd) {
        anotherRep yes = new anotherRep();
        yes.Tryy(fncd);
    }
    public void execute5(FNCD fncd) {
//        askInventory tee = new askInventory();
        tempV = tee.getInv(fncd);
    }public void execute6(FNCD fncd) {
        tempV = tee.getInv(fncd);
        tee2.getInv2(tempV);
    }public void execute7(FNCD fncd) {
        Vehicle test =  tee.getInv(fncd);
        System.out.println(test.getName());
    }
    public void executeSet() {

    }

//    public void seeTime{
//            //Getting the current time value
//            LocalTime time = LocalTime.now();
//            System.out.println("Current time: "+time);
//    }


}
