import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Visitor, Observer, Subject {

    private String userID;
    private List<String> following = new ArrayList<String>();
    private List<String> follower = new ArrayList<String>();
    private List<String> tweetMessages = new ArrayList<String>();
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    private List<Observer> observers = new ArrayList<Observer>();

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
        following.add(0, "[" + formatTimeFollowing() + "] " + userID);
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

    public void setTweetMessages(String tweetMessage, String userID){
        tweetMessages.add(0, "[" + formatTimeTweetMessages()+ " " + userID + "] " + tweetMessage);
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

    public void setFollower(String userID){
        follower.add(userID);
    }

    public List<String> getFollower(){
        return follower;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    // @Override
    // public void notify(String message) {
    //     for(Observer observer: observers){
    //         observer.update(message);
    //     }        
    // }

    @Override
    public void update(String message, String userID) {
        setTweetMessages(message, userID);
    }

    @Override
    public void notifyUsers(String message, String userID) {
        for(Observer observer: observers){
            observer.update(message, userID);
        }            
    }

}
