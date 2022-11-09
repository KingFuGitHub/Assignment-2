public class Group implements Data {
    
    private String groupID;

    @Override
    public void setName(String groupID){
        this.groupID = groupID;
    }

    @Override
    public String getName(){
        return groupID;
    }

    public String toString(){
        return groupID;
    }

}
