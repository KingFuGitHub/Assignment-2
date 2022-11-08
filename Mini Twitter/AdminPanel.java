import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class AdminPanel extends JFrame {

    // private JTree tree = null;


    public void GUI() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        JFrame frame = new JFrame();
        JTree tree = new JTree(root);
        JLabel labelTreeView = new JLabel("Tree View");

        JButton buttonAddUser = new JButton("Add User");
        JTextField textFieldAddUser = new JTextField("user id");

        JButton buttonAddGroup = new JButton("Add group");
        JTextField textFieldAddGroup = new JTextField("group id");

        JButton buttonOpenUser = new JButton("Open user");

        JButton buttonShowUserTotal = new JButton("Show user total");
        JButton buttonShowGroupTotal = new JButton("Show group total");
        JButton buttonShowMessageTotal = new JButton("Show message total");
        JButton buttonShowPositivePercentage = new JButton("Show positive percentage");


        frame.setSize(800,500);
        frame.setVisible(true);
        frame.setLayout(null);

        tree.setBounds(25, 25, 400, 425);
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
        
        
        frame.add(labelTreeView);
        frame.add(tree);
        frame.add(buttonAddUser);
        frame.add(textFieldAddUser);
        frame.add(buttonAddGroup);
        frame.add(textFieldAddGroup);
        frame.add(buttonOpenUser);
        frame.add(buttonShowUserTotal);
        frame.add(buttonShowGroupTotal);
        frame.add(buttonShowMessageTotal);
        frame.add(buttonShowPositivePercentage);



        buttonAddUser.addActionListener(e ->{
            String temp = textFieldAddUser.getText().toString();
            DefaultMutableTreeNode test = new DefaultMutableTreeNode(temp);

            root.add(test);
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            model.reload(root);
        });

        tree.addTreeSelectionListener(e->{

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(node == null){
                return;
            }

            Object nodeInfo = node.getUserObject();

        });
        


    }
} 