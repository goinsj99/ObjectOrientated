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
    private Double salaryRate;
    private Integer staffID;
    private Double salary;
    private Double hours;
    private String position;
    private String firstName;
    private Double bonus;
    private Double bonusTemp;
    private Float Rate;

    public Staff(String firstName, Double salaryRate, Integer staffID, Double salary, Double hours, String position, Double bonus) {
        this.firstName = firstName;
        this.salaryRate = salaryRate;
        this.staffID = staffID;
        this.hours = hours;
        this.position = position;
        this.salary = salary;
        this.bonus = bonus;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

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
        this.salary = salary;
    }
    public void setBonus(Double bonus){this.bonus = bonus;}
    public void setBonusTemp(Double bonusTemp){this.bonusTemp = bonusTemp;
    }
    public String getName() {
        return this.firstName;
    }

    public Double getSalaryRate() {
        Double temp= 0.0;
        temp += getSalary();
        this.salaryRate = temp;
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
    public Double getBonus(){return this.bonus;}
    public Double getBonusTemp(){
        Double temp = 0.0;
        temp += getBonus();
        this.bonusTemp =temp;
        return this.bonusTemp;
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
    public Intern(String firstName, Double salaryRate, Integer staffID, Double salary, Double hours, String position, Double bonus) {
        super(firstName, salaryRate, staffID, salary, hours, position, bonus);
        this.position = "Intern";
        this.salary = (salary*myRate);
    }
    @Override
    public String getPosition(){return this.position; }
    public Double getSalary(){return this.salary;}

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