package controller;

import model.Feedback;
import model.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackController {
    
    public FeedbackController() {
        DBConnection.createTables();
    }
    
    // CREATE
    public boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback (student, rating, comments) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getWellnessConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, feedback.getStudent());
            pstmt.setInt(2, feedback.getRating());
            pstmt.setString(3, feedback.getComment()); // Fixed: was getComments()
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM feedback ORDER BY id DESC";
        
        try (Connection conn = DBConnection.getWellnessConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                feedbackList.add(new Feedback(
                    rs.getInt("id"),
                    rs.getString("student"),
                    rs.getInt("rating"),
                    rs.getString("comments")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }
}