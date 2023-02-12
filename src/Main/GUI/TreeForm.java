/*
 * Created by JFormDesigner on Fri Dec 17 17:31:50 IRST 2021
 */

package Main.GUI;

import Main.Tree;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.tree.*;
import Main.Tree.Node;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * @author Fateme
 */
public class TreeForm extends JFrame {
    public TreeForm(Tree tree) {
        initComponents();
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(tree.getRoot().getData().equals("!") ? "-" : tree.getRoot().getData());
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
        jtree.setModel(defaultTreeModel);

        Node expressionNode = (Node) tree.getRoot();
        if (expressionNode != null) {
            setJTreeData(expressionNode.getLeftChild(), root);
            setJTreeData(expressionNode.getRightChild(), root);
        }

        for (int i = 0; i < jtree.getRowCount(); i++) {
            jtree.expandRow(i);
        }
    }

    private void setJTreeData(Node root, TreeNode treeRoot) {
        if (root != null) {
            MutableTreeNode node = new DefaultMutableTreeNode(root.getData().equals("!") ? "-" : root.getData());
            setJTreeData(root.getLeftChild(), node);
            setJTreeData(root.getRightChild(), node);
            ((DefaultTreeModel) (jtree.getModel())).insertNodeInto(node, (MutableTreeNode) treeRoot, treeRoot.getChildCount());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        jtree = new JTree();
        label1 = new JLabel();

        //======== this ========
        setTitle("Tree");
        setIconImage(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\Phase 3\\phase3-Fateme-Aghababaei\\icon.png").getImage());
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- jtree ----
            jtree.setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode("(root)") {
                    {
                        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("a");
                            node1.add(new DefaultMutableTreeNode("b"));
                            node1.add(new DefaultMutableTreeNode("c"));
                        add(node1);
                        add(new DefaultMutableTreeNode("d"));
                    }
                }));
            jtree.setShowsRootHandles(true);
            jtree.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            scrollPane1.setViewportView(jtree);
        }

        //---- label1 ----
        label1.setText("Expression Tree");
        label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addComponent(label1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(33, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(17, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTree jtree;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
