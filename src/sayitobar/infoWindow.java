/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sayitobar;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;

/**
 *
 * @author mac
 */
public class infoWindow extends javax.swing.JFrame {

    /**
     * Creates new form infoWindow
     */
    public infoWindow() {
        initComponents();
        
        String message = "In order to use GenomeDetector properly:\n"
                + "· Select your patient's gene file (must be Excel .xlsx file)\n"
                + "· Select your gene pool (must be Excel .xlsx file)\n"
                + "· Select your output folder (must be a folder) (comes pre-selected)\n"
                + "· Select your desired name for your new excel file (creates itself)\n\n"
                + "All (four) input places must be filled in order to work.\n"
                + "At last an Excel (.xlsx) file will be created in your selected output folder with gene occurrences of your gene pool in your patient's gene data.\n\n"
                + "Rules of input excel files:\n"
                + "1. Excel file of patients gene data must have a column named \"Gene symbols\" at 1st row\n"
                + "2. Excel file of desired gene pool must have a column named \"Gene\" at 1st row and others shouldn't have \"Gene\" in their names\n"
                + "3. Excel file of desired gene pool shouldn't have a text (gene) that is spread across two rows (and stuff like that), keep things simple...\n"
                + "4. Excel file of desired gene pool is allowed to have \"/\" or \",\" or \"=\" or \";\" between two genes in one cell (!only those!). In that case both genes will be searched. (i.e. -> KIF23=MKLP1)\n"
                + "5. Excel files should be in \"_.xlsx\" format (not .xls)\n"
                + "\nGenomeDetector " + GenomeDetector.version + " by Sayitobar Inc.\n\nBarış Sayitoğlu\nContact: bsayitoglu@gmail.com";
        
        textArea.setText("<html>" + message.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        
        // Window location
        setLocation(GenomeDetector.WinX - getWidth()/2, GenomeDetector.WinY - getHeight()/2);
    }
    
    public void close() {
        WindowEvent closeWin = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWin);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Heading = new javax.swing.JLabel();
        textArea = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        Heading.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        Heading.setText("Infos & Rules");

        textArea.setFont(new java.awt.Font("Hiragino Sans", 0, 13)); // NOI18N
        textArea.setText(".");
        textArea.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(Heading)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Heading)
                .addGap(18, 18, 18)
                .addComponent(textArea, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        System.out.println("Back to main menu window");
        
        GenomeDetector.WinX = getX() + getWidth() /2;
        GenomeDetector.WinY = getY() + getHeight()/2;
        
        close();
        DetectorMenu.wakeup();
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void wakeup() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infoWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new infoWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel textArea;
    // End of variables declaration//GEN-END:variables
}