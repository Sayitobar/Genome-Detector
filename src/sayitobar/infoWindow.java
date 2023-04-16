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
                + "· Select first file, for example your gene pool (must be Excel .xlsx file)\n"
                + "· Select second file, for example your patient's genes (must be Excel .xlsx file)\n"
                + "· Write the \"Column name\" of gene symbols for both of your files. (i.e. If \"Genes\" is the header of your genes column, write that in.)\n"
                + "· If desired, rename, change the \"Output folder\" or un/mark \"Split cell text\" option.\n\n"
                + "All (four) input places must be filled in order to work.\n"
                + "As output, an Excel (.xlsx) file will be created in chosen output folder with gene occurrences of your gene pool (1st file) in your patient's gene data (2nd file).\n\n"
                + "<u>Rules of input Excel files:</u>\n"
                + "1. Excel file of gene pool shouldn't have a text (gene) that is spread across two rows/columns (and stuff like that), keep things simple.\n"
                + "2. All files should be in Excel Workbook \"_.xlsx\" format (not .xls or not \"Strict Open XML Spreadsheet\" as it has the same suffix .xlsx)\n"
                + "3. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 51, you accidently highlighted row 62. Algorithm thinks there are gene data in row 62, therefore it crashes.)\n"
                + "4. Excel file of gene pool (1st file) is allowed to have \"\\\" or \",\" or \"=\" or \";\" or your custom splitters between two gene names in one cell and more if you add your custom splitter. In that case both genes will be searched.\n"
                + "\n\nGenomeDetector " + GenomeDetector.VERSION + " by Sayitobar Inc.\n\nBarış Sayitoğlu\nContact: bsayitoglu@gmail.com\nGithub: https://github.com/Sayitobar/Genome-Detector"),
        VCF_COMPARATOR("<u>How to use Genome Detector - VCF Comparator:</u>\n"
                + "· Add at least 2 VCF (Variant Call Format) files by clicking \"Add file\" button (there is no file number limit)\n"
                + "· Change \"FCHR\" & \"ACHR\" of every file!\n"
                + "--- FCHR: First Cell of the Categories Row. Find your headers row, (usually the first row) write the first cell's value here\n"
                + "--- ACHR: Aimed Cell of the Categories Row. Find your headers row, (usually the first row) find the column you want to compare, write the header of that column here\n"
                + "· If desired, un/mark \"Output the differences\". (Check this if you need discordant comparison)\n\n"
                + "At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of VCF files. If \"Output the differences\" checked, each spreadsheet will have it's own differences.\n\n"
                + "<u>Rules of input VCF files:</u>\n"
                + "1. Input file name mustn't contain illegal characters like . : or '\n"
                + "2. You should write FCHR & ACHR names correctly, these are *case-sensitive*. Otherwise, program will not work.\n"
                + "3. It is assumed that first rows are metadata for VCF file, which start with \"##\". If they don't exist, it is no problem.\n"
                + "4. Numbers under the positions column may have String values to make reading more easy (like 132.681). In that case, cell will be read as an integer value.\n"
                + "5. It is the best that you don't alter/change/touch the fresh VCF file before processing it, to make sure a potential error is not your doing.\n"
                + "\n\nGenomeDetector " + GenomeDetector.VERSION + " by Sayitobar Inc.\n\nBarış Sayitoğlu\nContact: bsayitoglu@gmail.com\nGithub: https://github.com/Sayitobar/Genome-Detector"),
        XLSX_COMPARATOR("<u>How to use Genome Detector - XLSX Comparator:</u>\n"
                + "· Add at least 2 XLSX Excel files by clicking \"Add file\" button (there is no file number limit)\n"
                + "· Change \"FCHR\" & \"ACHR\" of every file!\n"
                + "--- FCHR: First Cell of the Categories Row. Find your headers row, (usually the first row) write the first cell's value here\n"
                + "--- ACHR: Aimed Cell of the Categories Row. Find your headers row, (usually the first row) find the column you want to compare, write the header of that column here\n"
                + "· If desired, un/mark \"Output the differences\". (Check this if you need discordant comparison)\n\n"
                + "At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of VCF files. If \"Output the differences\" checked, each spreadsheet will have it's own differences.\n\n"
                + "<u>Rules of input XLSX files:</u>\n"
                + "1. Input file name mustn't contain illegal characters like . : or '\n"
                + "2. You should write FCHR & ACHR names correctly, these are *case-sensitive*. Otherwise, program will not work.\n"
                + "3. All files should be in Excel Workbook \".xlsx\" format (not .xls or not \"Strict Open XML Spreadsheet\" as it has the same suffix .xlsx)\n"
                + "4. First rows of XLSX files may have metadata starting with \"##\". If they don't exist, it's perfect. (As these kinds of files mostly get converted from a VCF file to Excel, they tend to have \"##\" rows)\n"
                + "5. Numbers under the positions column may have String values to make reading more easy (like 132.681). In that case, cell will be read as an integer value.\n"
                + "6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 351, you accidently highlighted row 462. Algorithm thinks there are more data in row 462, therefore it crashes.\n"
                + "\n\nGenomeDetector " + GenomeDetector.VERSION + " by Sayitobar Inc.\n\nBarış Sayitoğlu\nContact: bsayitoglu@gmail.com\nGithub: https://github.com/Sayitobar/Genome-Detector");
        
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
