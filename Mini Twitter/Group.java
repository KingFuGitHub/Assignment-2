public class Group implements Data {
    
    private String groupID;

    @Override
    public void setID(String groupID){
        this.groupID = groupID;
    }

    @Override
    public String getID(){
        return groupID;
    }

    public String toString(){
        return groupID;
    }

}
