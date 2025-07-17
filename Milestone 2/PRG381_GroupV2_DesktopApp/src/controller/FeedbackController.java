package controller;

import model.Feedback;
import model.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackController {
    private DBConnection dBConnection;
    
    public FeedbackController() 
    {
        this.dBConnection = new DBConnection();
    }
    
    // CREATE
    public boolean addFeedback(Feedback feedback) throws ClassNotFoundException 
    {
        String sql = "INSERT INTO feedback (student, rating, comments) VALUES (?, ?, ?)";
        
        Connection conn = dBConnection.getWellnessConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            
            pstmt.setString(1, feedback.getStudent());
            pstmt.setInt(2, feedback.getRating());
            pstmt.setString(3, feedback.getComment()); // Fixed: was getComments()
            
            return pstmt.executeUpdate() > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public List<Feedback> getAllFeedback() throws ClassNotFoundException 
    {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM feedback ORDER BY id DESC";
        
        Connection conn = dBConnection.getWellnessConnection();
        try ( Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
        {
            
            while (rs.next()) {
                feedbackList.add(new Feedback(
                    rs.getInt("id"),
                    rs.getString("student"),
                    rs.getInt("rating"),
                    rs.getString("comments")
                ));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return feedbackList;
    }
    
    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE feedback SET rating = ?, comments = ? WHERE id = ?";

        try (Connection conn = dBConnection.getWellnessConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, feedback.getRating());
            ps.setString(2, feedback.getComment());
            ps.setInt(3, feedback.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFeedback(String id) {
        String sql = "DELETE FROM feedback WHERE id = ?";

        try (Connection conn = dBConnection.getWellnessConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
