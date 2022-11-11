import java.util.HashMap;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class AdminPanel extends JFrame {


    private Visitor data; 
    private User user;
    private Group group;
    private DefaultMutableTreeNode root;
    private UserPanel userPanel = new UserPanel();
    Object nodeInfo;
    private int totalMessage = 0;
    private int percentagePositiveMessage = 0;

    public HashMap<String, Visitor> userData = new HashMap<String, Visitor>();
    public HashMap<String, Visitor> groupData = new HashMap<String, Visitor>();
    // Popup popup = new;
    private Popup popup = Popup.getInstance();


    private static AdminPanel adminPanelObject;
    private AdminPanel(){}


    public static AdminPanel getInstance(){
        if(adminPanelObject == null){
            synchronized(AdminPanel.class){
                if(adminPanelObject == null){
                    adminPanelObject = new AdminPanel();
                }
            }
        }

        return adminPanelObject;
    }

    public void increaseTotalMessage(){
        totalMessage+=1;
    }

    public String getPrecentageMessage(){
        if(totalMessage == 0){
            return "0%";
        }
        double temp1 = percentagePositiveMessage;
        double temp2 = totalMessage;
        return String.format("%.2f",(temp1/temp2)*100.0) + "%";
    }

    public void increasePercentageMessage(){
        percentagePositiveMessage += 1;
    }

    public void adminPanel() {

        data = new Group();
        data.setID("Root");
        // group = new Group();
        // group.setID("Root");
        root = new DefaultMutableTreeNode(data);
        nodeInfo = root.getUserObject();

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

        JButton buttonOpenUser = new JButton("Open user");
        JButton buttonShowUserTotal = new JButton("Show total user");
        JButton buttonShowGroupTotal = new JButton("Show total group");
        JButton buttonShowMessageTotal = new JButton("Show total message");
        JButton buttonShowPositivePercentage = new JButton("Show positive %");

        adminPanelFrame.setSize(800, 500);
        adminPanelFrame.setVisible(true);
        adminPanelFrame.setLayout(null);

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

        buttonAddUser.addActionListener(e -> {

            String userName = textFieldAddUser.getText().toString().toLowerCase();

            if (nodeInfo instanceof Group && userName.length() > 0 && !userData.containsKey(userName)) {
                data = new User();
                data.setID(userName);
                // user = new User();
                // user.setID(userName);

                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(data);
                if (root != null) {
                    root.add(userNode);
                } else {
                    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                    root.add(userNode);
                }

                userData.put(userName, data);
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
                textFieldAddUser.setText("");
            }else{
                // Popup popup = Popup.getInstance();
                popup.showPopup("error", "invalid input");
            }

        });

        buttonAddGroup.addActionListener(e -> {

            String groupName = textFieldAddGroup.getText().toString().toUpperCase();

            if (nodeInfo instanceof Group && groupName.length() > 0 && !groupData.containsKey(groupName)) {
                data = new Group();
                data.setID(groupName);

                // group = new Group();
                // group.setID(groupName);

                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(data);
                if (root != null) {
                    root.add(groupNode);
                } else {
                    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                    root.add(groupNode);
                }

                groupData.put(groupName, data);
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
                textFieldAddGroup.setText("");
            }else{
                // Popup popup = Popup.getInstance();
                popup.showPopup("error", "invalid input");
            }

        });

        buttonShowUserTotal.addActionListener(e -> {
            // Popup popup = Popup.getInstance();
            popup.showPopup("Total user", "Total user(s): " + Integer.toString(userData.size()));
        });

        buttonShowGroupTotal.addActionListener(e -> {
            // Popup popup = Popup.getInstance();
            popup.showPopup("Total group", "Total group(s): " + Integer.toString(groupData.size()));
        });

        buttonOpenUser.addActionListener(e -> {
            userPanel.userPanel(nodeInfo, userData);
        });

        buttonShowMessageTotal.addActionListener(e->{
            // Popup popup = Popup.getInstance();
            popup.showPopup("Total message", "Total message(s): " + totalMessage);
        });

        buttonShowPositivePercentage.addActionListener(e->{
            // Popup popup = Popup.getInstance();
            popup.showPopup("Positive percentage", "Positive percentage: " + getPrecentageMessage());
        });

        tree.addTreeSelectionListener(e -> {
            root = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if (root == null) {
                return;
            }
            nodeInfo = root.getUserObject();
            System.out.println(nodeInfo);
        });

    }
}