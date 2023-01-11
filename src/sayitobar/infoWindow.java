/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sayitobar;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;


public class infoWindow extends javax.swing.JFrame {

    /**
     * Creates new form infoWindow
     */
    public enum Messages {
        DETECTOR("<u>How to use Genome Detector - Gene Detector:</u>\n"
                + "· Select your patient's gene file (must be Excel .xlsx file)\n"
                + "· Select your gene pool (must be Excel .xlsx file)\n"
                + "· Select your output folder which comes pre-selected (must be a folder)\n"
                + "· Create a name for your new Excel file, which will be created automatically\n"
                + "All (four) input places must be filled in order to work.\n"
                + "As output, an Excel (.xlsx) file will be created in chosen output folder with gene occurrences of your gene pool in your patient's gene data.\n\n"
                + "<u>Rules of input Excel files:</u>\n"
                + "1. Excel file of patients gene data must have a column named \"Gene symbols\" at 1st row (can be changed in settings)\n"
                + "2. Excel file of gene pool must have a column named \"Gene\" at 1st row and others shouldn't have \"Gene\" in their names (can be changed in settings)\n"
                + "3. Excel file of gene pool shouldn't have a text (gene) that is spread across two rows/columns (and stuff like that), keep things simple...\n"
                + "4. Excel file of gene pool is allowed to have \"/\" or \",\" or \"=\" or \";\" between two gene names in one cell (!only those!). In that case both genes will be searched. (i.e. -> KIF23=MKLP1)\n"
                + "5. All files should be in Excel Workbook \"_.xlsx\" format (not .xls or not \"Strict Open XML Spreadsheet\" as it has the same suffix .xlsx)\n"
                + "6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 51, you accidently highlighted row 62. Code thinks there are gene data in row 62, therefore it crashes.)\n"
                + "\n\nGenomeDetector " + GenomeDetector.version + " by Sayitobar Inc.\n\nBarış Sayitoğlu\nContact: bsayitoglu@gmail.com\nGithub: https://github.com/Sayitobar/Genome-Detector"),
        VCF_COMPARATOR("<u>How to use Genome Detector - VCF Comparator:</u>\n"
                + "· Add at least 2 VCF (Variant Call Format) files by clicking \"Add file\" button (there is no number limit)\n"
                + "· Select your output folder which comes pre-selected (must be a folder)\n"
                + "· Create a name for your new Excel file, which will be created automatically\n\n"
                + "At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of VCF files.\n\n"
                + "<u>Rules of input VCF files:</u>\n"
                + "1. It is assumed that first rows are metadata for VCF file, which start with \"##\". If they don't exist, it is no problem.\n"
                + "2. Headers/categories row should have \"CHROM\" at it's first cell. Keyword can be changed in settings. (As the headers rows index is not known, we must find it by searching some keywords in each row)\n"
                + "3. Headers/categories row should have \"POS\" at the cell/column, where positions of mutations are displayed. Keyword can be changed in settings.\n"
                + "4. Numbers under the positions column may have text between numbers to make reading more easy (like 132.681 or 12,134).\n"
                + "5. It is the best that you don't alter/change/touch the fresh VCF file before processing it, to make sure a potential error is not your doing.\n"
                + "\n\nGenomeDetector " + GenomeDetector.version + " by Sayitobar Inc.\n\nBarış Sayitoğlu\nContact: bsayitoglu@gmail.com\nGithub: https://github.com/Sayitobar/Genome-Detector"),
        XLSX_COMPARATOR("FCHR: First cell of the categories row (headers row)\nACHR: Aimed cell of the categories row (headers row) - the column to be compared\n\n<u>How to use Genome Detector - XLSX Comparator:</u>\n"
                + "· Add at least 2 XLSX files by clicking \"Add file\" button (there is no number limit)\n"
                + "· Select your output folder which comes pre-selected (must be a folder)\n"
                + "· Create a name for your new Excel file, which will be created automatically\n\n"
                + "At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of XLSX files.\n\n"
                + "<u>Rules of input XLSX files:</u>\n"
                + "1. First rows of XLSX files may have metadata starting with \"##\". If they don't exist, it is no problem. (As these kinds of files mostly get converted from a VCF file to Excel, they tend to have \"##\" rows)\n"
                + "2. Headers/categories row should have \"CHROM\" at it's first cell. Keyword can be changed in settings (FCHR). (As the headers rows index is not known, we must find it by searching some keywords in each row)\n"
                + "3. Headers/categories row should have \"POS\" at the cell/column, where positions of mutations are displayed. Keyword can be changed in settings (ACHR).\n"
                + "4. Numbers under the positions column may have text between numbers to make reading more easy (like 132.681 or 12,134).\n"
                + "5. All files should be in Excel Workbook \"_.xlsx\" format (not .xls or not \"Strict Open XML Spreadsheet\" as it has the same suffix .xlsx)\n"
                + "6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 351, you accidently highlighted row 462. Code thinks there are more data in row 462, therefore it crashes.)\n"
                + "\n\nGenomeDetector " + GenomeDetector.version + " by Sayitobar Inc.\n\nBarış Sayitoğlu\nContact: bsayitoglu@gmail.com\nGithub: https://github.com/Sayitobar/Genome-Detector");
        
        Messages(String value) {this.message = value;}
        private final String message;
        public String getMsg() {return this.message;}
    }
    
    public infoWindow() {
        initComponents();
        
        textArea.setText("<html>" + msg.getMsg().replaceAll("\n", "<br/>") + "</html>");
        
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
                        .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(Heading)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Heading)
                .addGap(18, 18, 18)
                .addComponent(textArea, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        
        switch (msg) {
            case DETECTOR:
                DetectorMenu.wakeup();
                break;
            case VCF_COMPARATOR:
                VCF_ComparatorMenu.wakeup();
                break;
            case XLSX_COMPARATOR:
                XLSX_ComparatorMenu.wakeup();
                break;
            default:
                break;
        }

    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    private static Messages msg;
    public static void wakeup(infoWindow.Messages message) {
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
        java.awt.EventQueue.invokeLater(() -> {
            msg = message;
            new infoWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel textArea;
    // End of variables declaration//GEN-END:variables
}
