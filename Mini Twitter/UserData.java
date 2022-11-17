import java.util.HashMap;

public class UserData implements Visitor1{
    private HashMap<String, Visitor> userData = new HashMap<String, Visitor>();

   

    @Override
    public void add(String ID, Visitor visitor) {
        // TODO Auto-generated method stub
        userData.put(ID, visitor);
    }



    @Override
    public HashMap<String, Visitor> getMap() {
        // TODO Auto-generated method stub
        return userData;
    }

    
}
