/*
 * Created by JFormDesigner on Sun Nov 28 23:15:31 IRST 2021
 */

package Main.GUI;

import javax.swing.border.*;
import Main.Calculator;
import Main.Tree;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import Main.Tree.Node;

/**
 * @author Fateme
 */
public class MainForm extends JFrame {
    ArrayList<String> historyExpressions = new ArrayList<>();

    public MainForm() {
        initComponents();
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        this.btnTree.setEnabled(false);
        this.pack();
    }

    private void btn0ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "0");
    }

    private void btn1ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "1");
    }

    private void btn2ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "2");
    }

    private void btn3ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "3");
    }

    private void btn4ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "4");
    }

    private void btn5ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "5");
    }

    private void btn6ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "6");
    }

    private void btn7ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "7");
    }

    private void btn8ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "8");
    }

    private void btn9ActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "9");
    }

    private void btnDotActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + ".");
    }

    private void btnOpenParenthesisActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "(");
    }

    private void btnCloseParenthesisActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + ")");
    }

    private void btnPowerActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "^");
    }

    private void btnDivActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "/");
    }

    private void btnMulActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "*");
    }

    private void btnSubActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "-");
    }

    private void btnSumActionPerformed(ActionEvent e) {
        this.textField.setText(this.textField.getText() + "+");
    }

    private void btnClearActionPerformed(ActionEvent e) {
        textField.setText("");
        lblResult.setText(" Result");
        txtSteps.setText(" Steps");
        textField.requestFocus();
        btnTree.setEnabled(false);
    }

    private void btnEqualActionPerformed(ActionEvent e) {
        txtSteps.setText("");
        String expression = textField.getText();
        if (Calculator.Validate(expression)) {
            try {
                ArrayList<String> res = Calculator.CalculateExpressionWithSteps(expression);
                lblResult.setText(String.valueOf(res.get(res.size() - 1)));
                for (int i = 0; i < res.size(); i++) {
                    txtSteps.append((i + 1) + ": " + res.get(i) + "\n");
                }
                this.btnTree.setEnabled(true);
                historyExpressions.add(0, expression + " = " + res.get(res.size() - 1));
            } catch (ArithmeticException ex) {
                lblResult.setText(ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            lblResult.setText("Invalid Expression");
        }
    }

    private void textFieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnEqualActionPerformed(new ActionEvent(e.getSource(), e.getID(), ""));
    }

    private void btnTreeActionPerformed(ActionEvent e) {
        String expression = textField.getText();
        Node root = Calculator.InfixToTree("(" + expression + ")");
        Tree tree = new Tree(root);
        TreeForm treeForm = new TreeForm(tree);
        treeForm.setVisible(true);
    }

    private void btnHistoryActionPerformed(ActionEvent e) {
        HistoryForm historyForm = new HistoryForm(historyExpressions);
        historyForm.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        btnClear = new JButton();
        btnOpenParenthesis = new JButton();
        btnCloseParenthesis = new JButton();
        btnPower = new JButton();
        btn1 = new JButton();
        btn2 = new JButton();
        btn3 = new JButton();
        btnDiv = new JButton();
        btn4 = new JButton();
        btn5 = new JButton();
        btn6 = new JButton();
        btnMul = new JButton();
        btn7 = new JButton();
        btn8 = new JButton();
        btn9 = new JButton();
        btnSub = new JButton();
        btn0 = new JButton();
        btnDot = new JButton();
        btnEqual = new JButton();
        btnSum = new JButton();
        lblResult = new JLabel();
        textField = new JTextField();
        scrollPane1 = new JScrollPane();
        txtSteps = new JTextArea();
        panel2 = new JPanel();
        btnTree = new JButton();
        btnHistory = new JButton();

        //======== this ========
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\Phase 3\\phase3-Fateme-Aghababaei\\icon.png").getImage());
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(5, 4));

            //---- btnClear ----
            btnClear.setText("AC");
            btnClear.setForeground(Color.black);
            btnClear.setBackground(new Color(191, 63, 67));
            btnClear.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnClear.setBorder(LineBorder.createBlackLineBorder());
            btnClear.addActionListener(e -> btnClearActionPerformed(e));
            panel1.add(btnClear);

            //---- btnOpenParenthesis ----
            btnOpenParenthesis.setText("(");
            btnOpenParenthesis.setBackground(new Color(214, 157, 22));
            btnOpenParenthesis.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnOpenParenthesis.addActionListener(e -> btnOpenParenthesisActionPerformed(e));
            panel1.add(btnOpenParenthesis);

            //---- btnCloseParenthesis ----
            btnCloseParenthesis.setText(")");
            btnCloseParenthesis.setBackground(new Color(214, 157, 22));
            btnCloseParenthesis.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnCloseParenthesis.addActionListener(e -> btnCloseParenthesisActionPerformed(e));
            panel1.add(btnCloseParenthesis);

            //---- btnPower ----
            btnPower.setText("^");
            btnPower.setBackground(new Color(214, 157, 22));
            btnPower.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnPower.addActionListener(e -> btnPowerActionPerformed(e));
            panel1.add(btnPower);

            //---- btn1 ----
            btn1.setText("1");
            btn1.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn1.addActionListener(e -> btn1ActionPerformed(e));
            panel1.add(btn1);

            //---- btn2 ----
            btn2.setText("2");
            btn2.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn2.addActionListener(e -> btn2ActionPerformed(e));
            panel1.add(btn2);

            //---- btn3 ----
            btn3.setText("3");
            btn3.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn3.addActionListener(e -> btn3ActionPerformed(e));
            panel1.add(btn3);

            //---- btnDiv ----
            btnDiv.setText("/");
            btnDiv.setBackground(new Color(214, 157, 22));
            btnDiv.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnDiv.addActionListener(e -> btnDivActionPerformed(e));
            panel1.add(btnDiv);

            //---- btn4 ----
            btn4.setText("4");
            btn4.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn4.addActionListener(e -> btn4ActionPerformed(e));
            panel1.add(btn4);

            //---- btn5 ----
            btn5.setText("5");
            btn5.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn5.addActionListener(e -> btn5ActionPerformed(e));
            panel1.add(btn5);

            //---- btn6 ----
            btn6.setText("6");
            btn6.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn6.addActionListener(e -> btn6ActionPerformed(e));
            panel1.add(btn6);

            //---- btnMul ----
            btnMul.setText("x");
            btnMul.setBackground(new Color(214, 157, 22));
            btnMul.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnMul.addActionListener(e -> btnMulActionPerformed(e));
            panel1.add(btnMul);

            //---- btn7 ----
            btn7.setText("7");
            btn7.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn7.addActionListener(e -> btn7ActionPerformed(e));
            panel1.add(btn7);

            //---- btn8 ----
            btn8.setText("8");
            btn8.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn8.addActionListener(e -> btn8ActionPerformed(e));
            panel1.add(btn8);

            //---- btn9 ----
            btn9.setText("9");
            btn9.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn9.addActionListener(e -> btn9ActionPerformed(e));
            panel1.add(btn9);

            //---- btnSub ----
            btnSub.setText("_");
            btnSub.setBackground(new Color(214, 157, 22));
            btnSub.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnSub.addActionListener(e -> btnSubActionPerformed(e));
            panel1.add(btnSub);

            //---- btn0 ----
            btn0.setText("0");
            btn0.setFont(new Font("sansserif", Font.PLAIN, 20));
            btn0.addActionListener(e -> btn0ActionPerformed(e));
            panel1.add(btn0);

            //---- btnDot ----
            btnDot.setText(".");
            btnDot.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnDot.addActionListener(e -> btnDotActionPerformed(e));
            panel1.add(btnDot);

            //---- btnEqual ----
            btnEqual.setText("=");
            btnEqual.setBackground(new Color(107, 252, 106));
            btnEqual.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnEqual.addActionListener(e -> btnEqualActionPerformed(e));
            panel1.add(btnEqual);

            //---- btnSum ----
            btnSum.setText("+");
            btnSum.setBackground(new Color(214, 157, 22));
            btnSum.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnSum.addActionListener(e -> btnSumActionPerformed(e));
            panel1.add(btnSum);
        }

        //---- lblResult ----
        lblResult.setText("  Result");
        lblResult.setFont(new Font("sansserif", Font.PLAIN, 20));
        lblResult.setBorder(LineBorder.createBlackLineBorder());

        //---- textField ----
        textField.setFont(new Font("sansserif", Font.PLAIN, 20));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                textFieldKeyReleased(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- txtSteps ----
            txtSteps.setEnabled(false);
            txtSteps.setFont(new Font("sansserif", Font.PLAIN, 20));
            txtSteps.setText("  Steps");
            scrollPane1.setViewportView(txtSteps);
        }

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(1, 2));

            //---- btnTree ----
            btnTree.setText("Tree");
            btnTree.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnTree.setIcon(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\Phase 3\\phase3-Fateme-Aghababaei\\treeIcon.png"));
            btnTree.addActionListener(e -> btnTreeActionPerformed(e));
            panel2.add(btnTree);

            //---- btnHistory ----
            btnHistory.setText("History");
            btnHistory.setFont(new Font("sansserif", Font.PLAIN, 20));
            btnHistory.setIcon(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\Phase 3\\phase3-Fateme-Aghababaei\\historyIcon.jpg"));
            btnHistory.addActionListener(e -> btnHistoryActionPerformed(e));
            panel2.add(btnHistory);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblResult, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                        .addComponent(textField)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGap(32, 32, 32))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JButton btnClear;
    private JButton btnOpenParenthesis;
    private JButton btnCloseParenthesis;
    private JButton btnPower;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btnDiv;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btnMul;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnSub;
    private JButton btn0;
    private JButton btnDot;
    private JButton btnEqual;
    private JButton btnSum;
    private JLabel lblResult;
    private JTextField textField;
    private JScrollPane scrollPane1;
    private JTextArea txtSteps;
    private JPanel panel2;
    private JButton btnTree;
    private JButton btnHistory;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
