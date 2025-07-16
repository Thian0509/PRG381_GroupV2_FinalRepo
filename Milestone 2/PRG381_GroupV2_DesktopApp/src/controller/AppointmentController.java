package controller;

import model.Appointment;
import model.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {
    private WellnessManager wellnessManager;
    
    public AppointmentController() {
        this.wellnessManager = WellnessManager.getInstance();
        try {
            DBConnection.createTables();
        } catch (Exception e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
        }
    }
    
    public boolean addAppointment(Appointment appointment) {
        // Simple validation
        if (!isValidAppointment(appointment)) {
            return false;
        }
        
        String sql = "INSERT INTO appointments (student, counselor, date, time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getWellnessConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, appointment.getStudent());
            pstmt.setString(2, appointment.getCounselor());
            pstmt.setString(3, appointment.getDate());
            pstmt.setString(4, appointment.getTime());
            pstmt.setString(5, appointment.getStatus());
            
            boolean result = pstmt.executeUpdate() > 0;
            if (result) {
                wellnessManager.addAppointment(appointment);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY date, time";
        
        try (Connection conn = DBConnection.getWellnessConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("id"),
                    rs.getString("student"),
                    rs.getString("counselor"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    private boolean isValidAppointment(Appointment appointment) {
        return appointment.getStudent() != null && !appointment.getStudent().trim().isEmpty() &&
               appointment.getCounselor() != null && !appointment.getCounselor().trim().isEmpty() &&
               appointment.getDate() != null && !appointment.getDate().trim().isEmpty() &&
               appointment.getTime() != null && !appointment.getTime().trim().isEmpty();
    }
}