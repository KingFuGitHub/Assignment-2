
// a visitor class to get data for the users and groups
public interface Data {
    public void setID(String ID);
    public String getID();
    public void setCreatedDate();
    public String getCreatedDate();
    public User getUser();
}
