import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPanel extends JFrame {

    private String positiveWords[] = { "good", "goodjob", "great", "happy", "amazing", "awesome", "excellent",
            "inspiring", "joy", "marvelous", "motivated", "determined", "outgoing", "playful", "fun", "kind",
            "thoughful", "thanks", "thankyou", "thank", "generous", "delightful", "handsome", "pretty", "genius",
            "bright", "useful", "laugh", "hilarious", "optimistic", "peaceful", "freedom", "relax", "humble",
            "courageous", "diligent", "adventerous", "adaptable", "thankful", "hopeful", "lol", "cool" };

    public void userPanel(Object nodeInfo, HashMap<String, User> userData) {

        if (nodeInfo != null && nodeInfo instanceof User) {
            AdminPanel adminPanel = AdminPanel.getInstance();

            User currentUserInfo = userData.get(nodeInfo.toString());

            JFrame userPanelFrame = new JFrame(currentUserInfo.getID());
            JButton followUserButton = new JButton("Follow User");
            JTextField userIDTextField = new JTextField();
            userIDTextField.setHorizontalAlignment(JTextField.CENTER);
            JList followerList = new JList<>(currentUserInfo.getFollowing().toArray());
            JScrollPane follwerListScrollPane = new JScrollPane(followerList);
            JTextArea tweetMessage = new JTextArea();
            JScrollPane tweetMessageScrollPane = new JScrollPane(tweetMessage);
            JButton postTweetButton = new JButton("Post Tweet");
            JList newsFeedList = new JList<>(currentUserInfo.getTweetMessages().toArray());
            JScrollPane newsFeedListScrollPane = new JScrollPane(newsFeedList);

            userPanelFrame.setSize(350, 500);
            userPanelFrame.setVisible(true);
            userPanelFrame.setLayout(null);
            userPanelFrame.setLocationRelativeTo(null);

            userIDTextField.setBounds(5, 10, 170, 50);
            followUserButton.setBounds(175, 10, 170, 50);
            follwerListScrollPane.setBounds(5, 65, 340, 140);
            tweetMessageScrollPane.setBounds(5, 210, 200, 100);
            postTweetButton.setBounds(210, 210, 135, 100);
            newsFeedListScrollPane.setBounds(5, 315, 340, 150);

            userPanelFrame.add(follwerListScrollPane);
            userPanelFrame.add(followUserButton);
            userPanelFrame.add(userIDTextField);
            userPanelFrame.add(tweetMessageScrollPane);
            userPanelFrame.add(postTweetButton);
            userPanelFrame.add(newsFeedListScrollPane);

            followUserButton.addActionListener(e -> {
                String userID = userIDTextField.getText();

                if (userData.containsKey(userID) && currentUserInfo.isContainID(userID) == false
                        && !currentUserInfo.getID().equals(userID)) {
                    currentUserInfo.setFollowing(userID);
                    followerList.setListData(currentUserInfo.getFollowing().toArray());

                    User user = userData.get(userID);
                    // System.out.println(user);
                    user.setFollower(currentUserInfo.getID());


                    userIDTextField.setText("");

                    followerList.invalidate();
                    followerList.validate();
                    followerList.repaint();
                }

            });

            postTweetButton.addActionListener(e -> {
                String tweet = tweetMessage.getText();

                if (!tweet.equals("")) {
                    for(int i = 0; i<positiveWords.length; i++){
                        if(tweet.contains(positiveWords[i])){
                            adminPanel.increasePercentageMessage();
                            System.out.println("Executed");
                            break;
                        }
                    }

                    currentUserInfo.setTweetMessages(tweet, currentUserInfo.getID());
                    newsFeedList.setListData(currentUserInfo.getTweetMessages().toArray());
                    adminPanel.increaseTotalMessage();

                    List<String> followers = currentUserInfo.getFollower();
                    System.out.println(followers);
                    for(int i = 0; i < followers.size(); i++){
                        String temp = followers.get(i);
                        User temp1 = userData.get(temp);
                        temp1.setTweetMessages(tweet, currentUserInfo.getID());
                        newsFeedList.setListData(temp1.getTweetMessages().toArray());
                        newsFeedList.invalidate();
                    }

                    tweetMessage.setText("");
                    // newsFeedList.invalidate();
                    // newsFeedList.validate();
                    // newsFeedList.repaint();
               
                }
            });

        }
    }
}
