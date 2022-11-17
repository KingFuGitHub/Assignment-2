import java.util.HashMap;

public class GroupData implements Visitor1{

    private HashMap<String, Visitor> groupData = new HashMap<String, Visitor>();

    @Override
    public void add(String ID, Visitor visitor) {
        // TODO Auto-generated method stub
        groupData.put(ID, visitor);

        
    }

    @Override
    public HashMap<String, Visitor> getMap() {
        // TODO Auto-generated method stub
        return groupData;
    }


    
}