import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPanel extends JFrame {

    private User currentUserInfo;

    public void userPanel(Object nodeInfo, HashMap<String, User> userData) {
        // private User test = user;

        String week[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturaday", "Sunday" };

        if (nodeInfo != null && nodeInfo instanceof User) {
            currentUserInfo = userData.get(nodeInfo.toString());

            JFrame userPanelFrame = new JFrame(currentUserInfo.getID());
            JButton followUserButton = new JButton("Follow User");
            JTextField userIDTextField = new JTextField();
            userIDTextField.setHorizontalAlignment(JTextField.CENTER);
            // JList followerList = new JList(user.getFollowing().toArray());
            JList followerList = new JList(currentUserInfo.getFollowing().toArray());
            followerList.setSelectedIndex(2);
            JTextArea tweetMessage = new JTextArea();
            JScrollPane tweetMessageScrollPane = new JScrollPane(tweetMessage);
            JButton postTweetButton = new JButton("Post Tweet");
            JList newsFeedList = new JList(week);
            newsFeedList.setSelectedIndex(3);

            userPanelFrame.setSize(350, 500);
            userPanelFrame.setVisible(true);
            userPanelFrame.setLayout(null);
            userPanelFrame.setLocationRelativeTo(null);

            userIDTextField.setBounds(5, 10, 170, 50);
            followUserButton.setBounds(175, 10, 170, 50);
            followerList.setBounds(5, 65, 340, 140);
            tweetMessageScrollPane.setBounds(5, 210, 200, 100);
            postTweetButton.setBounds(210, 210, 135, 100);
            newsFeedList.setBounds(5, 315, 340, 150);

            userPanelFrame.add(followerList);
            userPanelFrame.add(followUserButton);
            userPanelFrame.add(userIDTextField);
            userPanelFrame.add(tweetMessageScrollPane);
            userPanelFrame.add(postTweetButton);
            userPanelFrame.add(newsFeedList);

            userPanelFrame.invalidate();
            userPanelFrame.validate();
            userPanelFrame.repaint();


            followUserButton.addActionListener(e -> {
                String userID = userIDTextField.getText().toString().toLowerCase();
                if(userID != ""){
                    System.out.println("1. true: " + userID);
                }

                if(currentUserInfo.isContainID(userID) == false){
                    System.out.println("2. true: " + currentUserInfo.isContainID(userID));
                }

                if(!currentUserInfo.getID().equals(userID)){
                    System.out.println("3. true: " + currentUserInfo.getID() + " | " + userID);

                }
                if (userID != "" && currentUserInfo.isContainID(userID) == false && currentUserInfo.getID().toString() != userID) {
                    currentUserInfo.setFollowing(userID);
                    followerList.setListData(currentUserInfo.getFollowing().toArray());

                    // followerList.invalidate();
                    // followerList.validate();
                    // followerList.repaint();
                }
                userPanelFrame.invalidate();
                userPanelFrame.validate();
                userPanelFrame.repaint();
            });

        }
    }
}
