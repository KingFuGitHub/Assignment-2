import javax.swing.JFrame;
import javax.swing.JTextField;

public class UserPanel extends JFrame{
    

    public void userPanel(boolean isVisible, Object nodeInfo){
        
        if(nodeInfo != null && nodeInfo instanceof User){
            
            JFrame userPanelFrame = new JFrame(nodeInfo.toString());
            JTextField userIDTexField = new JTextField();
            userIDTexField.setHorizontalAlignment(JTextField.CENTER);

            userPanelFrame.setSize(450, 600);
            userPanelFrame.setVisible(isVisible);
            userPanelFrame.setLayout(null);
            userPanelFrame.setLocationRelativeTo(null);

            

            userPanelFrame.invalidate();
            userPanelFrame.validate();
            userPanelFrame.repaint();


        }
    }
}
