
public class User implements Data {

    private String userID;

    @Override
    public String getName() {
        return userID.toString();
    }

    @Override
    public void setName(String name) {
        this.userID = name;
    }

    public String toString() {
        return userID;
    }

}
