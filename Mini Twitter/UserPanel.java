
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPanel extends JFrame {

    // a list of positive words
    private String positiveWords[] = { "good", "goodjob", "great", "happy", "amazing", "awesome", "excellent",
            "inspiring", "joy", "marvelous", "motivated", "determined", "outgoing", "playful", "fun", "kind",
            "thoughful", "thanks", "thankyou", "thank", "generous", "delightful", "handsome", "pretty", "genius",
            "bright", "useful", "laugh", "hilarious", "optimistic", "peaceful", "freedom", "relax", "humble",
            "courageous", "diligent", "adventerous", "adaptable", "thankful", "hopeful", "lol", "cool" };

    private HashMap<String, DefaultListModel> DefaultListModelHashMap = new HashMap<String, DefaultListModel>();
    private HashMap<String, JLabel> JListHashMap = new HashMap<String, JLabel>();


    // the user panel
    public void userPanel(Object nodeInfo, HashMap<String, Data> userData) {

        if (nodeInfo != null && nodeInfo instanceof User) {

            AdminPanel adminPanel = AdminPanel.getInstance();

            User currentUserInfo = (User) userData.get(nodeInfo.toString());
            JLabel labelLastUpdated = new JLabel("Last updated: " + currentUserInfo.getLastUpdate());
            JLabel labelCreatedDate = new JLabel("Created Date: " + currentUserInfo.getCreatedDate());

            JFrame userPanelFrame = new JFrame(currentUserInfo.getID());
            JButton followUserButton = new JButton("Follow User");
            JTextField userIDTextField = new JTextField();
            userIDTextField.setHorizontalAlignment(JTextField.CENTER);
            DefaultListModel followerListModel = new DefaultListModel();

            for (int i = 0; i < currentUserInfo.getFollowing().size(); i++) {
                followerListModel.addElement(
                        "[" + currentUserInfo.getAFollowingDate(i) + "] " + currentUserInfo.getAFollowing(i));
            }

            JList followerList = new JList(followerListModel);

            JScrollPane follwerListScrollPane = new JScrollPane(followerList);
            JTextArea tweetMessage = new JTextArea();
            JScrollPane tweetMessageScrollPane = new JScrollPane(tweetMessage);
            JButton postTweetButton = new JButton("Post Tweet");
            DefaultListModel newsFeedListModel = new DefaultListModel();

            for (int i = 0; i < currentUserInfo.getTweetMessages().size(); i++) {
                newsFeedListModel.addElement("[" + currentUserInfo.getATweetMessagesDate(i) + " "
                        + currentUserInfo.getAFrom(i) + "] " + currentUserInfo.getATweetMessage(i));
            }

            JList newsFeedList = new JList(newsFeedListModel);

            DefaultListModelHashMap.put(currentUserInfo.getID(), newsFeedListModel);
            JListHashMap.put(currentUserInfo.getID(), labelLastUpdated);

            JScrollPane newsFeedListScrollPane = new JScrollPane(newsFeedList);

            userPanelFrame.setSize(350, 545);
            userPanelFrame.setVisible(true);
            userPanelFrame.setLayout(null);
            userPanelFrame.setLocationRelativeTo(null);

            labelLastUpdated.setBounds(15, 5, 300, 20);
            userIDTextField.setBounds(5, 30, 170, 50);
            followUserButton.setBounds(175, 30, 170, 50);
            follwerListScrollPane.setBounds(5, 85, 340, 140);
            tweetMessageScrollPane.setBounds(5, 230, 200, 100);
            postTweetButton.setBounds(210, 230, 135, 100);
            newsFeedListScrollPane.setBounds(5, 335, 340, 150);
            labelCreatedDate.setBounds(170, 490, 200, 20);

            userPanelFrame.add(labelLastUpdated);
            userPanelFrame.add(follwerListScrollPane);
            userPanelFrame.add(followUserButton);
            userPanelFrame.add(userIDTextField);
            userPanelFrame.add(tweetMessageScrollPane);
            userPanelFrame.add(postTweetButton);
            userPanelFrame.add(newsFeedListScrollPane);
            userPanelFrame.add(labelCreatedDate);

            // follow user button
            followUserButton.addActionListener(e -> {

                String userID = userIDTextField.getText();
                if (userData.containsKey(userID) && currentUserInfo.isContainID(userID) == false
                        && !currentUserInfo.getID().equals(userID)) {

                    currentUserInfo.setFollowing(userID);
                    currentUserInfo.setFollowingDate();
                    followerListModel.add(0, "[" + currentUserInfo.getAFollowingDate(0) + "] " + userID);

                    // observer design pattern following a user will attach self to that user.
                    User user = (User) userData.get(userID);
                    user.setFollower(currentUserInfo.getID());
                    user.attach(currentUserInfo);

                    // empty the textfield
                    userIDTextField.setText("");
                }

            });

            // post tweet button
            postTweetButton.addActionListener(e -> {

                System.out.println(currentUserInfo.getFrom());
                String tweet = tweetMessage.getText();

                if (!tweet.equals("")) {

                    // scan for positive words and count them.
                    for (int i = 0; i < positiveWords.length; i++) {
                        if (tweet.contains(positiveWords[i])) {
                            adminPanel.increasePercentageMessage(currentUserInfo.getFollower().size());
                            break;
                        }
                    }

                    currentUserInfo.setTweetMessages(tweet, currentUserInfo.getID());

                    currentUserInfo.setFrom(currentUserInfo.getID());
                    currentUserInfo.setTweetMessagesDate();
                    String tweetToBeAdded = "[" + currentUserInfo.getATweetMessagesDate(0) + " "
                            + currentUserInfo.getAFrom(0) + "] " + tweet;

                    newsFeedListModel.add(0, tweetToBeAdded);

                    // increase total message
                    adminPanel.increaseTotalMessage();

                    // Observer design pattern notifying all users for update.
                    currentUserInfo.notifyUsers(tweet, currentUserInfo.getID());

                    currentUserInfo.setLastUpdatedMilli();
                    currentUserInfo.setLastUpdated();
                    currentUserInfo.notifyUsers(currentUserInfo.getLastUpdate(), currentUserInfo.getID());
                    labelLastUpdated.setText("Last updated: " + currentUserInfo.getLastUpdate());

                    // update the UI and increase the total message
                    for (int i = 0; i < currentUserInfo.getFollower().size(); i++) {
                        if (DefaultListModelHashMap.containsKey(currentUserInfo.getAFollower(i))) {
                            DefaultListModelHashMap.get(currentUserInfo.getAFollower(i)).add(0, tweetToBeAdded);
                            adminPanel.increaseTotalMessage();
                            JLabel followerText = JListHashMap.get(currentUserInfo.getAFollower(i));
                            followerText.setText("Last updated: " + currentUserInfo.getLastUpdate());
                        }
                    }

                    // tempty the textfield
                    tweetMessage.setText("");
                }
            });

        }
    }
}
