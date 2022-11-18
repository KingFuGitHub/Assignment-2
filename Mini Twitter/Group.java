public class Group implements Data {
    
    // delare variables and initialize it
    private String groupID;

    //getters and setters

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
