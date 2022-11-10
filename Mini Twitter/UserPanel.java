import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPanel extends JFrame{
    

    public void userPanel(boolean isVisible, Object nodeInfo){

        String week[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturaday", "Sunday"}; 
        
        if(nodeInfo != null && nodeInfo instanceof User){
            
            JFrame userPanelFrame = new JFrame(nodeInfo.toString());
            JButton followUserButton = new JButton("Follow User");
            JTextField userIDTexField = new JTextField();
            userIDTexField.setHorizontalAlignment(JTextField.CENTER);
            JList followerList = new JList(week);
            followerList.setSelectedIndex(2);
            JTextArea tweetMessage = new JTextArea();
            JScrollPane tweetMessageScrollPane = new JScrollPane(tweetMessage);
            JButton postTweetButton = new JButton("Post Tweet");
            JList newsFeedList = new JList(week);
            newsFeedList.setSelectedIndex(3);

            userPanelFrame.setSize(350, 500);
            userPanelFrame.setVisible(isVisible);
            userPanelFrame.setLayout(null);
            userPanelFrame.setLocationRelativeTo(null);

            userIDTexField.setBounds(5, 10, 170, 50);
            followUserButton.setBounds(175, 10, 170, 50);
            followerList.setBounds(5, 65, 340, 140);
            tweetMessageScrollPane.setBounds(5, 210, 200, 100);
            postTweetButton.setBounds(210, 210,135, 100);
            newsFeedList.setBounds(5, 315, 340, 150);

            userPanelFrame.add(followerList);
            userPanelFrame.add(followUserButton);
            userPanelFrame.add(userIDTexField);
            userPanelFrame.add(tweetMessageScrollPane);
            userPanelFrame.add(postTweetButton);
            userPanelFrame.add(newsFeedList);

            userPanelFrame.invalidate();
            userPanelFrame.validate();
            userPanelFrame.repaint();




        }
    }
}
