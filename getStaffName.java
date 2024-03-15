import java.util.List;

public class getStaffName {
    FNCD fncd;
    Staff staff;
    Staff tempStaff;
    List<Staff> staffList;
    private String tname;
    Staff tStaff;
    getStaffName(FNCD fncd){
        this.staffList = fncd.getStaffList();
        this.staff = staff;
    }
    public String getTheName(){

        Staff tempStaff;
        for (Staff staff : staffList) {
            if (staff instanceof SalesPerson) {
                tname = staff.getName();
                this.tStaff = staff;
                break;
            }
        }
        System.out.println("Salesperson name is " + tname);
        return tname;
    }
}