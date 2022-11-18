import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Data, Observer, Subject {

    // declaring and initializing variables
    private String userID;
    private List<String> following = new ArrayList<String>();
    private List<String> follower = new ArrayList<String>();
    private List<String> tweetMessages = new ArrayList<String>();
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    private List<Observer> observers = new ArrayList<Observer>();
    private List<String> followingDates = new ArrayList<String>();

    private List<String> tweetMessagesDate = new ArrayList<String>();

    private List<String> from = new ArrayList<String>();

    // getters and setters are below including some helper methods
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
        // following.add(0, "[" + formatTimeFollowing() + "] " + userID);
        following.add(0, userID);
    }

    public List<String> getFollowing() {
        return following;
    }

    public String getAFollowing(int index) {
        return following.get(index);
    }

    public boolean isContainID(String userID) {
        if (following.contains(userID)) {
            return true;
        }
        return false;
    }

    public void setTweetMessages(String tweetMessage, String userID) {
        tweetMessages.add(0, tweetMessage);
    }

    public List<String> getTweetMessages() {
        return tweetMessages;
    }

    public String getATweetMessage(int index) {

        return tweetMessages.get(index);
    }

    public String formatTime() {
        Long time = System.currentTimeMillis();
        Date date = new Date(time);
        return format.format(date);
    }

    public void setFollower(String userID) {
        follower.add(userID);
    }

    public List<String> getFollower() {
        return follower;
    }

    public String getAFollower(int index) {
        return follower.get(index);
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void update(String message, String userID) {
        setTweetMessages(message, userID);
        setTweetMessagesDate();
        setFrom(userID);
    }

    @Override
    public void notifyUsers(String message, String userID) {
        for (Observer observer : observers) {
            observer.update(message, userID);
        }
    }

    public String getAFollowingDate(int index) {
        return followingDates.get(index);
    }

    public String getFollowingDate() {
        return formatTime();
    }

    public void setFollowingDate() {
        followingDates.add(formatTime());
    }

    public List<String> getFollowingDates() {
        return followingDates;
    }

    public String getATweetMessagesDate(int index) {
        return tweetMessagesDate.get(index);
    }

    public void setTweetMessagesDate() {
        tweetMessagesDate.add(formatTime());
    }

    public List<String> getTweetMessagesDates() {
        return tweetMessagesDate;
    }

    public String getAFrom(int index) {
        return from.get(index);
    }

    public void setFrom(String name) {
        from.add(0, name);
    }

    public List<String> getFrom() {
        return from;
    }

}
