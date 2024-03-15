public class anotherRep {
    Staff tempStaff;


    public void Tryy(FNCD fncd){
        getStaffName now = new getStaffName(fncd);

        for(Staff staff : fncd.getStaffList()){
            if (now.getTheName() != staff.getName()){
                System.out.println("Your new sales rep is ");
                break;
            }
        }
    }
}
