package view;

import controller.FeedbackController;
import model.Feedback;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FeedbackGUI extends javax.swing.JPanel {
    
    private FeedbackController feedbackController;
    private DefaultTableModel tableModel;
    private final String CURRENT_USER = "StefanLandsberg";

    public FeedbackGUI() {
        feedbackController = new FeedbackController();
        initComponents();
        setupTable();
        setupRatingComboBox();
        loadFeedback();
        setupEventListeners();
    }

    private void setupTable() {
        tableModel = (DefaultTableModel) tblFeedback.getModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Student", "Rating", "Comments"});
        tblFeedback.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void setupRatingComboBox() {
        cbRating.removeAllItems();
        for (int i = 1; i <= 5; i++) {
            cbRating.addItem(String.valueOf(i));
        }
    }

    private void loadFeedback() {
        tableModel.setRowCount(0);
        
        try {
            List<Feedback> feedbackList = feedbackController.getAllFeedback();
            for (Feedback feedback : feedbackList) {
                tableModel.addRow(new Object[]{
                    feedback.getId(),
                    feedback.getStudent(),
                    feedback.getRating(),
                    feedback.getComment()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading feedback: " + e.getMessage(), 
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupEventListeners() {
        btnSubmitFeedback.addActionListener(e -> submitFeedback());
        
        // Add table selection listener to populate form fields
        tblFeedback.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                populateFormFromSelection();
            }
        });
    }

    private void submitFeedback() {
        if (!validateInputs()) {
            return;
        }

        try {
            Feedback feedback = new Feedback(
                CURRENT_USER,
                Integer.parseInt(cbRating.getSelectedItem().toString()),
                txtaComment.getText().trim()
            );

            if (feedbackController.addFeedback(feedback)) {
                JOptionPane.showMessageDialog(this, "Feedback submitted successfully!");
                clearInputs();
                loadFeedback();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to submit feedback", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error submitting feedback: " + e.getMessage(), 
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateFormFromSelection() {
        int selectedRow = tblFeedback.getSelectedRow();
        if (selectedRow >= 0) {
            String student = (String) tableModel.getValueAt(selectedRow, 1);
            if (student.equals(CURRENT_USER)) {
                int rating = (Integer) tableModel.getValueAt(selectedRow, 2);
                String comments = (String) tableModel.getValueAt(selectedRow, 3);
                cbRating.setSelectedItem(String.valueOf(rating));
                txtaComment.setText(comments);
            }
        }
    }

    private boolean validateInputs() {
        if (cbRating.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a rating");
            return false;
        }
        if (txtaComment.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a comment");
            return false;
        }
        return true;
    }

    private void clearInputs() {
        cbRating.setSelectedIndex(0);
        txtaComment.setText("");
        tblFeedback.clearSelection();
    }

    private void resetSubmitButton() {
        btnSubmitFeedback.setText("Submit Feedback");
        for (var listener : btnSubmitFeedback.getActionListeners()) {
            btnSubmitFeedback.removeActionListener(listener);
        }
        btnSubmitFeedback.addActionListener(e -> submitFeedback());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbRating = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaComment = new javax.swing.JTextArea();
        btnSubmitFeedback = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFeedback = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnEditFeedback = new javax.swing.JButton();
        btnDeleteFeedback = new javax.swing.JButton();

        jLabel1.setText("Rating (1-5)");

        cbRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Comment");

        txtaComment.setColumns(20);
        txtaComment.setRows(5);
        jScrollPane1.setViewportView(txtaComment);

        btnSubmitFeedback.setText("Submit Feedback");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSubmitFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSubmitFeedback)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblFeedback.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Rating", "Comment"
            }
        ));
        jScrollPane2.setViewportView(tblFeedback);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditFeedback.setText("Edit Feedback");

        btnDeleteFeedback.setText("Delete Feedback");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditFeedback)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteFeedback)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditFeedback)
                    .addComponent(btnDeleteFeedback))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(346, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteFeedback;
    private javax.swing.JButton btnEditFeedback;
    private javax.swing.JButton btnSubmitFeedback;
    private javax.swing.JComboBox<String> cbRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFeedback;
    private javax.swing.JTextArea txtaComment;
    // End of variables declaration//GEN-END:variables
}
