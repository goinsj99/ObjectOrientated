public class RemoteControl {
    Command slot;
    public RemoteControl(){}
    public void setCommand(Command command){
        slot = command;
    }
    public void execute(FNCD fncd){

            slot.execute(fncd);
    }
    public void execute2(FNCD fncd){
        slot.execute2(fncd);
    }
    public void execute3(FNCD fncd){
        slot.execute3(fncd);
    }
    public void execute4(FNCD fncd){
        slot.execute4(fncd);
    }
    public void execute5(FNCD fncd){
        slot.execute5(fncd);
    }
    public void execute6(FNCD fncd){
        slot.execute6(fncd);
    }
    public void execute7(FNCD fncd){
        slot.execute7(fncd);
    }
//    public void executeSet(){
//        slot.executeSet();
//    }

}
