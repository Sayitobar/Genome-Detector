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
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        
        // Open window to center of screen
        setLocationRelativeTo(null);
        
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

        Title = new javax.swing.JLabel();
        detectorButton = new javax.swing.JButton();
        vcfComparisonButton = new javax.swing.JButton();
        vcfcompExpLabel = new javax.swing.JLabel();
        detectorExplLabel = new javax.swing.JLabel("<html>Search for some genes in patient's gene list</html>", javax.swing.SwingConstants.RIGHT);
        jSeparator1 = new javax.swing.JSeparator();
        xlsxComparisonButton = new javax.swing.JButton();
        xlsxcompExpLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        Title.setFont(new java.awt.Font("Futura", 0, 28)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Genome Detector");

        detectorButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        detectorButton.setText("Gene Detector");
        detectorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detectorButtonActionPerformed(evt);
            }
        });

        vcfComparisonButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        vcfComparisonButton.setText("VCF Comparator");
        vcfComparisonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vcfComparisonButtonActionPerformed(evt);
            }
        });

        vcfcompExpLabel.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        vcfcompExpLabel.setText("<html>Compare multiple VCF files and get common cells in selected column (great for: Multisample comparison)</html>");

        detectorExplLabel.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        detectorExplLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        detectorExplLabel.setText("<html>Search for some genes in patient's gene list</html>");
        detectorExplLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        xlsxComparisonButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        xlsxComparisonButton.setText("XLSX Comparator");
        xlsxComparisonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xlsxComparisonButtonActionPerformed(evt);
            }
        });

        xlsxcompExpLabel.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        xlsxcompExpLabel.setText("<html>Compare multiple XLSX files and get common cells in selected column (great for: Multisample comparison)</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(113, 113, 113))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vcfComparisonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(xlsxComparisonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detectorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xlsxcompExpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vcfcompExpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detectorExplLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(detectorButton)
                        .addGap(29, 29, 29)
                        .addComponent(vcfComparisonButton)
                        .addGap(29, 29, 29)
                        .addComponent(xlsxComparisonButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(detectorExplLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vcfcompExpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xlsxcompExpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void detectorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detectorButtonActionPerformed
        System.out.println("Accessing genome detector");
        
        GenomeDetector.WinX = getX() + getWidth() /2;
        GenomeDetector.WinY = getY() + getHeight()/2;
        
        close();
        DetectorMenu.wakeup();
    }//GEN-LAST:event_detectorButtonActionPerformed

    private void vcfComparisonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vcfComparisonButtonActionPerformed
        System.out.println("Accessing VCF genome comparator");
        
        GenomeDetector.WinX = getX() + getWidth() /2;
        GenomeDetector.WinY = getY() + getHeight()/2;
        
        close();
        VCF_ComparatorMenu.wakeup();
    }//GEN-LAST:event_vcfComparisonButtonActionPerformed

    private void xlsxComparisonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xlsxComparisonButtonActionPerformed
        System.out.println("Accessing XLSX genome comparator");
        
        GenomeDetector.WinX = getX() + getWidth() /2;
        GenomeDetector.WinY = getY() + getHeight()/2;
        
        close();
        XLSX_ComparatorMenu.wakeup();
    }//GEN-LAST:event_xlsxComparisonButtonActionPerformed

    public static void wakeup() {
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
        java.awt.EventQueue.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton detectorButton;
    private javax.swing.JLabel detectorExplLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton vcfComparisonButton;
    private javax.swing.JLabel vcfcompExpLabel;
    private javax.swing.JButton xlsxComparisonButton;
    private javax.swing.JLabel xlsxcompExpLabel;
    // End of variables declaration//GEN-END:variables
}
