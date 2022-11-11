import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Popup extends JFrame{

    // private so that the class can only be accessed by only getInstance() method.
    private static Popup popObject;

    // private constructor
    private Popup(){}

    // Singleton
    public static Popup getInstance(){
        if(popObject == null){
            // synchronized block to remove overhead
            synchronized(AdminPanel.class){
                if(popObject == null){
                    popObject = new Popup();
                }
            }
        }

        // returns the singleton object
        return popObject;
    }    

    // to show all the popups
    public void showPopup(String name, String data){
        JFrame popUpFrameTotalUser = new JFrame(name);
        popUpFrameTotalUser.setLocationRelativeTo(null);

        JLabel labelPopupTotalUser = new JLabel(data, SwingConstants.CENTER);

        popUpFrameTotalUser.setSize(300, 200);

        popUpFrameTotalUser.add(labelPopupTotalUser);

        popUpFrameTotalUser.setVisible(true);
    }
   
}
