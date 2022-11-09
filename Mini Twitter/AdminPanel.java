import java.util.HashMap;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class AdminPanel extends JFrame {

    private User user;
    private Group group;
    private DefaultMutableTreeNode root;
    Object nodeInfo;

    HashMap<String, User> userData = new HashMap<String, User>();
    HashMap<String, Group> groupData = new HashMap<String, Group>();

    public void adminGUI() {

        group = new Group();
        group.setName("Root");
        root = new DefaultMutableTreeNode(group);
        nodeInfo = root.getUserObject();

        JFrame frame = new JFrame("Mini Twitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame popUpFrameTotalUser = new JFrame("Total User");
        JPanel panelTotalUser = new JPanel();


        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        JScrollPane treeScrollPane = new JScrollPane(tree);

        JLabel labelTreeView = new JLabel("Tree View");
        JLabel labelPopupTotalUser = new JLabel("Total user(s): " + userData.size());

        JButton buttonAddUser = new JButton("Add User");
        JTextField textFieldAddUser = new JTextField();
        textFieldAddUser.setHorizontalAlignment(JTextField.CENTER);

        JButton buttonAddGroup = new JButton("Add group");
        JTextField textFieldAddGroup = new JTextField();
        textFieldAddGroup.setHorizontalAlignment(JTextField.CENTER);

        JButton buttonOpenUser = new JButton("Open user");

        JButton buttonShowUserTotal = new JButton("Show user total");
        JButton buttonShowGroupTotal = new JButton("Show group total");
        JButton buttonShowMessageTotal = new JButton("Show message total");
        JButton buttonShowPositivePercentage = new JButton("Show positive %");

        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setLayout(null);

        popUpFrameTotalUser.setSize(300, 200);
        popUpFrameTotalUser.setVisible(false);
        popUpFrameTotalUser.setLayout(null);

        treeScrollPane.setBounds(25, 25, 400, 425);

        labelTreeView.setBounds(30, 5, 100, 20);
        labelPopupTotalUser.setBounds(100,60, 250, 20);

        buttonAddUser.setBounds(610, 30, 150, 40);
        textFieldAddUser.setBounds(440, 30, 165, 40);

        buttonAddGroup.setBounds(610, 80, 150, 40);
        textFieldAddGroup.setBounds(440, 80, 165, 40);

        buttonOpenUser.setBounds(440, 130, 320, 40);

        buttonShowUserTotal.setBounds(440, 350, 150, 40);

        buttonShowGroupTotal.setBounds(600, 350, 150, 40);

        buttonShowMessageTotal.setBounds(440, 400, 150, 40);

        buttonShowPositivePercentage.setBounds(600, 400, 150, 40);

        frame.add(labelTreeView);
        frame.add(buttonAddUser);
        frame.add(textFieldAddUser);
        frame.add(buttonAddGroup);
        frame.add(textFieldAddGroup);
        frame.add(buttonOpenUser);
        frame.add(buttonShowUserTotal);
        frame.add(buttonShowGroupTotal);
        frame.add(buttonShowMessageTotal);
        frame.add(buttonShowPositivePercentage);
        frame.add(treeScrollPane);

        popUpFrameTotalUser.add(panelTotalUser);
        popUpFrameTotalUser.add(labelPopupTotalUser);

        buttonAddUser.addActionListener(e -> {

            String userName = textFieldAddUser.getText().toString().toLowerCase();

            if (nodeInfo instanceof Group && userName.length() > 0 && !userData.containsKey(userName)) {
                user = new User();
                user.setName(userName);

                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(user);
                if (root != null) {
                    root.add(userNode);
                } else {
                    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                    root.add(userNode);
                }

                userData.put(userName, user);
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
            }
        });

        buttonAddGroup.addActionListener(e -> {

            String groupName = textFieldAddGroup.getText().toString().toUpperCase();

            if (nodeInfo instanceof Group && groupName.length() > 0 && !groupData.containsKey(groupName)) {
                group = new Group();
                group.setName(groupName);
                
                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group);
                if (root != null) {
                    root.add(groupNode);
                } else {
                    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                    root.add(groupNode);
                }

                groupData.put(groupName, group);
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
            }
        });

        buttonShowUserTotal.addActionListener(e ->{
            popUpFrameTotalUser.setVisible(true);
            labelPopupTotalUser.setText("Total user(s): " + userData.size());
            System.out.println(userData.size());
        });

        tree.addTreeSelectionListener(e -> {
            root = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (root == null) {
                return;
            }

            nodeInfo = root.getUserObject();
        });

    }
}