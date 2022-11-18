import java.util.HashMap;

// visitor pattern to update the number of users and groups
public interface Visitor {
    public void addNode(String ID, Data visitor);
    public HashMap<String, Data> getMap();
    
}
