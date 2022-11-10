import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Data {

    private String userID;
    private List<String> following = new ArrayList<String>();
    private List<String> tweetMessages = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");

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
        following.add("[" + formatTimeFollowing() + "] " + userID);
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

    public void setTweetMessages(String tweetMessage){
        tweetMessages.add("[" + formatTimeTweetMessages()+ " " + userID + "] " + tweetMessage);
    }

    public List<String> getTweetMessages(){
        return tweetMessages;
    }

    public String formatTimeTweetMessages(){
        Long time = System.currentTimeMillis();
        Date date = new Date(time);
        return format.format(date);
    }

    public String formatTimeFollowing(){
        Long time = System.currentTimeMillis();
        Date date = new Date(time);
        return date.toString();
    }

}
