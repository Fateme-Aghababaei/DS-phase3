/*
 * Created by JFormDesigner on Fri Dec 17 21:21:49 IRST 2021
 */

package Main.GUI;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Fateme
 */
public class HistoryForm extends JFrame {
    ArrayList<String> historyExpressions;

    public HistoryForm(ArrayList<String> historyExpressions) {
        initComponents();
        this.historyExpressions = historyExpressions;
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);

        textArea.append("Recent Expressions:");
        for (String h : historyExpressions) {
            textArea.append("\n\n");
            textArea.append(h);
        }

        SwingUtilities.invokeLater(() -> {
            scrollPane1.getVerticalScrollBar().setValue(0);
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        textArea = new JTextArea();

        //======== this ========
        setTitle("History");
        setIconImage(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\Phase 3\\phase3-Fateme-Aghababaei\\icon.png").getImage());
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- textArea ----
            textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            textArea.setEditable(false);
            scrollPane1.setViewportView(textArea);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTextArea textArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
