package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String POSTGRESQL_JDBC_DRIVER_NAME = "org.postgresql.Driver";
    private static final String JDBC_POSTGRESQL_HOST = "jdbc:postgresql://localhost:5432/";
    private static final String WELLNESS_DB_NAME = "WellnessDB";
    private static final String BCSTUDENTS_DB_NAME = "BCStudents";
    private static final String USERNAME = "Stefan";
    private static final String PASSWORD = "Stefan007";

    public DBConnection() {}

    // Load the driver
    private static void loadDriver() throws SQLException {
        try {
            Class.forName(POSTGRESQL_JDBC_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
    }

    // Connect to WellnessDB
    public static Connection getWellnessConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(JDBC_POSTGRESQL_HOST + WELLNESS_DB_NAME, USERNAME, PASSWORD);
    }

    // Connect to BCStudents
    public static Connection getBCStudentsConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(JDBC_POSTGRESQL_HOST + BCSTUDENTS_DB_NAME, USERNAME, PASSWORD);
    }

    // Create tables in WellnessDB only (not for BCStudents)
    public static void createTables() {
        try (Connection conn = getWellnessConnection();
             Statement stmt = conn.createStatement()) {

            String createAppointments = "CREATE TABLE IF NOT EXISTS appointments (" +
                "id SERIAL PRIMARY KEY, " +
                "student VARCHAR(100) NOT NULL, " +
                "counselor VARCHAR(100) NOT NULL, " +
                "date VARCHAR(20) NOT NULL, " +
                "time VARCHAR(20) NOT NULL, " +
                "status VARCHAR(50) NOT NULL)";

            String createCounselors = "CREATE TABLE IF NOT EXISTS counselors (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "specialization VARCHAR(100) NOT NULL, " +
                "availability BOOLEAN NOT NULL)";

            String createFeedback = "CREATE TABLE IF NOT EXISTS feedback (" +
                "id SERIAL PRIMARY KEY, " +
                "student VARCHAR(100) NOT NULL, " +
                "rating INTEGER NOT NULL, " +
                "comments VARCHAR(500))";

            stmt.execute(createAppointments);
            stmt.execute(createCounselors);
            stmt.execute(createFeedback);

            System.out.println("WellnessDB tables created/verified successfully");
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get student names from BCStudents.students (tries several common column names)
    public static List<User> getStudentObjectsFromBCStudents() {
        List<User> students = new ArrayList<>();
        String query = "SELECT student_number, name, surname, email, phone, password FROM users ORDER BY name";
        try (Connection conn = getBCStudentsConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String studentNumber = rs.getString("student_number");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                students.add(new User(studentNumber, name, surname, email, phone, password));
            }
        } catch (SQLException e) {
            System.err.println("Could not fetch students from BCStudents: " + e.getMessage());
        }
        return students;
    }
}