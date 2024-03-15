import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.Optional;

// used user guide to implement unit test
// https://junit.org/junit5/docs/current/user-guide/
public class FncdTest {

    @Test
    public void testOpendingDay(){
        FNCD fncdTest = new FNCD(50000.0);
        Opening openTest = new Opening(fncdTest);
        openTest.internHire(fncdTest.getStaffList(), fncdTest);
        int internCount = 0;
        for(Staff s: fncdTest.getStaffList()){
            if(s instanceof Intern){
                internCount++;
            }
        }
        assertEquals(3, internCount);
    }

    @Test
    public void testOpendingCarCountDay(){
        FNCD fncdTest = new FNCD(50000.0);
        Opening openTest = new Opening(fncdTest);
        openTest.AddVehicle(fncdTest);
        int carCount = 0;
        for(Vehicle v: fncdTest.getVehicleList()){
            if(v instanceof Car){
                carCount++;
            }
        }
        assertEquals(6, carCount);
    }

    @Test
    public void testRacingDay(){
        FNCD fncdTest = new FNCD(50000.0);
        RacingEvent raceTest = new RacingEvent(fncdTest);
        Boolean tes = raceTest.racingDay(fncdTest);
        if(fncdTest.getDay() == "Wensday" || fncdTest.getDay() == "Sunday"){
            assertEquals(true, tes);
        }else{
            assertEquals(false, tes);
        }
    }

    @Test
    public void testBugetSet() {
        double sem = 50000.0;
        FNCD fncdTest = new FNCD(sem);
        double delta = 0.0001;
        assertEquals(sem, fncdTest.getOpBudget(), delta);
    }

    @Test
    public void testVehicle() {
        Vehicle tempCar = new Car("0000", "accord", "honda");
        String model = "accord honda";
        assertEquals(model, tempCar.getName());
    }

    @Test
    public void testStaff() {
        FNCD tFNCD = new FNCD(50000.0);
        Staff staff = new Staff("Jonathan", 000.0, 1, 999.9, 42.0, "Sales", 42.0);
        String name = "Jonathan";
        assertEquals(name, staff.getName());
    }

    @Test
    public void testVehicleCleanliness() {
        Vehicle temp = new Car("0000", "accord", "honda");
        String cleanlines = "Clean";
        assertEquals(cleanlines, temp.getCleanliness());
    }

    @Test
    public void testVehicleCount() {
        FNCD fncdTest = new FNCD(50000.0);
        Opening openTest = new Opening(fncdTest);
        openTest.AddVehicle(fncdTest);
        int carTotal = 54;
        int carCount = 0;
        for(Vehicle v: fncdTest.getVehicleList()){
            if(v instanceof Car){
                carCount++;
            } else if (v instanceof Monster_Truck) {
                carCount++;
            } else if (v instanceof Motorcycle) {
                carCount++;
            } else if (v instanceof Electric_Car) {
                carCount++;
            } else if (v instanceof Performance_Car) {
                carCount++;
            } else if (v instanceof Pickup_Car) {
                carCount++;
            } else if (v instanceof Cunstruction) {
                carCount++;
            } else if (v instanceof Hybrid) {
                carCount++;
            } else if (v instanceof Moped) {
                carCount++;
            }
        }
        assertEquals(carTotal, carCount);
    }

    @Test
    public void testSalesPerson() {
        SalesPerson sa = new SalesPerson("Timmy", 200.0, 1, 230.0, 23.0, "Sales", 20.0);
        String position = "Sales";
        assertEquals(position, sa.getPosition());
    }

    @Test
    public void testMechPerson() {
        Mechanic me = new Mechanic("Jimmy", 140.0, 69, 1000.0, 69.0, "Mechanic", 1.0);
        String postion = "Mechanic";
        assertEquals(postion, me.getPosition());
    }
    @Test
    public void testStaffBonus() {
        Staff thr = new Staff("Jimmy", 140.0, 69, 1000.0, 69.0, "Mechanic", 1.0);
        Double bonus = 1.0;
        assertEquals(bonus, thr.getBonus());
    }
    @Test
    public void testUniiquID(){
        FNCD fncdTest = new FNCD(50000.0);
        Opening openTest = new Opening(fncdTest);
        Staff thr = new Staff("Jimmy", 140.0, 69, 1000.0, 69.0, "Mechanic", 1.0);
        Integer count = 1;
        openTest.internHire(fncdTest.getStaffList(), fncdTest);
        for(Staff s: fncdTest.getStaffList()){
            if(thr.getStaffID() == s.getStaffID()){
                count ++;
            }
        }
        assertEquals(Optional.of(1), count);
    }

}