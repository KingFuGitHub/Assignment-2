
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class AdminPanel extends JFrame {

    // declaring and initializing variables
    private Data data; 
    private DefaultMutableTreeNode root;
    private UserPanel userPanel = new UserPanel();
    Object nodeInfo;
    
    private int totalMessage = 0;
    private int percentagePositiveMessage = 0;

    private Popup popup = Popup.getInstance();

    private Visitor visitorUser = new UserData();
    private Visitor visitorGroup = new GroupData();



    // private so that the class can be accessed by only getInstance() method
    private static AdminPanel adminPanelObject;

    // private constructor
    private AdminPanel(){}

    // singleton
    public static AdminPanel getInstance(){
        if(adminPanelObject == null){
            // sychonized block to remove overhead
            synchronized(AdminPanel.class){
                if(adminPanelObject == null){
                    adminPanelObject = new AdminPanel();
                }
            }
        }

        // returns the singleton object
        return adminPanelObject;
    }

    // increase the total message by 1
    public void increaseTotalMessage(){
        totalMessage+=1;
    }

    // calculate and return the % positive tweets
    public String getPrecentageMessage(){
        if(totalMessage == 0){
            return "0%";
        }
        double temp1 = percentagePositiveMessage;
        double temp2 = totalMessage;
        return String.format("%.2f",(temp1/temp2)*100.0) + "%";
    }

    // update the positive % message by 1 and the amount of followers
    public void increasePercentageMessage(int followers){
        percentagePositiveMessage += 1 + followers;
    }

    // this is the main admin panel GUI and some logic.
    public void adminPanel() {

        // setting the root to the of type group and setting the name to be Root.
        data = new Group();
        data.setID("Root");
        root = new DefaultMutableTreeNode(data);
        nodeInfo = root.getUserObject();

        // creating the frame for Admin panel giving it the name Mini Twitter
        JFrame adminPanelFrame = new JFrame("Mini Twitter");
        adminPanelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        JScrollPane treeScrollPane = new JScrollPane(tree);

        JLabel labelTreeView = new JLabel("Tree View");

        JButton buttonAddUser = new JButton("Add User");
        JTextField textFieldAddUser = new JTextField();
        textFieldAddUser.setHorizontalAlignment(JTextField.CENTER);

        JButton buttonAddGroup = new JButton("Add group");
        JTextField textFieldAddGroup = new JTextField();
        textFieldAddGroup.setHorizontalAlignment(JTextField.CENTER);

        // buttons objects with their names initialized.
        JButton buttonOpenUser = new JButton("Open user");
        JButton buttonShowUserTotal = new JButton("Show total user");
        JButton buttonShowGroupTotal = new JButton("Show total group");
        JButton buttonShowMessageTotal = new JButton("Show total message");
        JButton buttonShowPositivePercentage = new JButton("Show positive %");

        adminPanelFrame.setSize(800, 500);
        adminPanelFrame.setVisible(true);
        adminPanelFrame.setLayout(null);


        // setting bounds for each object.
        treeScrollPane.setBounds(25, 25, 400, 425);

        labelTreeView.setBounds(30, 5, 100, 20);

        buttonAddUser.setBounds(610, 30, 150, 40);
        textFieldAddUser.setBounds(440, 30, 165, 40);

        buttonAddGroup.setBounds(610, 80, 150, 40);
        textFieldAddGroup.setBounds(440, 80, 165, 40);

        buttonOpenUser.setBounds(440, 130, 320, 40);

        buttonShowUserTotal.setBounds(440, 350, 150, 40);

        buttonShowGroupTotal.setBounds(600, 350, 150, 40);

        buttonShowMessageTotal.setBounds(440, 400, 150, 40);

        buttonShowPositivePercentage.setBounds(600, 400, 150, 40);

        // add the required objects to the admin panel frame.
        adminPanelFrame.add(labelTreeView);
        adminPanelFrame.add(buttonAddUser);
        adminPanelFrame.add(textFieldAddUser);
        adminPanelFrame.add(buttonAddGroup);
        adminPanelFrame.add(textFieldAddGroup);
        adminPanelFrame.add(buttonOpenUser);
        adminPanelFrame.add(buttonShowUserTotal);
        adminPanelFrame.add(buttonShowGroupTotal);
        adminPanelFrame.add(buttonShowMessageTotal);
        adminPanelFrame.add(buttonShowPositivePercentage);
        adminPanelFrame.add(treeScrollPane);


        // add user button
        buttonAddUser.addActionListener(e -> {

            String userName = textFieldAddUser.getText().toString().toLowerCase();

            if (nodeInfo instanceof Group && userName.length() > 0 && !visitorUser.getMap().containsKey(userName)) {

                data = new User();
                data.setID(userName);

                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(data);
                if (root != null) {
                    root.add(userNode);
                } else {
                    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                    root.add(userNode);
                }

                visitorUser.getMap().put(userName, data);

                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
                textFieldAddUser.setText("");
            }else{
                popup.showPopup("error", "invalid input");
            }

        });


        // add group button
        buttonAddGroup.addActionListener(e -> {

            String groupName = textFieldAddGroup.getText().toString().toUpperCase();

            if (nodeInfo instanceof Group && groupName.length() > 0 && !visitorGroup.getMap().containsKey(groupName)) {

                data = new Group();
                data.setID(groupName);

                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(data);
                if (root != null) {
                    root.add(groupNode);
                } else {
                    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                    root.add(groupNode);
                }

                visitorGroup.getMap().put(groupName, data);

                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
                textFieldAddGroup.setText("");
            }else{
                popup.showPopup("error", "invalid input");
            }

        });

        // show user total button
        buttonShowUserTotal.addActionListener(e -> {
            popup.showPopup("Total user", "Total user(s): " + Integer.toString(visitorUser.getMap().size()));

        });

        // show total group button
        buttonShowGroupTotal.addActionListener(e -> {
            popup.showPopup("Total group", "Total group(s): " + Integer.toString(visitorGroup.getMap().size()));

        });

        // open user button
        buttonOpenUser.addActionListener(e -> {
            if(nodeInfo instanceof User){
                userPanel.userPanel(nodeInfo, visitorUser.getMap());
            }else{
                popup.showPopup("Error", "Did not select a user.");
            }

        });

        // show total message button
        buttonShowMessageTotal.addActionListener(e->{
            popup.showPopup("Total message", "Total message(s): " + totalMessage);
        });

        // show positive % tweets
        buttonShowPositivePercentage.addActionListener(e->{
            popup.showPopup("Positive percentage", "Positive percentage: " + getPrecentageMessage());
        });

        // a selection listener for the tree view.
        tree.addTreeSelectionListener(e -> {
            root = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if (root == null) {
                return;
            }
            // get the tree node information
            nodeInfo = root.getUserObject();
        });

    }
}