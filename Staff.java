import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.UUID;


public class Staff {
    Cleans cleans;

    private Double salaryRate;
    private Integer staffID;
    private Double salary;
    private Double hours;
    private String position;
    private String firstName;
    private Double bonus;
    private Double bonusTemp;
    private Float Rate;
    public String cleanType;


    public Staff(String firstName, Double salaryRate, Integer staffID, Double salary, Double hours, String position, Double bonus) {
        this.firstName = firstName;
        this.salaryRate = salaryRate;
        this.staffID = staffID;
        this.hours = hours;
        this.position = position;
        this.salary = salary;
        this.bonus = bonus;
        this.bonusTemp = 0.0;

    }
//    public void cleanStrategy(){
//        cleans.behave();
//    }
    public void setName(String firstName) {
        this.firstName = firstName;
    }
//    public void setCleanType(String cleanType){
//        this.cleanType = cleanType;
//    }
    public void setSalaryRate(Double salaryRate) {
        this.salaryRate = salaryRate;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double Salary) {
        this.salary = Salary;
    }
    public void setBonus(Double bonus){this.bonus = bonus;}
    public void setBonusTemp(Double bonusTemp){this.bonusTemp = bonusTemp;
    }
    public String getName() {
        return this.firstName;
    }
//    public String getCleanType(){
//        return this.cleanType;
//    }

    public Double getSalaryRate() {
        return this.salaryRate;
    }

    public Integer getStaffID() {
        return this.staffID;
    }

    public Double getHours() {
        return this.hours;
    }

    public String getPosition() {
        return this.position;
    }

    public Double getSalary() {
        return this.salary;
    }
    public Double getBonusTemp(){return this.bonusTemp;} // bonus earned that day
    public Double getBonus(){ return this.bonus;} // total earned bonus
    public String getCleanType(){
        return this.cleanType;
    }


}

// Subclass (inherit from Animal)
class SalesPerson extends Staff{
    Double myRate = 1.25;
    private String position;
    private Double salary;
    public SalesPerson(String firstName, Double salaryRate, Integer staffID, Double salary, Double hours, String position, Double bonus) {
        super(firstName, salaryRate, staffID, salary, hours, position, bonus);
        this.position = "Sales";
        this.salary = (salary*myRate);
    }
    @Override
    public String getPosition(){return this.position; }
    public Double getSalary(){return this.salary;}

}

class Intern extends Staff {
    Double myRate = 1.5;
    private String position;
    private Double salary;

    private List<String> typesOfClean = Arrays.asList("Chemical", "Elbow", "Detail");


    public Intern(String firstName, Double salaryRate, Integer staffID, Double salary, Double hours, String position, Double bonus) {
        super(firstName, salaryRate, staffID, salary, hours, position, bonus);
        this.position = "Intern";
        this.salary = (salary*myRate);
        this.cleanType = decideCleanType();
    }
    @Override
    public String getPosition(){return this.position; }
    public Double getSalary(){return this.salary;}
    public String decideCleanType(){
        Random random1 = new Random();
        String cleanTemp;
        int temp = random1.nextInt(typesOfClean.size());
        cleanTemp = typesOfClean.get(temp);
        return cleanTemp;
    }

}

class Mechanic extends Staff {
    Double myRate = 1.75;
    private String position;
    private Double salary;
    public Mechanic(String firstName, Double salaryRate, Integer staffID, Double salary, Double hours, String position, Double bonus) {
        super(firstName, salaryRate, staffID, salary, hours, position, bonus);
        this.position = "Mechanic";
        this.salary = (salary*myRate);
    }
    @Override
    public String getPosition(){return this.position; }
    public Double getSalary(){return this.salary;}

}
class Driver extends Staff {
    Double myRate = 1.75;
    private String position;
    private Double salary;
    private boolean injury;
    public Driver(String firstName, Double salaryRate, Integer staffID, Double salary, Double hours, String position, Double bonus) {
        super(firstName, salaryRate, staffID, salary, hours, position, bonus);
        this.position = "Driver";
        this.salary = (salary*myRate);
        this.injury = false;
    }
    @Override
    public String getPosition(){return this.position; }
    public Double getSalary(){return this.salary;}
    public boolean getInjuryStat(){
        return this.injury;
    }
    public void updateInjury(){
        this.injury = true;
    }
}