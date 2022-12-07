import java.util.HashMap;

public class UserData implements Visitor{
    private HashMap<String, Data> userData = new HashMap<String, Data>();

   
    // to add a node user or group
    // @Override
    public void addNode(String ID, Data user) {
        userData.put(ID, user);
    }

    // to get the hashmap
    @Override
    public HashMap<String, Data> getMap() {
        return userData;
    }

    



    
}
