package view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author thian
 */
public class DashboardGUI extends javax.swing.JFrame {
    
    private AppointmentGUI appointmentGUI;
    private CounselorGUI counselorGUI;
    private FeedbackGUI feedbackGUI;

    public DashboardGUI() throws ClassNotFoundException {
        initComponents();
        setupMainPanel();
        setupEventListeners();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Wellness Management System");
    }

    private void setupMainPanel() throws ClassNotFoundException {
        mainPanel.setLayout(new CardLayout());
        
        // Initialize GUI panels
        appointmentGUI = new AppointmentGUI();
        counselorGUI = new CounselorGUI();
        feedbackGUI = new FeedbackGUI();
        
        // Add panels to main panel
        mainPanel.add(appointmentGUI, "appointments");
        mainPanel.add(counselorGUI, "counselors");
        mainPanel.add(feedbackGUI, "feedback");
        
        // Show appointments panel by default
        showAppointments();
    }

    private void setupEventListeners() {
        // Remove existing action listeners and add our own
        btnAppointments.removeActionListener(btnAppointments.getActionListeners()[0]);
        btnCounselors.removeActionListener(btnCounselors.getActionListeners()[0]);
        btnFeedback.removeActionListener(btnFeedback.getActionListeners()[0]);
        btnExit.removeActionListener(btnExit.getActionListeners()[0]);
        
        // Add our custom action listeners
        btnAppointments.addActionListener(e -> showAppointments());
        btnCounselors.addActionListener(e -> showCounselors());
        btnFeedback.addActionListener(e -> showFeedback());
        btnExit.addActionListener(e -> exitApplication());
    }

    private void showAppointments() {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "appointments");
        
        // Update button states
        btnAppointments.setEnabled(false);
        btnCounselors.setEnabled(true);
        btnFeedback.setEnabled(true);
    }

    private void showCounselors() {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "counselors");
        
        // Update button states
        btnAppointments.setEnabled(true);
        btnCounselors.setEnabled(false);
        btnFeedback.setEnabled(true);
    }

    private void showFeedback() {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "feedback");
        
        // Update button states
        btnAppointments.setEnabled(true);
        btnCounselors.setEnabled(true);
        btnFeedback.setEnabled(false);
    }

    private void exitApplication() {
        int result = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to exit the application?",
            "Confirm Exit", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Helper method for the generated action listeners
    private void showCard(String cardName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, cardName);
        
        // Update button states based on the card shown
        btnAppointments.setEnabled(!cardName.equals("appointments"));
        btnCounselors.setEnabled(!cardName.equals("counselors"));
        btnFeedback.setEnabled(!cardName.equals("feedback"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        navPanel = new javax.swing.JPanel();
        btnAppointments = new javax.swing.JButton();
        btnCounselors = new javax.swing.JButton();
        btnFeedback = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.CardLayout());

        btnAppointments.setText("Appointments");
        btnAppointments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppointmentsActionPerformed(evt);
            }
        });

        btnCounselors.setText("Counselors");
        btnCounselors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCounselorsActionPerformed(evt);
            }
        });

        btnFeedback.setText("Feedback");
        btnFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbackActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navPanelLayout = new javax.swing.GroupLayout(navPanel);
        navPanel.setLayout(navPanelLayout);
        navPanelLayout.setHorizontalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCounselors, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(btnFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        navPanelLayout.setVerticalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAppointments)
                    .addComponent(btnCounselors)
                    .addComponent(btnFeedback)
                    .addComponent(btnExit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAppointmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppointmentsActionPerformed
        // TODO add your handling code here:
        showCard("appointments");
    }//GEN-LAST:event_btnAppointmentsActionPerformed

    private void btnCounselorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCounselorsActionPerformed
        // TODO add your handling code here:
        showCard("counselors");
    }//GEN-LAST:event_btnCounselorsActionPerformed

    private void btnFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbackActionPerformed
        // TODO add your handling code here:
        showCard("feedback");
    }//GEN-LAST:event_btnFeedbackActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try 
                {
                    new DashboardGUI().setVisible(true);
                } 
                catch (ClassNotFoundException ex) 
                {
                    System.getLogger(DashboardGUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAppointments;
    private javax.swing.JButton btnCounselors;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFeedback;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navPanel;
    // End of variables declaration//GEN-END:variables
}
