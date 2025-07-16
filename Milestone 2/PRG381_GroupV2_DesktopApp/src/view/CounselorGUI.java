package view;

import controller.CounselorController;
import model.Counselor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CounselorGUI extends javax.swing.JPanel {

    private CounselorController counselorController;
    private DefaultTableModel tableModel;

    public CounselorGUI() {
        counselorController = new CounselorController();
        initComponents();
        setupTable();
        loadCounselors();
        setupEventListeners();

        // Set placeholder text and color
        clearInputs();

        // Add focus listeners for placeholders
        txtCounselorName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtCounselorName.getText().equals("Enter counselor name")) {
                    txtCounselorName.setText("");
                    txtCounselorName.setForeground(java.awt.Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtCounselorName.getText().isEmpty()) {
                    txtCounselorName.setText("Enter counselor name");
                    txtCounselorName.setForeground(java.awt.Color.GRAY);
                }
            }
        });

        txtSpecialization.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtSpecialization.getText().equals("Enter specialization")) {
                    txtSpecialization.setText("");
                    txtSpecialization.setForeground(java.awt.Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtSpecialization.getText().isEmpty()) {
                    txtSpecialization.setText("Enter specialization");
                    txtSpecialization.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }

    private void setupTable() {
        tableModel = (DefaultTableModel) tblCouncelor.getModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Specialization", "Availability"});
        tblCouncelor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void loadCounselors() {
        tableModel.setRowCount(0);

        try {
            List<Counselor> counselors = counselorController.getAllCounselors();
            for (Counselor counselor : counselors) {
                tableModel.addRow(new Object[]{
                        counselor.getId(),
                        counselor.getName(),
                        counselor.getSpecialization(),
                        counselor.isAvailable() ? "Available" : "Not Available"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading counselors: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupEventListeners() {
        btnAddCouncelor.addActionListener(e -> addCounselor());

        // Add table selection listener to populate form fields
        tblCouncelor.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                populateFormFromSelection();
            }
        });
    }

    private void addCounselor() {
        if (!validateInputs()) {
            return;
        }

        try {
            Counselor counselor = new Counselor(
                    txtCounselorName.getText().trim(),
                    txtSpecialization.getText().trim(),
                    cbAvailablility.isSelected()
            );

            if (counselorController.addCounselor(counselor)) {
                JOptionPane.showMessageDialog(this, "Counselor added successfully!");
                clearInputs();
                loadCounselors();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add counselor",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding counselor: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateFormFromSelection() {
        int selectedRow = tblCouncelor.getSelectedRow();
        if (selectedRow >= 0) {
            txtCounselorName.setForeground(java.awt.Color.BLACK);
            txtSpecialization.setForeground(java.awt.Color.BLACK);

            txtCounselorName.setText((String) tableModel.getValueAt(selectedRow, 1));
            txtSpecialization.setText((String) tableModel.getValueAt(selectedRow, 2));
            String availability = (String) tableModel.getValueAt(selectedRow, 3);
            cbAvailablility.setSelected(availability.equals("Available"));
        }
    }

    private boolean validateInputs() {
        String name = txtCounselorName.getText().trim();
        String spec = txtSpecialization.getText().trim();
        if (name.isEmpty() || name.equals("Enter counselor name")) {
            JOptionPane.showMessageDialog(this, "Please enter counselor name");
            return false;
        }
        if (spec.isEmpty() || spec.equals("Enter specialization")) {
            JOptionPane.showMessageDialog(this, "Please enter specialization");
            return false;
        }
        return true;
    }

    private void clearInputs() {
        txtCounselorName.setText("Enter counselor name");
        txtCounselorName.setForeground(java.awt.Color.GRAY);

        txtSpecialization.setText("Enter specialization");
        txtSpecialization.setForeground(java.awt.Color.GRAY);

        cbAvailablility.setSelected(false);
        tblCouncelor.clearSelection();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCounselorName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSpecialization = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbAvailablility = new javax.swing.JCheckBox();
        btnAddCouncelor = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCouncelor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnUpdateCouncelor = new javax.swing.JButton();
        btnDeleteCouncelor = new javax.swing.JButton();

        jLabel1.setText("Counselor Name");

        txtCounselorName.setText("jTextField1");

        jLabel2.setText("Specialization");

        txtSpecialization.setText("jTextField2");

        jLabel3.setText("Availability");

        btnAddCouncelor.setText("Add Counselor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAddCouncelor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbAvailablility)
                            .addComponent(txtSpecialization, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCounselorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCounselorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSpecialization, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbAvailablility))
                .addGap(18, 18, 18)
                .addComponent(btnAddCouncelor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblCouncelor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Counselor Name", "Specialization", "Availability"
            }
        ));
        jScrollPane1.setViewportView(tblCouncelor);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnUpdateCouncelor.setText("Update Counselor");

        btnDeleteCouncelor.setText("Delete Counselor");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdateCouncelor)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteCouncelor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateCouncelor)
                    .addComponent(btnDeleteCouncelor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(362, Short.MAX_VALUE))
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
                .addContainerGap(262, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCouncelor;
    private javax.swing.JButton btnDeleteCouncelor;
    private javax.swing.JButton btnUpdateCouncelor;
    private javax.swing.JCheckBox cbAvailablility;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCouncelor;
    private javax.swing.JTextField txtCounselorName;
    private javax.swing.JTextField txtSpecialization;
    // End of variables declaration//GEN-END:variables
}
