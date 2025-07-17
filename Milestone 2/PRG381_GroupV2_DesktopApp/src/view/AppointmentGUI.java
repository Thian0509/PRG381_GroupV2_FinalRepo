package view;

import controller.AppointmentController;
import controller.CounselorController;
import model.Appointment;
import model.Counselor;
import model.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import model.User;

public class AppointmentGUI extends javax.swing.JPanel {
    
    private AppointmentController appointmentController;
    private CounselorController counselorController;
    private DefaultTableModel tableModel;

    public AppointmentGUI() throws ClassNotFoundException 
    {
        appointmentController = new AppointmentController();
        counselorController = new CounselorController();
        initComponents();
        setupTable();
        setupDefaultValues();
        loadStudentsFromBCDatabase();
        loadCounselors();
        loadAppointments();
        setupEventListeners();
    }

    private void setupTable() {
        tableModel = (DefaultTableModel) tblAppointment.getModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Student", "Counselor", "Date", "Time", "Status"});
        tblAppointment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void setupDefaultValues() {
        // Clear default text fields and set format examples
        txtDate.setText("YYYY-MM-DD");
        txtTime.setText("HH:MM");
        
        // Set placeholder text color (gray)
        txtDate.setForeground(java.awt.Color.GRAY);
        txtTime.setForeground(java.awt.Color.GRAY);
        
        // Add focus listeners to clear placeholder text
        txtDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtDate.getText().equals("YYYY-MM-DD")) {
                    txtDate.setText("");
                    txtDate.setForeground(java.awt.Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtDate.getText().isEmpty()) {
                    txtDate.setText("YYYY-MM-DD");
                    txtDate.setForeground(java.awt.Color.GRAY);
                }
            }
        });
        
        txtTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtTime.getText().equals("HH:MM")) {
                    txtTime.setText("");
                    txtTime.setForeground(java.awt.Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtTime.getText().isEmpty()) {
                    txtTime.setText("HH:MM");
                    txtTime.setForeground(java.awt.Color.GRAY);
                }
            }
        });
        
        // Set detailed tooltips
        txtDate.setToolTipText("Enter date in YYYY-MM-DD format (e.g., " + LocalDate.now().toString() + ")");
        txtTime.setToolTipText("Enter time in HH:MM format (e.g., " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + ")");
    }

    private void loadStudentsFromBCDatabase() throws ClassNotFoundException {
        cbStudent.removeAllItems();
        cbStudent.addItem("Select Student");
        
        DBConnection dBConnection = new DBConnection();
        List<User> students = dBConnection.getStudentObjectsFromWellnessDB();
        
        if (students.isEmpty())
        {
            // Optionally show a message instead of adding fake data:
            JOptionPane.showMessageDialog(this, "No students found in database.", "Warning", JOptionPane.WARNING_MESSAGE);
        } 
        else 
        {
            for (User s : students) 
            {
                cbStudent.addItem(s.getName() + " " + s.getSurname());
            }
        }
    }

    private void loadCounselors() {
        cbCouncelor.removeAllItems();
        cbCouncelor.addItem("Select Counselor");
        
        try {
            List<Counselor> counselors = counselorController.getAllCounselors();
            for (Counselor counselor : counselors) {
                if (counselor.isAvailable()) {
                    cbCouncelor.addItem(counselor.getName());
                }
            }
            
            // If no counselors exist, add some sample ones
            if (counselors.isEmpty()) {
                System.out.println("No counselors found, adding sample counselors...");
                addSampleCounselors();
                // Reload after adding samples
                counselors = counselorController.getAllCounselors();
                for (Counselor counselor : counselors) {
                    if (counselor.isAvailable()) {
                        cbCouncelor.addItem(counselor.getName());
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading counselors: " + e.getMessage(), 
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSampleCounselors() {
        try {
            // Add sample counselors
            Counselor[] sampleCounselors = {
                new Counselor("Dr. Sarah Johnson", "Academic Counseling", true),
                new Counselor("Dr. Michael Chen", "Career Guidance", true),
                new Counselor("Dr. Emily Rodriguez", "Mental Health", true),
                new Counselor("Dr. David Wilson", "Life Skills", true),
                new Counselor("Dr. Lisa Anderson", "Study Skills", false)
            };
            
            for (Counselor counselor : sampleCounselors) {
                counselorController.addCounselor(counselor);
            }
            System.out.println("✓ Sample counselors added successfully");
        } catch (Exception e) {
            System.out.println("Error adding sample counselors: " + e.getMessage());
        }
    }

    private void loadAppointments() {
        tableModel.setRowCount(0);
        
        try {
            List<Appointment> appointments = appointmentController.getAllAppointments();
            
            // If no appointments exist, add some sample ones
            if (appointments.isEmpty()) {
                System.out.println("No appointments found, adding sample appointments...");
                // Reload after adding samples
                appointments = appointmentController.getAllAppointments();
            }
            
            for (Appointment appointment : appointments) {
                tableModel.addRow(new Object[]{
                    appointment.getId(),
                    appointment.getStudent(),
                    appointment.getCounselor(),
                    appointment.getDate(),
                    appointment.getTime(),
                    appointment.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading appointments: " + e.getMessage(), 
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupEventListeners() {
    btnBookAppointment.addActionListener(e -> bookAppointment());

    tblAppointment.getSelectionModel().addListSelectionListener(e -> {
        int row = tblAppointment.getSelectedRow();
        if (row >= 0) {
            txtDate.setText(tblAppointment.getValueAt(row, 3).toString());      // date
            txtTime.setText(tblAppointment.getValueAt(row, 4).toString());      // time
            cbStudent.setSelectedItem(tblAppointment.getValueAt(row, 1).toString()); // student
            cbCouncelor.setSelectedItem(tblAppointment.getValueAt(row, 2).toString()); // counselor
        }
    });
}

    private void bookAppointment() {
        if (!validateInputs()) {
            return;
        }

        try {
            String dateText = txtDate.getText().trim();
            String timeText = txtTime.getText().trim();
            
            // Don't use placeholder text
            if (dateText.equals("YYYY-MM-DD") || timeText.equals("HH:MM")) {
                JOptionPane.showMessageDialog(this, "Please enter valid date and time");
                return;
            }
            
            Appointment appointment = new Appointment(
                cbStudent.getSelectedItem().toString(),
                cbCouncelor.getSelectedItem().toString(),
                dateText,
                timeText,
                "Scheduled"
            );

            if (appointmentController.addAppointment(appointment)) {
                JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
                clearInputs();
                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to book appointment. Please check your input.", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error booking appointment: " + e.getMessage(), 
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInputs() {
        if (cbStudent.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a student");
            return false;
        }
        if (cbCouncelor.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a counselor");
            return false;
        }
        
        String dateText = txtDate.getText().trim();
        String timeText = txtTime.getText().trim();
        
        if (dateText.isEmpty() || dateText.equals("YYYY-MM-DD")) {
            JOptionPane.showMessageDialog(this, "Please enter a date (YYYY-MM-DD)");
            return false;
        }
        if (timeText.isEmpty() || timeText.equals("HH:MM")) {
            JOptionPane.showMessageDialog(this, "Please enter a time (HH:MM)");
            return false;
        }
        
        // Basic date format validation
        if (!dateText.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Please enter date in YYYY-MM-DD format\nExample: " + LocalDate.now().toString());
            return false;
        }
        
        // Basic time format validation
        if (!timeText.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Please enter time in HH:MM format\nExample: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            return false;
        }
        
        return true;
    }

    private void clearInputs() {
        cbStudent.setSelectedIndex(0);
        cbCouncelor.setSelectedIndex(0);
        
        // Reset to placeholder text
        txtDate.setText("YYYY-MM-DD");
        txtDate.setForeground(java.awt.Color.GRAY);
        txtTime.setText("HH:MM");
        txtTime.setForeground(java.awt.Color.GRAY);
    }

    private void resetBookButton() {
        btnBookAppointment.setText("Book Appointment");
        for (var listener : btnBookAppointment.getActionListeners()) {
            btnBookAppointment.removeActionListener(listener);
        }
        btnBookAppointment.addActionListener(e -> bookAppointment());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbStudent = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbCouncelor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        btnBookAppointment = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointment = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnEditAppointment = new javax.swing.JButton();
        btnCancelAppointment = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 830));

        jLabel1.setText("Student");

        cbStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Counselor");

        cbCouncelor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Date");

        txtDate.setText("YYYY-MM-DD");

        jLabel4.setText("Time");

        txtTime.setText("HH:MM");

        btnBookAppointment.setText("Book Appointment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCouncelor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4)
                    .addComponent(btnBookAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbCouncelor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBookAppointment)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblAppointment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Student", "Counselor", "Date", "Time"
            }
        ));
        jScrollPane1.setViewportView(tblAppointment);

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

        btnEditAppointment.setText("Edit Appointment");
        btnEditAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAppointmentActionPerformed(evt);
            }
        });

        btnCancelAppointment.setText("Cancel Appointment");
        btnCancelAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditAppointment)
                .addGap(18, 18, 18)
                .addComponent(btnCancelAppointment)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditAppointment)
                    .addComponent(btnCancelAppointment))
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
                .addContainerGap(430, Short.MAX_VALUE))
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
                .addContainerGap(219, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAppointmentActionPerformed
        // TODO add your handling code here:
        int row = tblAppointment.getSelectedRow();
        if (row >= 0) {
        String id = tblAppointment.getValueAt(row, 0).toString(); // assuming ID is in column 0

        Appointment updated = new Appointment(
            Integer.parseInt(id),
            cbStudent.getSelectedItem().toString(),
            cbCouncelor.getSelectedItem().toString(),
            txtDate.getText().trim(),
            txtTime.getText().trim(),
            "Scheduled" // or allow user to select status if needed
        );

        if (appointmentController.updateAppointment(updated)) {
            JOptionPane.showMessageDialog(null, "Appointment updated successfully!");
            loadAppointments(); // Refresh the table
            clearInputs();      // Reset the form
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update appointment.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select a row to update.");
    }        
    }//GEN-LAST:event_btnEditAppointmentActionPerformed

    private void btnCancelAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAppointmentActionPerformed
        // TODO add your handling code here:
        int rowIndexToRemove = tblAppointment.getSelectedRow();
        
        if (rowIndexToRemove >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblAppointment.getModel();

            // Assuming column 0 holds a unique ID for the appointment
            Object appointmentIdObj = model.getValueAt(rowIndexToRemove, 0);
            if (appointmentIdObj != null) {
                String appointmentId = appointmentIdObj.toString();

                // Delete from database
                boolean success = appointmentController.deleteAppointment(appointmentId);
                if (success) {
                    // Delete from table model
                    model.removeRow(rowIndexToRemove);
                    JOptionPane.showMessageDialog(null, "Appointment deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete appointment from database.",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }

    }//GEN-LAST:event_btnCancelAppointmentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBookAppointment;
    private javax.swing.JButton btnCancelAppointment;
    private javax.swing.JButton btnEditAppointment;
    private javax.swing.JComboBox<String> cbCouncelor;
    private javax.swing.JComboBox<String> cbStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointment;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
