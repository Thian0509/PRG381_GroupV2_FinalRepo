package model;

import java.sql.*;
import java.util.*;

public class DBConnection {
    
    //INTERNAL Connection field
    Connection DerbyConnection;
    Connection PostgresSQLConnection;
    
    //DERBY DB RELATED CONFIGURATION VARIABLE
    private static final String JDBC_DERBY_URL = "jdbc:derby:wellnessDB;create=true";
    private static final String DERBY_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String WELLNESS_DB_NAME = "wellnessDB";
    
    //POSTGRESQL DB RELATED CONFIGURATION VARIABLES 
    private static final String POSTGRESQL_JDBC_DRIVER_NAME = "org.postgresql.Driver";
    private static final String JDBC_POSTGRESQL_HOST = "jdbc:postgresql://localhost:5432/";
    private static final String BCSTUDENTS_DB_NAME = "BCStudents";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    public DBConnection() 
    {
        try 
        {
            getWellnessConnection(); 
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    
    // WellnessDB Related code
            
    public Connection getWellnessConnection() throws ClassNotFoundException {
        try {
            if (this.DerbyConnection == null || this.DerbyConnection.isClosed()) {
                Class.forName(DERBY_DRIVER);
                this.DerbyConnection = DriverManager.getConnection(JDBC_DERBY_URL);
                System.out.println("Connected Successfully to derbyDB");
            }
            return this.DerbyConnection;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<User> getStudentObjectsFromWellnessDB() throws ClassNotFoundException 
    {
        List<User> students = new ArrayList<>();
        String query = "SELECT student_number, name, surname, email, phone, password FROM users ORDER BY name";
        
        Connection conn = getWellnessConnection();
        
        if (conn == null) 
        {
            System.err.println("Connection to wellnessDB is null.");
            return students;
        }
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) 
        {

            while (rs.next()) 
            {
                String studentNumber = rs.getString("student_number");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                students.add(new User(studentNumber, name, surname, email, phone, password));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Could not fetch students from wellnessDB: " + e.getMessage());
        }
        return students;
    }
        
        
    public void createTables() {
        try {
            DatabaseMetaData dbm = DerbyConnection.getMetaData();

            // Check and create users table
            if (!tableExists(dbm, "users")) 
            {
                try (Statement stmt = DerbyConnection.createStatement()) 
                {
                    String createUsersTable = "CREATE TABLE users (" +
                        "student_number VARCHAR(20) PRIMARY KEY, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "surname VARCHAR(100) NOT NULL, " +
                        "email VARCHAR(255) UNIQUE NOT NULL, " +
                        "phone VARCHAR(20) UNIQUE, " +
                        "password VARCHAR(255) NOT NULL" +
                        ")";
                    stmt.execute(createUsersTable);
                }
            }

            // Check and create appointments table
            if (!tableExists(dbm, "appointments")) 
            {
                try (Statement stmt = DerbyConnection.createStatement()) 
                {
                    String createAppointments = "CREATE TABLE appointments (" +
                        "id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                        "student VARCHAR(100) NOT NULL, " +
                        "counselor VARCHAR(100) NOT NULL, " +
                        "date VARCHAR(20) NOT NULL, " +
                        "time VARCHAR(20) NOT NULL, " +
                        "status VARCHAR(50) NOT NULL" +
                        ")";
                    stmt.execute(createAppointments);
                }
            }

            // Check and create counselors table
            if (!tableExists(dbm, "counselors")) 
            {
                try (Statement stmt = DerbyConnection.createStatement()) 
                {
                    String createCounselors = "CREATE TABLE counselors (" +
                        "id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "specialization VARCHAR(100) NOT NULL, " +
                        "availability BOOLEAN NOT NULL" +
                        ")";
                    stmt.execute(createCounselors);
                }
            }

            // Check and create feedback table
            if (!tableExists(dbm, "feedback")) 
            {
                try (Statement stmt = DerbyConnection.createStatement()) 
                {
                    String createFeedback = "CREATE TABLE feedback (" +
                        "id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                        "student VARCHAR(100) NOT NULL, " +
                        "rating INTEGER NOT NULL, " +
                        "comments VARCHAR(500)" +
                        ")";
                    stmt.execute(createFeedback);
                }
            }

            System.out.println("All required tables created or already exist.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private boolean tableExists(DatabaseMetaData dbm, String tableName) throws SQLException // Check if table existence
    {
        try (ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(), null)) 
        {
            return tables.next();
        }
    }
    
    
    public void syncStudentsToWellnessDB() //Map postgreSQL users who registered on webapp to derbyDB for use in desktop app
    {
        try
        {
            getBCStudentsConnection(); 
           
           
            List<User> students = getStudentObjectsFromBCStudents();

            if (students.isEmpty()) 
            {
                System.out.println("No students fetched from BCStudents.");
                return;
            }

            String insertSQL = "INSERT INTO users (student_number, name, surname, email, phone, password) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";

            for (User student : students) {
                try (PreparedStatement pstmt = DerbyConnection.prepareStatement(insertSQL)) {
                    pstmt.setString(1, student.getStudentNumber());
                    pstmt.setString(2, student.getName());
                    pstmt.setString(3, student.getSurname());
                    pstmt.setString(4, student.getEmail());
                    pstmt.setString(5, student.getPhone());
                    pstmt.setString(6, student.getPassword());

                    pstmt.executeUpdate();
                    System.out.println("Inserted student: " + student.getStudentNumber());

                } 
                catch (SQLIntegrityConstraintViolationException dupEx) 
                {
                    System.out.println("Duplicate found, skipping: " + student.getStudentNumber());
                } 
                catch (SQLException e) 
                {
                    System.err.println("Failed to insert student: " + student.getStudentNumber());
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }


    //BCStudents Related Code
    
    private boolean isLoadDriver() throws SQLException // Load the driver
    {
        try
        {
            Class.forName(POSTGRESQL_JDBC_DRIVER_NAME);
            return true;
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Cant find driver, check if you have added .jar file path in project Libraries: See Output ");
            e.printStackTrace();
            return false;
        }
    }

    

        
    private void getBCStudentsConnection() throws SQLException // Connect to BCStudents
    {
        if(!isLoadDriver()){
            System.err.println("Error: no driver configured.");
            return;
        };
        
        try
        {
            this.PostgresSQLConnection = DriverManager.getConnection(JDBC_POSTGRESQL_HOST + BCSTUDENTS_DB_NAME, USERNAME, PASSWORD);
            if(PostgresSQLConnection != null)
            {
                System.out.println("PostgreSQL connected successfully.");
            }
            else
            {
                System.err.println("Failed to connection.");
                return;
            }
        }
        catch(SQLException e)
        {
            System.err.println("Error: Connection failed! Check output console.");
            e.printStackTrace();            
        }        
    }


    private List<User> getStudentObjectsFromBCStudents() 
    {
        List<User> students = new ArrayList<>();
        String query = "SELECT student_number, name, surname, email, phone, password FROM users ORDER BY name";
        try (Statement stmt = this.PostgresSQLConnection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String studentNumber = rs.getString("student_number");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                students.add(new User(studentNumber, name, surname, email, phone, password));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Could not fetch students from BCStudents: " + e.getMessage());
        }
        return students;
    }
    
    
    
    //Initialize
    public void initialize() 
    {
        try 
        {
            if (this.DerbyConnection == null) 
            {
                getWellnessConnection();
            }
            createTables();
            syncStudentsToWellnessDB();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


}