package controller;

import model.Counselor;
import model.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CounselorController 
{
    private DBConnection dBConnection;
    
    public CounselorController() 
    {
        dBConnection = new DBConnection();
    }
    
    // CREATE
    public boolean addCounselor(Counselor counselor) throws ClassNotFoundException {
        String sql = "INSERT INTO counselors (name, specialization, availability) VALUES (?, ?, ?)";
        
        Connection conn = dBConnection.getWellnessConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            
            pstmt.setString(1, counselor.getName());
            pstmt.setString(2, counselor.getSpecialization());
            pstmt.setBoolean(3, counselor.isAvailable()); // Fixed: was isAvailability()
            
            return pstmt.executeUpdate() > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public List<Counselor> getAllCounselors() throws ClassNotFoundException 
    {
        List<Counselor> counselors = new ArrayList<>();
        String sql = "SELECT * FROM counselors ORDER BY name";
        Connection conn = dBConnection.getWellnessConnection();
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
        {
            
            while (rs.next()) {
                counselors.add(new Counselor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getBoolean("availability")
                ));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return counselors;
    }
}