import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Popup extends JFrame{

    private static Popup popObject;
    private Popup(){}

    public static Popup getInstance(){
        if(popObject == null){
            synchronized(AdminPanel.class){
                if(popObject == null){
                    popObject = new Popup();
                }
            }
        }

        return popObject;
    }    

    public void showPopup(String name, String data){
        JFrame popUpFrameTotalUser = new JFrame(name);
        popUpFrameTotalUser.setLocationRelativeTo(null);

        JLabel labelPopupTotalUser = new JLabel(data, SwingConstants.CENTER);

        popUpFrameTotalUser.setSize(300, 200);

        popUpFrameTotalUser.add(labelPopupTotalUser);

        popUpFrameTotalUser.setVisible(true);
    }
   
}
