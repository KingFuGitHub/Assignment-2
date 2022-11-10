import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Data {

    private String userID;
    private List<String> following = new ArrayList<String>();


    @Override
    public String getID() {
        return userID.toString();
    }

    @Override
    public void setID(String ID) {
        this.userID = ID;
    }

    public String toString() {
        return userID;
    }

    public void setFollowing(String userID) {
        following.add(userID);
    }

    public List<String> getFollowing() {
        return following;
    }

    public boolean isContainID(String userID) {
        if (following.contains(userID)) {
            return true;
        }

        return false;
    }

}
