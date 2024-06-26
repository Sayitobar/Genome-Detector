/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sayitobar;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;


public class devConsole extends javax.swing.JFrame {

    /**
     * Creates new form devConsole
     */
    public devConsole() {
        initComponents();
        
        // textArea.setText("babbaboi");
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
        
        // Window location
        setLocation(GenomeDetector.WinX, GenomeDetector.WinY);
        
        
        this.addWindowListener(new WindowAdapter() {  // do these when devConsole gets closed
            public void windowClosing(WindowEvent e) {
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));  // resets console output place (this avoids errors)
                System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            }
        });
    }
    
    public class CustomOutputStream extends OutputStream {
        private javax.swing.JTextArea txt;

        public CustomOutputStream(javax.swing.JTextArea textArea) {
            this.txt = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            txt.append(String.valueOf((char) b));
            
            if ((char) b == '\n') {
                // scrolls the text area to the end of data
                txt.setCaretPosition(txt.getDocument().getLength());
                // keeps the textArea up to date
                txt.update(txt.getGraphics());
            }    
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        clearButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Heading.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        Heading.setText("Dev Console");

        textArea.setEditable(false);
        textArea.setBackground(new java.awt.Color(20, 20, 20));
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        textArea.setForeground(new java.awt.Color(255, 255, 255));
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        clearButton.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        printButton.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Heading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Heading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // Clear the console (reduces lag and boosts performance)
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
        
        textArea.setText("");
        System.out.println("devConsole cleared!");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        String text  = textArea.getText();
        String fpath = System.getProperty("user.home") + "/Desktop/ConsoleOUT.txt";
        
        text += "\n\n\nError issued in:\t" + new java.text.SimpleDateFormat("yyyy-MM-dd_hh-mm").format(new java.util.Date());
        
        try (PrintWriter out = new PrintWriter(fpath)) {
            out.println(text);
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e);
        }
        
        
        System.out.println("\nConsole Output saved in: " + fpath);
    }//GEN-LAST:event_printButtonActionPerformed

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
            java.util.logging.Logger.getLogger(devConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new devConsole().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JButton clearButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printButton;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
