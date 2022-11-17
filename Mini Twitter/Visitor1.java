import java.util.HashMap;

public interface Visitor1 {
    public void add(String ID, Visitor visitor);
    public HashMap<String, Visitor> getMap();
    
}
