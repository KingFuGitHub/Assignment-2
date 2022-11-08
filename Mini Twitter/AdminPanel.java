import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class AdminPanel extends JFrame {

    private Data data;
    private DefaultMutableTreeNode root = null;
    Object nodeInfo;
    // private NodeInfo nodeInfo;

    public void GUI() {
        // DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Node("Root"));
        // Data userData = new User();
        // Data groupData = new Group();

        // root = new DefaultMutableTreeNode("Root");
        data = new Group();
        data.setName("Root");
        root = new DefaultMutableTreeNode(data);


        JFrame frame = new JFrame();
        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        JLabel labelTreeView = new JLabel("Tree View");

        JButton buttonAddUser = new JButton("Add User");
        JTextField textFieldAddUser = new JTextField();

        JButton buttonAddGroup = new JButton("Add group");
        JTextField textFieldAddGroup = new JTextField();

        JButton buttonOpenUser = new JButton("Open user");

        JButton buttonShowUserTotal = new JButton("Show user total");
        JButton buttonShowGroupTotal = new JButton("Show group total");
        JButton buttonShowMessageTotal = new JButton("Show message total");
        JButton buttonShowPositivePercentage = new JButton("Show positive percentage");

        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setLayout(null);

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

        // treeView.setViewportView(tree);

        frame.add(labelTreeView);
        // frame.add(tree);
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

        buttonAddUser.addActionListener(e -> {
            // Object nodeInfo = root.getUserObject();
            // nodeInfo = root.getUserObject();
            // if (nodeInfo instanceof Group || nodeInfo instanceof String) {

            // if (nodeInfo instanceof Group) {

                String userName = textFieldAddUser.getText().toString().toLowerCase();
                // Data userData = new User();

                data = new User();
                data.setName(userName);

                // DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(new
                // Node(userName));

                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(data);

                // root.add(userNode);
                root.add(userNode);

                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
            // }
        });

        buttonAddGroup.addActionListener(e -> {
            // if (!(nodeInfo instanceof User) || nodeInfo instanceof Group) {
                String groupName = textFieldAddGroup.getText().toString().toUpperCase();
                // Data groupData = new Group();
                data = new Group();
                data.setName(groupName);
                // DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(new
                // Node(groupName));
                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(data);
                System.out.println(groupNode);
                // groupNode.add(groupNode);

                root.add(groupNode);
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                model.reload(root);
            // }
        });

        tree.addTreeSelectionListener(e -> {

            // DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            // tree.getLastSelectedPathComponent();
            // if (root == null) {
            // DefaultMutableTreeNode node = tree.getLastSelectedPathComponent();
            // root = tree.getLastSelectedPathComponent();
            
            root = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            // System.out.println(root);
            // }
            // String temp = textFieldAddUser.getText().toString();
            // if (this.root != null) {
            if(root == null){
                return;
            }
            
            nodeInfo = root.getUserObject();

            System.out.println(nodeInfo.getClass().getSimpleName());

            // DefaultMutableTreeNode test = new DefaultMutableTreeNode(new Node(temp));

            // node.add(test);
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            model.reload(root);
            // }

        });

    }
}