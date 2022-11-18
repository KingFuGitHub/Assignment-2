import java.util.HashMap;

public class GroupData implements Visitor{

    private HashMap<String, Data> groupData = new HashMap<String, Data>();

    @Override
    public void addNode(String ID, Data visitor) {
        groupData.put(ID, visitor);

        
    }

    @Override
    public HashMap<String, Data> getMap() {
        return groupData;
    }


    
}