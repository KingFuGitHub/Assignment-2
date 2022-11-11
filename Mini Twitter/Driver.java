
public class Driver{

    public static void main(String[] args) {
        // creating and initialize the admin panel object (singleton)
        AdminPanel adminPanel = AdminPanel.getInstance();
        adminPanel.adminPanel();
    }
}