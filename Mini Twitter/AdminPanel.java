import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class AdminPanel extends JFrame {

    private Data data;
    private DefaultMutableTreeNode root;
    private UserPanel userPanel = new UserPanel();
    Object nodeInfo;

    HashMap<String, Data> userData = new HashMap<String, Data>();
    HashMap<String, Data> groupData = new HashMap<String, Data>();

    public void adminPanel() {

        data = new Group();
        data.setName("Root");
        root = new DefaultMutableTreeNode(data);
        nodeInfo = root.getUserObject();

        JFrame adminPanelFrame = new JFrame("Mini Twitter");
        adminPanelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame popUpFrameTotalUser = new JFrame("Total User");
        popUpFrameTotalUser.setLocationRelativeTo(null);
        JFrame popUpFrameTotalGroup = new JFrame("Total Group");
        popUpFrameTotalGroup.setLocationRelativeTo(null);

        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        JScrollPane treeScrollPane = new JScrollPane(tree);

        JLabel labelTreeView = new JLabel("Tree View");
        JLabel labelPopupTotalUser = new JLabel("Total user(s): " + userData.size());
        JLabel labelPopupTotalGroup = new JLabel("Total group(s): " + groupData.size());

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

        popUpFrameTotalUser.setSize(300, 200);
        popUpFrameTotalUser.setVisible(false);
        popUpFrameTotalUser.setLayout(null);

        popUpFrameTotalGroup.setSize(300, 200);
        popUpFrameTotalGroup.setVisible(false);
        popUpFrameTotalGroup.setLayout(null);

        treeScrollPane.setBounds(25, 25, 400, 425);

        labelTreeView.setBounds(30, 5, 100, 20);
        labelPopupTotalUser.setBounds(100, 60, 250, 20);
        labelPopupTotalGroup.setBounds(100, 60, 250, 20);

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

        popUpFrameTotalUser.add(labelPopupTotalUser);
        popUpFrameTotalGroup.add(labelPopupTotalGroup);

        buttonAddUser.addActionListener(e -> {

            String userName = textFieldAddUser.getText().toString().toLowerCase();

            if (nodeInfo instanceof Group && userName.length() > 0 && !userData.containsKey(userName)) {
                data = new User();
                data.setName(userName);

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
            }

        });

        buttonAddGroup.addActionListener(e -> {

            String groupName = textFieldAddGroup.getText().toString().toUpperCase();

            if (nodeInfo instanceof Group && groupName.length() > 0 && !groupData.containsKey(groupName)) {
                data = new Group();
                data.setName(groupName);

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
            }

        });

        buttonShowUserTotal.addActionListener(e -> {
            labelPopupTotalUser.setText("Total user(s): " + userData.size());
            popUpFrameTotalUser.setVisible(true);
        });

        buttonShowGroupTotal.addActionListener(e -> {
            labelPopupTotalGroup.setText("Total group(s): " + groupData.size());
            popUpFrameTotalGroup.setVisible(true);
        });

        buttonOpenUser.addActionListener(e -> {
            userPanel.userPanel(true, nodeInfo);
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