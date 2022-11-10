import javax.swing.JFrame;

public class UserPanel extends JFrame{
    

    public void userPanel(boolean isVisible, Object nodeInfo){
        
        if(nodeInfo != null && nodeInfo instanceof User){
            
            JFrame userPanelFrame = new JFrame(nodeInfo.toString());

            userPanelFrame.setSize(450, 600);
            userPanelFrame.setVisible(isVisible);
            userPanelFrame.setLayout(null);

            userPanelFrame.invalidate();
            userPanelFrame.validate();
            userPanelFrame.repaint();

            
        }
    }
}
