import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPanel extends JFrame {

    public void userPanel(Object nodeInfo, HashMap<String, User> userData) {

        String week[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturaday", "Sunday" };

        if (nodeInfo != null && nodeInfo instanceof User) {
            User currentUserInfo = userData.get(nodeInfo.toString());
            // String week1[] = currentUserInfo.getFollowing();

            JFrame userPanelFrame = new JFrame(currentUserInfo.getID());
            JButton followUserButton = new JButton("Follow User");
            JTextField userIDTextField = new JTextField();
            userIDTextField.setHorizontalAlignment(JTextField.CENTER);
            // JList followerList = new JList(user.getFollowing().toArray());
            JList followerList = new JList<>(currentUserInfo.getFollowing().toArray());
            followerList.setSelectedIndex(2);
            JTextArea tweetMessage = new JTextArea();
            JScrollPane tweetMessageScrollPane = new JScrollPane(tweetMessage);
            JButton postTweetButton = new JButton("Post Tweet");
            JList newsFeedList = new JList<>(week);
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

            // userPanelFrame.invalidate();
            // userPanelFrame.validate();
            // userPanelFrame.repaint();

            // public String userID1 = userIDTextField.getText().toString().toLowerCase();

            followUserButton.addActionListener(e -> {
                String userID = userIDTextField.getText();

                // System.out.println("1. " + userID);
                // System.out.println("2. " + currentUserInfo.isContainID(userID));
                // System.out.println("3. " +
                // currentUserInfo.isContainID(currentUserInfo.getID()));
                // System.out.println("4. " + currentUserInfo.isContainID(nodeInfo.toString()));
                // System.out.println("5. " + currentUserInfo.getID());
                // System.out.println("6. " + nodeInfo.toString());
                System.out.println("Size: " + currentUserInfo.getFollowing().size());
                    System.out.println(nodeInfo.toString().toLowerCase());
                    System.out.println(currentUserInfo.getID().toString().toLowerCase());
                    System.out.println(userID);
                if (userID != "" && currentUserInfo.isContainID(userID) == false && nodeInfo.toString().toLowerCase() != userIDTextField.getText()) {
                    currentUserInfo.setFollowing(userID);

                    followerList.setListData(currentUserInfo.getFollowing().toArray());

                    System.out.println("executed");
                    followerList.invalidate();
                    followerList.validate();
                    followerList.repaint();
                }
                followerList.invalidate();
                followerList.validate();
                followerList.repaint();
                userPanelFrame.invalidate();
                userPanelFrame.validate();
                userPanelFrame.repaint();
            });

        }
    }
}
