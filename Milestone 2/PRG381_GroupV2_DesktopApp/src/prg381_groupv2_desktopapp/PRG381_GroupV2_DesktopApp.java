package prg381_groupv2_desktopapp;

import view.DashboardGUI;
import model.DBConnection;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author thian
 */
public class PRG381_GroupV2_DesktopApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Initialize database and create tables
            initializeDatabase();
            
            // Set look and feel
            setLookAndFeel();
            
            // Launch GUI on EDT
            SwingUtilities.invokeLater(() -> {
                try {
                    DashboardGUI dashboard = new DashboardGUI();
                    dashboard.setVisible(true);
                } catch (Exception e) {
                    showError("Error launching application", e);
                }
            });
            
        } catch (Exception e) {
            showError("Error initializing application", e);
        }
    }
    
    private static void initializeDatabase() {
        try {
            // Test database connection
            DBConnection.getWellnessConnection().close();
            
            // Create tables if they don't exist
            DBConnection.createTables();
            
            System.out.println("Database initialized successfully");
            
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            e.printStackTrace();
            
            // Show error dialog
            JOptionPane.showMessageDialog(null, 
                "Database connection failed!\nPlease ensure JavaDB/Derby is available.\n\nError: " + e.getMessage(),
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            
            System.exit(1);
        }
    }
    
    private static void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, use default look and feel
            System.out.println("Nimbus look and feel not available, using default");
        }
    }
    
    private static void showError(String message, Exception e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
        
        JOptionPane.showMessageDialog(null, 
            message + "\n\nError: " + e.getMessage(),
            "Application Error", 
            JOptionPane.ERROR_MESSAGE);
        
        System.exit(1);
    }
}